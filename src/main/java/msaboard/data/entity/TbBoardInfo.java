package msaboard.data.entity;

import lombok.*;

import msaboard.data.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * 사용자 Entity
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Entity
@Getter
@Setter
@DynamicInsert
@Table(name = "TB_BOARD_INFO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/*@SequenceGenerator(
        name="TB_BOARD_INFO_SEQ_GENERATOR", //시퀀스 제너레이터 이름
        sequenceName="TB_BOARD_INFO_SEQ", //시퀀스 이름
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)*/
public class TbBoardInfo extends BaseTimeEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_BOARD_INFO_SEQ_GENERATOR")
    @Column(name = "BOARD_ID", nullable = false)
    private Long boardId;                                      //게시판 아이디

    @Column(name = "BOARD_TITLE")
    private String boardTitle;                                 //게시판 제목

    @Column(name = "BOARD_CONTENTS")
    private String boardContents;                              //게시판 내용

    @Column(name = "BOARD_COUNT", length = 8)
    private Long boardCount;                                   //게시판 조회수

    @Builder
    public TbBoardInfo(Long boardId, String boardTitle, String boardContents, Long boardCount){
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardCount = boardCount;
    }
}