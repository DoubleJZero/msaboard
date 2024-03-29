package msaboard.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import msaboard.data.entity.TbBoardInfo;

/**
 * 게시판 DTO
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BoardDto {
    private Long boardId;                                       //게시판 아이디

    private String boardTitle;                                  //게시판 제목

    private String boardContents;                               //게시판 내용

    private Long boardCount;                                    //게시판 조회수

    private String rgstId;                                      //게시판 등록자 아아디

    /**
     * dto -> entity convert
     * @return entity
     */
    @JsonIgnore
    public TbBoardInfo toEntity(){
        return TbBoardInfo.builder()
                .boardId(boardId)
                .boardTitle(boardTitle)
                .boardContents(boardContents)
                .boardCount(boardCount)
                .build();
    }

    /**
     * entity -> dto convert
     * @param tbBoardInfo 게시판정보 entity
     * @return BoardDto
     */
    @JsonIgnore
    public static BoardDto of(TbBoardInfo tbBoardInfo){
        return BoardDto.builder()
                .boardId(tbBoardInfo.getBoardId())
                .boardTitle(tbBoardInfo.getBoardTitle())
                .boardContents(tbBoardInfo.getBoardContents())
                .boardCount(tbBoardInfo.getBoardCount())
                .rgstId(tbBoardInfo.getRgstId())
                .build();
    }
}
