package app.gangdan.please.service.oauth;

import app.gangdan.please.domain.member.Member;
import app.gangdan.please.domain.member.MemberRepository;
import app.gangdan.please.domain.member.constant.SocialType;
import app.gangdan.please.domain.memberToken.MemberToken;
import app.gangdan.please.domain.memberToken.MemberTokenRepository;
import app.gangdan.please.dto.member.jwt.ResponseJwtTokenDto;
import app.gangdan.please.dto.member.jwt.TokenDto;
import app.gangdan.please.dto.oauth.OAuthAttributes;
import app.gangdan.please.dto.oauth.OauthLoginDto;
import app.gangdan.please.global.exception.MemberTokenNotFoundException;
import app.gangdan.please.service.jwt.TokenProvider;
import app.gangdan.please.service.oauth.google.GoogleFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {

    private final GoogleFeignService googleFeignService;
    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final MemberTokenRepository memberTokenRepository;


    // 7
    public ResponseJwtTokenDto createMemberAndJwt(OauthLoginDto oauthLoginDto) {

        // 소셜 회원 정보 조회
        final OAuthAttributes socialUserInfo = getSocialUserInfo(oauthLoginDto);
        log.info("oauthAttributes: {}", socialUserInfo.toString());

        // 회원 가입 or 로그인
        Member requestMember;
        final Optional<Member> foundMember = memberRepository.findByEmail(socialUserInfo.getEmail());

        if (foundMember.isEmpty()) { // 기존 회원 아닐 때
            Member newMember = Member.create(socialUserInfo);
            requestMember = memberRepository.save(newMember);
        } else {
            requestMember = foundMember.get(); // 기존 회원일 때
        }

        // JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.createTokenDto(requestMember.getMemberId());
        log.info("tokenDto: {}", tokenDto);

        ResponseJwtTokenDto responseJwtTokenDto = modelMapper.map(tokenDto, ResponseJwtTokenDto.class);
        final boolean isNewMember = StringUtils.isEmpty(requestMember.getUsername());
        responseJwtTokenDto.setIsNewMember(isNewMember);
        if (!isNewMember) {
            responseJwtTokenDto.setMemberName(requestMember.getUsername());
        }
        responseJwtTokenDto.setMemberId(requestMember.getMemberId());

        return responseJwtTokenDto;
    }

    private OAuthAttributes getSocialUserInfo(OauthLoginDto oauthLoginDto) {
        final String accessToken = oauthLoginDto.getAccessToken();
        final SocialType socialType = oauthLoginDto.getSocialType();
        return googleFeignService.getUserInfo(accessToken);
    }

    // 1
    public String getAccessToken(String authorizationCode) {
        return googleFeignService.getAccess(authorizationCode);
    }

    // 6
    public ResponseJwtTokenDto login(SocialType socialType, String accessToken) {
        final OauthLoginDto oauthLoginDto = OauthLoginDto.builder().accessToken(accessToken).socialType(socialType).build();
        return createMemberAndJwt(oauthLoginDto);
    }

    // 3
    public void validateLoginParams(String socialType, String accessToken) {
        validateSocialType(socialType);
        validateAccessToken(accessToken);
    }

    // 4
    private void validateSocialType(String socialType) {
        if (!EnumUtils.isValidEnumIgnoreCase(SocialType.class, socialType)) {
            throw new InvalidParameterException("잘못된 소셜 타입입니다. 'GOOGLE' 중에 입력해주세요.");
        }
    }

    // 5
    private void validateAccessToken(String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            throw new InvalidParameterException("Access 토큰값을 입력해주세요.");
        }
    }

    // 8
    public void logout(String refreshToken, LocalDateTime now) {
        final MemberToken memberToken = memberTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new MemberTokenNotFoundException("해당 리프레시 토큰이 존재하지 않습니다."));
        memberToken.expire(now);
    }
}