package com.fastcampus.javaallinone.project3.demo.exception.handler;

import com.fastcampus.javaallinone.project3.demo.exception.PersonNotFoundException;
import com.fastcampus.javaallinone.project3.demo.exception.RenameIsNotPermittedException;
import com.fastcampus.javaallinone.project3.demo.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RenameIsNotPermittedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRenameNoPermittedException(RenameIsNotPermittedException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePersonNotFoundException(PersonNotFoundException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(RuntimeException ex){
        log.error("서버 오류 : {}", ex.getMessage(), ex);
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 오류가 발생하였습니다.");
    }
}
