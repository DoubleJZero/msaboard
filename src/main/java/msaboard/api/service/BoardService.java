package msaboard.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msaboard.api.dto.BoardDto;
import msaboard.api.feign.UserFeignClient;
import msaboard.api.feign.UserInfoDto;
import msaboard.api.repository.BoardRepository;
import msaboard.data.entity.TbBoardInfo;
import msacore.constant.COMMON_MESSAGE;
import msacore.exception.CustomException;
import msacore.exception.CustomExceptionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시판 service
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserFeignClient userFeignClient;

    /**
     * 게시판 목록 조회
     * @return 게시판 목록
     */
    public List<BoardDto> getBoardList(){
        return boardRepository.findAll().stream().map(BoardDto::of).collect(Collectors.toList());
    }

    /**
     * 게시판 목록 조회 querydsl
     * @return 게시판 목록
     */
    public List<BoardDto> getBoardInfoList(){
        return boardRepository.getBoardInfoList();
    }

    /**
     * 게시판 상세 조회
     * @return 게시판 정보
     */
    public BoardDto getBoardDetail(Long boardId){
        return boardRepository.getBoardDetail(boardId);
    }

    /**
     * 게시판정보 저장
     * @param boardDto 게시판정보DTO
     */
    public void saveBoard(BoardDto boardDto){
        if(boardDto.getBoardId() == null){
            TbBoardInfo entity =  boardDto.toEntity();
            entity.setBoardId(boardRepository.nextValueBoardId());
            entity.setBoardCount(0L);
            entity.setRgstId(boardDto.getRgstId());
            entity.setUpdtId(boardDto.getRgstId());
            boardRepository.saveAndFlush(entity);
        }else{
            boardRepository.findById(boardDto.getBoardId())
                    .ifPresentOrElse(e -> {
                        e.setBoardTitle(boardDto.getBoardTitle());
                        e.setBoardContents(boardDto.getBoardContents());
                        boardRepository.saveAndFlush(e);
                    }, ()->{
                        TbBoardInfo entity =  boardDto.toEntity();
                        entity.setBoardId(boardRepository.nextValueBoardId());
                        entity.setBoardCount(0L);
                        entity.setRgstId(boardDto.getRgstId());
                        entity.setUpdtId(boardDto.getRgstId());
                        boardRepository.saveAndFlush(entity);
                    });
        }
    }

    /**
     * 게시판정보 insert querydsl
     * @param boardDto 게시판정보DTO
     */
    public void insertBoard(BoardDto boardDto){
        boardDto.setBoardId(boardRepository.nextValueBoardId());
        boardRepository.insertBoard(boardDto);
    }

    /**
     * 게시판정보 update querydsl
     * @param boardDto 게시판정보DTO
     */
    public void updateBoard(BoardDto boardDto){
        boardRepository.updateBoard(boardDto);
    }

    /**
     * 게시판정보 delete querydsl
     * @param boardDto 게시판정보DTO
     */
    public void deleteBoard(BoardDto boardDto){
        boardRepository.deleteBoard(boardDto);
    }

    /**
     * 게시판정보 delete by rgstId
     * @param rgstId 등록자 아이디
     */
    public void deleteBoardByRgstId(String rgstId){
        boardRepository.deleteByRgstId(rgstId);
    }

    /**
     * 사용자 상세 조회
     * @return 게시판 목록
     */
    public UserInfoDto getUserInfoDetail(String userId){
        return userFeignClient.getUserInfoDetail(userId);
    }

    /**
     * businessException test
     */
    public void occurBusinessException() throws CustomException {
        throw CustomExceptionFactory.createBusinessException(COMMON_MESSAGE.UNKNOWN);
    }

    /**
     * BadRequestException test
     */
    public void occurBadRequestException() throws CustomException {
        throw CustomExceptionFactory.createBadRequestException(COMMON_MESSAGE.BAD_REQUEST);
    }
}
