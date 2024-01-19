package msaboard.api.repository;

import msaboard.data.entity.TbBoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 게시판 repository
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
public interface BoardRepository extends JpaRepository<TbBoardInfo, Long>,BoardRepositoryCustom {

    @Query(nativeQuery = true, value="SELECT NEXTVAL(JANDB.TB_BOARD_INFO_SEQ)")
    Long nextValueBoardId();

    void deleteByRgstId(String rgstId);
}
