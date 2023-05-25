package app.gangdan.please.service.oauth.google;

import app.gangdan.please.dto.oauth.GoogleUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(url = "https://www.googleapis.com/oauth2", name = "GoogleInfoClient")
public interface GoogleInfoFeignClient {

    @GetMapping(value = "/v2/userinfo", produces = "application/json", consumes = "application/json")
    GoogleUserInfo getGoogleUserInfo(@RequestHeader("Authorization") String accessToken);
}
