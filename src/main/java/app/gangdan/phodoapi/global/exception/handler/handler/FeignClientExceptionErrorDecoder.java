package app.gangdan.phodoapi.global.exception.handler.handler;

import app.gangdan.phodoapi.global.exception.handler.FeignClientException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.codec.StringDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignClientExceptionErrorDecoder implements ErrorDecoder {

    private StringDecoder stringDecoder = new StringDecoder();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info(response.toString());
        String message = response.reason();
        return new FeignClientException(response.status(), message, response.headers());
    }
}
