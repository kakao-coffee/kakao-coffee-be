package com.kakaoCoffee.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaoCoffee.common.customEnum.ErrorMessage;
import com.kakaoCoffee.common.customEnum.ErrorType;
import com.kakaoCoffee.common.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static final ErrorResponseDto ERROR_RESPONSE_DTO = ErrorResponseDto.create(
            ErrorType.ACCESS_DENIED_EXCEPTION,
            ErrorMessage.ACCESS_DENIED.getMessage()
    );

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException{

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, ERROR_RESPONSE_DTO);
            os.flush();
        }
    }
}