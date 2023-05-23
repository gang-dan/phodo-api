package app.gangdan.phodoapi.global.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDto {

    private int code;
    private String errorMessage;
    private String referrerUrl;

}
