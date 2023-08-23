package com.example.news.news.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNewsImage is a Querydsl query type for NewsImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewsImage extends EntityPathBase<NewsImage> {

    private static final long serialVersionUID = -1572927154L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNewsImage newsImage = new QNewsImage("newsImage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final QNews news;

    public QNewsImage(String variable) {
        this(NewsImage.class, forVariable(variable), INITS);
    }

    public QNewsImage(Path<? extends NewsImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNewsImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNewsImage(PathMetadata metadata, PathInits inits) {
        this(NewsImage.class, metadata, inits);
    }

    public QNewsImage(Class<? extends NewsImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.news = inits.isInitialized("news") ? new QNews(forProperty("news")) : null;
    }

}

