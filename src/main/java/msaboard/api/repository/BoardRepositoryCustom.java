package msaboard.api.repository;

import msaboard.api.dto.BoardDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 게시판 querydsl interface
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
public interface BoardRepositoryCustom {

    List<BoardDto> getBoardInfoList();

    void insertBoard(BoardDto boardDto);

    void updateBoard(BoardDto boardDto);

    void deleteBoard(BoardDto boardDto);

    BoardDto getBoardDetail(Long boardId);
}
