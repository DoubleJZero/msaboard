package msaboard.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msaboard.api.dto.BoardDto;
import msaboard.api.feign.UserInfoDto;
import msaboard.api.service.BoardService;
import msacore.exception.CustomException;
import msacore.payload.Response;
import msacore.payload.ResponseFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시판 controller
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
@RequestMapping("/")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판 목록 조회
     * @return 게시판 목록
     * @throws CustomException
     */
    @GetMapping("/list")
    public Response<List<BoardDto>> getBoardList() throws CustomException {
        return ResponseFactory.createSuccess(boardService.getBoardList());
    }

    /**
     * 게시판 목록 조회 querydsl
     * @return 게시판 목록
     */
    @GetMapping("/getBoardInfoList")
    public List<BoardDto> getBoardInfoList(){
        return boardService.getBoardInfoList();
    }

    /**
     * 게시판 상세 조회
     * @return 게시판 정보
     */
    @GetMapping("/getBoardDetail")
    public BoardDto getBoardDetail(@RequestBody BoardDto boardDto){
        return boardService.getBoardDetail(boardDto.getBoardId());
    }

    /**
     * 게시판정보 저장
     * @param boardDto 게시판정보DTO
     */
    @PostMapping("/saveBoard")
    public void saveBoard(@RequestBody BoardDto boardDto){
        boardService.saveBoard(boardDto);
    }

    /**
     * 게시판정보 insert querydsl
     * @param boardDto 게시판정보DTO
     */
    @PostMapping("/insertBoard")
    public void insertBoard(@RequestBody BoardDto boardDto){
        boardService.insertBoard(boardDto);
    }

    /**
     * 게시판정보 update querydsl
     * @param boardDto 게시판정보DTO
     */
    @PostMapping("/updateBoard")
    public void updateBoard(@RequestBody BoardDto boardDto){
        boardService.updateBoard(boardDto);
    }

    /**
     * 게시판정보 delete querydsl
     * @param boardDto 게시판정보DTO
     */
    @PostMapping("/deletesBoard")
    public void deleteBoard(@RequestBody BoardDto boardDto){
        boardService.deleteBoard(boardDto);
    }

    /**
     * 게시판정보 delete by rgstId
     * @param boardDto 게시판정보DTO
     */
    @PostMapping("/deleteBoardByRgstId")
    public void deleteBoardByRgstId(@RequestBody BoardDto boardDto){
        boardService.deleteBoardByRgstId(boardDto.getRgstId());
    }

    /**
     * 사용자 상세 조회
     * @return 사용자목록
     */
    @GetMapping("/getUserInfoDetail")
    public UserInfoDto getUserInfoDetail(@RequestParam String userId){
        return boardService.getUserInfoDetail(userId);
    }

    /**
     * occurBusinessException
     * @throws CustomException
     */
    @GetMapping("/occurBusinessException")
    public void occurBusinessException() throws CustomException {
        boardService.occurBusinessException();
    }

    /**
     * occurBadRequestException
     * @throws CustomException
     */
    @GetMapping("/occurBadRequestException")
    public void occurBadRequestException() throws CustomException {
        boardService.occurBadRequestException();
    }
}
