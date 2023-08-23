package com.example.news.news.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNews is a Querydsl query type for News
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNews extends EntityPathBase<News> {

    private static final long serialVersionUID = 1714794509L;

    public static final QNews news = new QNews("news");

    public final BooleanPath aliveNews = createBoolean("aliveNews");

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath lock = createBoolean("lock");

    public final StringPath mainImageUrl = createString("mainImageUrl");

    public final SetPath<NewsImage, QNewsImage> newsImages = this.<NewsImage, QNewsImage>createSet("newsImages", NewsImage.class, QNewsImage.class, PathInits.DIRECT2);

    public final StringPath newsTitle = createString("newsTitle");

    public final SetPath<NewsVideo, QNewsVideo> newsVideos = this.<NewsVideo, QNewsVideo>createSet("newsVideos", NewsVideo.class, QNewsVideo.class, PathInits.DIRECT2);

    public final StringPath reporter = createString("reporter");

    public final NumberPath<Long> reporterIndex = createNumber("reporterIndex", Long.class);

    public QNews(String variable) {
        super(News.class, forVariable(variable));
    }

    public QNews(Path<? extends News> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNews(PathMetadata metadata) {
        super(News.class, metadata);
    }

}

