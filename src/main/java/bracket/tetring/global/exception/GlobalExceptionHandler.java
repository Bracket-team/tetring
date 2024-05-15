package bracket.tetring.global.exception;

import bracket.tetring.global.error.ErrorCode;
import bracket.tetring.global.error.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static bracket.tetring.global.error.ErrorCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setStatus(ex.getErrorCode().getStatus().value());
        exceptionResponse.setError("커스텀 에러입니다.");
        exceptionResponse.setMessage(ex.getErrorCode().getMessage());
        exceptionResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI());

        return new ResponseEntity<>(exceptionResponse, ex.getErrorCode().getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ExceptionResponse> handleServerException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setStatus(INTERNAL_SERVER_ERROR.getStatus().value());
        exceptionResponse.setError("서버 에러입니다.");
        exceptionResponse.setMessage(INTERNAL_SERVER_ERROR.getMessage());
        exceptionResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI());
        log.info("서버 오류 메세지={}", ex.getMessage());
        log.info("에러 종류={}", ex.getClass().getName());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
