package com.example.news.news.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNewsVideo is a Querydsl query type for NewsVideo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewsVideo extends EntityPathBase<NewsVideo> {

    private static final long serialVersionUID = -1561037714L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNewsVideo newsVideo = new QNewsVideo("newsVideo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QNews news;

    public final StringPath videoUrl = createString("videoUrl");

    public QNewsVideo(String variable) {
        this(NewsVideo.class, forVariable(variable), INITS);
    }

    public QNewsVideo(Path<? extends NewsVideo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNewsVideo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNewsVideo(PathMetadata metadata, PathInits inits) {
        this(NewsVideo.class, metadata, inits);
    }

    public QNewsVideo(Class<? extends NewsVideo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.news = inits.isInitialized("news") ? new QNews(forProperty("news")) : null;
    }

}

