package com.example.news.news.repository.query;

import com.example.news.news.dto.NewsResponseDto;
import com.example.news.news.dto.NewsSearchCond;
import com.example.news.news.entity.News;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.news.news.entity.QNews.news;
import static com.example.news.news.entity.QNewsVideo.newsVideo;
import static com.example.news.news.entity.QNewsImage.newsImage;
@Repository
public class NewsQueryRepositoryImpl implements NewsQueryRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public NewsQueryRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<NewsResponseDto> getNewsMain() {
        List<News> newsList = jpaQueryFactory
                .selectFrom(news)
                .leftJoin(news.newsImages, newsImage).fetchJoin()
                .leftJoin(news.newsVideos, newsVideo).fetchJoin()
                .where(news.aliveNews.eq(true))
                .fetch();

        return NewsResponseDto.listFrom(newsList);
    }

    @Override
    public List<NewsResponseDto> getNewsMainPaging(Long pageNumber) {
        int pageSize = 10;
        pageNumber = Math.max(pageNumber, 1L);
        long offset = (pageNumber-1)*pageSize;
        offset = Math.max(offset, 0L);

        Integer one = jpaQueryFactory.selectOne()
                .from(news)
                .where(news.id.between(offset, offset+10))
                .fetchOne();

        List<NewsResponseDto> nullPointHander = new ArrayList<>();

        if(one == null){
            return nullPointHander;
        }

        List<News> newsList = jpaQueryFactory
                .selectFrom(news)
                .leftJoin(news.newsImages, newsImage).fetchJoin()
                .leftJoin(news.newsVideos, newsVideo).fetchJoin()
                .where(news.aliveNews.eq(true))
                .offset(offset)
                .limit(pageSize)
                .fetch();

        return NewsResponseDto.listFrom(newsList);
    }

    @Override
    public List<NewsResponseDto> getNewsMainSearch(NewsSearchCond newsSearchCond)
    {
        if(newsSearchCond.getNewsTitle()==null
            && newsSearchCond.getCategory() == null
            && newsSearchCond.getReporter() == null){
            throw new IllegalArgumentException("검색 내용을 입력해주세요");
        }
        List<News> newsList = jpaQueryFactory.selectFrom(news)
                .leftJoin(news.newsImages, newsImage).fetchJoin()
                .leftJoin(news.newsVideos, newsVideo).fetchJoin()
                .where(
                        searchByNewsTitle(newsSearchCond.getNewsTitle()),
                        searchByCategory(newsSearchCond.getCategory()),
                        searchByReporter(newsSearchCond.getReporter()),
                        news.aliveNews.eq(true)
                ).fetch();
        return NewsResponseDto.listFrom(newsList);
    }

    @Override
    public Optional<News> findByIdAndAliveNewsIsTrue(Long newsId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(news)
                        .leftJoin(news.newsVideos, newsVideo).fetchJoin()
                        .leftJoin(news.newsImages, newsImage).fetchJoin()
                        .where(news.aliveNews.eq(true),
                                news.id.eq(newsId))
                        .fetchFirst()
        );
    }

    @Override
    public boolean existsByNewsId(Long newsId) {
        return jpaQueryFactory.selectOne()
                .from(news)
                .where(news.id.eq(newsId))
                .fetchFirst() != null;
    }

    @Override
    public boolean existsByNewsTitle(String newsTitle) {
        return jpaQueryFactory.selectOne()
                .from(news)
                .where(news.newsTitle.eq(newsTitle))
                .fetchFirst() != null;
    }

    @Override
    public void deleteNewsById(Long newsId) {
        if(!existsByNewsId(newsId)){
            //The news does not exist.
            throw new IllegalArgumentException("해당 뉴스가 존재하지 않습니다.");
        }
        /*
            뉴스를 삭제하기전 연관된 데이터들부터 삭제
            그 이유는 데이터 일관성에 대한 위험이 있습니다.
            만약 NewsImage나 NewsVideo가 다른 엔티티에서도 참조되고 있다면, 
            삭제될 때 예상치 못한 문제가 발생할 수 있습니다.
        */
        jpaQueryFactory.delete(newsVideo)
                .where(newsVideo.news.id.eq(newsId))
                .execute();

        jpaQueryFactory.delete(newsImage)
                .where(newsImage.news.id.eq(newsId))
                .execute();

        //NewsEntity 삭제
        jpaQueryFactory.delete(news).
                where(news.id.eq(newsId))
                .execute();

    }

    private BooleanExpression searchByNewsTitle(String newsTitle){
        return Optional.ofNullable(newsTitle)
                .map(news.newsTitle::contains)
                .orElse(null);
    }

    private BooleanExpression searchByCategory(String category){
        return Optional.ofNullable(category)
                .map(news.newsTitle::contains)
                .orElse(null);
    }
    private BooleanExpression searchByReporter(String reporter){
        return Objects.nonNull(reporter)?
                news.newsTitle.contains(reporter):null;
    }
}
