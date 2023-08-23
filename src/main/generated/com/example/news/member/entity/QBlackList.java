package com.example.news.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlackList is a Querydsl query type for BlackList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlackList extends EntityPathBase<BlackList> {

    private static final long serialVersionUID = -2085044772L;

    public static final QBlackList blackList = new QBlackList("blackList");

    public final StringPath accessor = createString("accessor");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<Restriction, EnumPath<Restriction>> restriction = this.<Restriction, EnumPath<Restriction>>createSet("restriction", Restriction.class, EnumPath.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> restrictionEndDateTime = createDateTime("restrictionEndDateTime", java.time.LocalDateTime.class);

    public QBlackList(String variable) {
        super(BlackList.class, forVariable(variable));
    }

    public QBlackList(Path<? extends BlackList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlackList(PathMetadata metadata) {
        super(BlackList.class, metadata);
    }

}

