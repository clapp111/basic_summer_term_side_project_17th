package server.board.global.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.board.global.exception.error.CustomErrorCode;
import server.board.global.exception.error.ErrorCode;
import server.board.global.exception.error.ErrorResponse;
import server.board.global.exception.error.RestApiException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ErrorResponse> handleRestApiException(RestApiException e) {
        log.error("RestApiException", e);
        return createResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = CustomErrorCode.INVALID_REQUEST;
        log.error("MethodArgumentNotValidException", e);
        return createResponseEntity(errorCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorCode errorCode = CustomErrorCode.INTERNAL_SERVER_ERROR;
        log.error("Exception", e);
        return createResponseEntity(errorCode);
    }

    // Exception에 맞는 ErrorCode 생성
    private ResponseEntity<ErrorResponse> createResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(
                        errorCode.getCode(),
                        errorCode.getMessage()
                ));
    }
}