package app.gangdan.please.dto.oauth;

import app.gangdan.please.domain.member.constant.SocialType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthLoginDto {

    private String accessToken;

    private SocialType socialType;
}
