package com.gmoi.shortify.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    private int status;
    private String error;
}
