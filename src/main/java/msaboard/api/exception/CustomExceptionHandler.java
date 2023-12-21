package msaboard.api.exception;

import msacore.constant.COMMON_MESSAGE;
import msacore.exception.CustomBadRequestException;
import msacore.exception.CustomException;
import msacore.exception.CustomExceptionFactory;
import msacore.payload.Response;
import msacore.payload.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * CustomExceptionHandler
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomBadRequestException.class)
    public Response<?> handleException(CustomBadRequestException cbre){
        return createCustomError(cbre);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Response<?> handleException(Exception e){
        return createCustomError(CustomExceptionFactory.createBusinessException(COMMON_MESSAGE.UNKNOWN));
    }

    private Response<?> createCustomError(CustomException ce){
        return ResponseFactory.createError(ce);
    }
}
