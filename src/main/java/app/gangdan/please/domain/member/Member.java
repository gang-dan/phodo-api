package app.gangdan.please.domain.member;

import app.gangdan.please.domain.BaseEntity;
import app.gangdan.please.dto.oauth.OAuthAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String profileImage;

    public static Member create(OAuthAttributes socialUserInfo) {
        return Member.builder()
                .email(socialUserInfo.getEmail())
                .username("")
                .profileImage("")
                .build();
    }

}
