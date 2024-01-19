package msaboard.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msaboard.api.dto.BoardDto;
import msaboard.api.service.BoardService;
import org.springframework.web.bind.annotation.*;

/**
 * 게시판 controller other service feign call
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/feign")
public class FromOtherServiceCallController {

    private final BoardService boardService;

    /**
     * 게시판 상세 조회 other service feign call
     * @return 게시판 목록
     */
    @GetMapping("/getBoardDetail/{boardId}")
    //@RequestMapping(method = RequestMethod.GET, value = "/getBoardDetail/{boardId}", consumes = "application/json", produces = "application/json")
    public BoardDto getBoardDetail(@PathVariable Long boardId){
        return boardService.getBoardDetail(boardId);
    }

    /**
     * 사용자 삭제시 게시판 등록자 게시물 cascade 삭제
     * @param boardDto 게시판 정보 Dto
     */
    @PostMapping("/deleteBoardByRgstId")
    public void deleteBoardByRgstId(@RequestBody BoardDto boardDto){
        boardService.deleteBoardByRgstId(boardDto.getRgstId());
    }
}
