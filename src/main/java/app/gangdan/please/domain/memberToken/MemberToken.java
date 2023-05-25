package app.gangdan.please.domain.memberToken;


import app.gangdan.please.domain.base.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "member_token")
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class MemberToken extends BaseTimeEntity {

    @Id
    @Column(name = "member_token_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberTokenId;

    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    public static MemberToken create(String refreshToken, LocalDateTime tokenExpiredTime) {
        final MemberToken memberToken = MemberToken.builder()
                .refreshToken(refreshToken)
                .tokenExpirationTime(tokenExpiredTime)
                .build();

        return memberToken;
    }


    /**
     * 토큰 만료 시간 갱신
     * @param tokenExpirationTime
     */
    public void updateTokenExpireTime(LocalDateTime tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public void expire(LocalDateTime now) {
        if (tokenExpirationTime.isAfter(now)) {
            this.tokenExpirationTime = now;
        }
    }
}
