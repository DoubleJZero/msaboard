package msaboard.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTbBoardInfo is a Querydsl query type for TbBoardInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTbBoardInfo extends EntityPathBase<TbBoardInfo> {

    private static final long serialVersionUID = -360420260L;

    public static final QTbBoardInfo tbBoardInfo = new QTbBoardInfo("tbBoardInfo");

    public final msaboard.data.QBaseTimeEntity _super = new msaboard.data.QBaseTimeEntity(this);

    public final StringPath boardContents = createString("boardContents");

    public final NumberPath<Long> boardCount = createNumber("boardCount", Long.class);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath boardTitle = createString("boardTitle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> rgstDttm = _super.rgstDttm;

    //inherited
    public final StringPath rgstId = _super.rgstId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updtDttm = _super.updtDttm;

    //inherited
    public final StringPath updtId = _super.updtId;

    public QTbBoardInfo(String variable) {
        super(TbBoardInfo.class, forVariable(variable));
    }

    public QTbBoardInfo(Path<? extends TbBoardInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTbBoardInfo(PathMetadata metadata) {
        super(TbBoardInfo.class, metadata);
    }

}

