package msaboard.api.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import msaboard.api.dto.BoardDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static msaboard.data.entity.QTbBoardInfo.tbBoardInfo;

/**
 * 게시판 querydsl repository implements
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 게시판 목록 조회 querydsl
     * @return 사용자 목록
     */
    @Override
    public List<BoardDto> getBoardInfoList() {
        return queryFactory.select(Projections.fields(BoardDto.class
                        , tbBoardInfo.boardId
                        , tbBoardInfo.boardTitle
                        , tbBoardInfo.boardContents
                        , tbBoardInfo.boardCount
                )).from(tbBoardInfo)
                .fetch();
    }

    /**
     * 게시판 insert querydsl
     */
    @Override
    public void insertBoard(BoardDto boardDto) {
        queryFactory
                .insert(tbBoardInfo)
                .columns(
                        tbBoardInfo.boardId,
                        tbBoardInfo.boardTitle,
                        tbBoardInfo.boardContents,
                        tbBoardInfo.boardCount,
                        tbBoardInfo.rgstId,
                        tbBoardInfo.rgstDttm,
                        tbBoardInfo.updtId,
                        tbBoardInfo.updtDttm
                )
                .values(
                        boardDto.getBoardId(),
                        boardDto.getBoardTitle(),
                        boardDto.getBoardContents(),
                        boardDto.getBoardCount(),
                        boardDto.getRgstId(),
                        LocalDateTime.now(),
                        boardDto.getRgstId(),
                        LocalDateTime.now()
                )
                .execute()
        ;
    }

    /**
     * 게시판 update querydsl
     */
    @Override
    public void updateBoard(BoardDto boardDto) {
        queryFactory.update(tbBoardInfo)
                .set(tbBoardInfo.boardTitle, boardDto.getBoardTitle())
                .set(tbBoardInfo.boardContents, boardDto.getBoardContents())
                .set(tbBoardInfo.boardCount, boardDto.getBoardCount())
                .set(tbBoardInfo.updtId, boardDto.getRgstId())
                .set(tbBoardInfo.updtDttm, LocalDateTime.now())
                .where(
                        tbBoardInfo.boardId.eq(boardDto.getBoardId())
                ).execute();
    }

    /**
     * 게시판 delete querydsl
     */
    @Override
    public void deleteBoard(BoardDto boardDto) {
        queryFactory
                .delete(tbBoardInfo)
                .where(
                        tbBoardInfo.boardId.eq(boardDto.getBoardId())

                )
                .execute();
    }

    /**
     * 게시판 상세 정보 조회
     * @param boardId 게시판 아이디
     * @return 게시판 정보
     */
    @Override
    public BoardDto getBoardDetail(Long boardId) {
        return queryFactory.select(Projections.fields(BoardDto.class
                        , tbBoardInfo.boardId
                        , tbBoardInfo.boardTitle
                        , tbBoardInfo.boardContents
                        , tbBoardInfo.boardCount
                        , tbBoardInfo.rgstId
                )).from(tbBoardInfo)
                .where(tbBoardInfo.boardId.eq(boardId))
                .fetchOne();
    }
}
