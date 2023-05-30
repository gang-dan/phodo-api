package app.gangdan.please.dto.oauth;

import app.gangdan.please.domain.member.constant.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributesKey;
    private String name;
    private String email;
    private String picture;
    private SocialType socialType;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributesKey, String name, String email, String picture, SocialType socialType) {
        this.attributes = attributes;
        this.nameAttributesKey = nameAttributesKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.socialType = socialType;
    }
}
