package com.client.ws.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String message;
    private HttpStatus httpStatus;
    private Integer statusCode;
}
