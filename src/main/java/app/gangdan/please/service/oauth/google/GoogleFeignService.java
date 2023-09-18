package app.gangdan.please.service.oauth.google;

import app.gangdan.please.domain.member.constant.SocialType;
import app.gangdan.please.dto.oauth.GoogleUserInfo;
import app.gangdan.please.dto.oauth.OAuthAttributes;
import app.gangdan.please.global.exception.BadRequestException;
import app.gangdan.please.service.oauth.google.GoogleInfoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GoogleFeignService {
    GoogleInfoFeignClient googleInfoFeignClient;

    @Value("${oauth2.redirectUri}")
    private String REDIRECT_URI;

    @Value("${oauth2.clientId}")
    private String CLIENT_ID;

    @Value("${oauth2.clientSecret}")
    private String CLIENT_SECRET;

    private final String GRANT_TYPE = "authorization_code";
    private final String REQUEST_URI = "https://www.googleapis.com/oauth2/v4/token";


    public OAuthAttributes getUserInfo(String accessToken) {
        accessToken = "Bearer " + accessToken.replace("Bearer", "").trim();
        log.info("Access Token: {}", accessToken);

        GoogleUserInfo googleUserInfo = googleInfoFeignClient.getGoogleUserInfo(accessToken);

        log.info("email: {}", googleUserInfo.getEmail());
        log.info("name: {}", googleUserInfo.getName());
        log.info("pictureUrl : {}", googleUserInfo.getPicture());

        return OAuthAttributes.builder()
                .email(StringUtils.isBlank(googleUserInfo.getEmail()) ? googleUserInfo.getId() : googleUserInfo.getEmail()) // 이메일 동의 x 경우
                .name(googleUserInfo.getName())
                .picture(googleUserInfo.getPicture())
                .socialType(SocialType.GOOGLE)
                .build();
    }

    // 2
//    public String getAccess(String authorizationCode) {
//
//        String accessToken = getAccessToken(authorizationCode);
//        log.info("access token: {}", accessToken);
//        return accessToken;
//    }

//    private String getAccessToken(String code) {
//        try {
//
//            log.info("codecode ::::::::::::: " + code);
//
//            final HttpEntity request = createRequest(code);
//
//            ResponseEntity<Map> response = postRequest(request);
//
//            log.info("plz response: {}", response.toString());
//
//            return (String) Objects.requireNonNull(response.getBody()).get("access_token");
//
//        } catch (HttpClientErrorException e) {
//            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
//                throw new BadRequestException(e.getMessage() + "getAccessToken err!!!!!!!!!!!!!");
//            }
//
//            throw e;
//        }
//    }

//    private HttpEntity<MultiValueMap<String, String>> createRequest(String code) {
//        //HTTPHeader 오브젝트 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        //HttpBody 오브젝트 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // body 데이터를 담을 value
//        params.add("client_id", CLIENT_ID);
//        params.add("client_secret", CLIENT_SECRET);
//        params.add("code", code);
//        params.add("grant_type", GRANT_TYPE);
//        params.add("redirect_uri", REDIRECT_URI);
//
//        //HTTPHeader 와 HTTPBody 를 하나의 오브젝트에 담기
//        return new HttpEntity<>(params, headers);
//    }

    private ResponseEntity<Map> postRequest(HttpEntity request) {
        return new RestTemplate().postForEntity(REQUEST_URI, request, Map.class);
    }
}
