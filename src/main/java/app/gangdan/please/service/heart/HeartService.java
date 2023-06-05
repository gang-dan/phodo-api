package app.gangdan.please.service.heart;

import app.gangdan.please.domain.heart.Heart;
import app.gangdan.please.domain.heart.HeartRepository;
import app.gangdan.please.domain.member.Member;
import app.gangdan.please.domain.member.MemberRepository;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoGuide.PhotoGuideRepository;
import app.gangdan.please.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final PhotoGuideRepository photoGuideRepository;
    private final MemberRepository memberRepository;


    public Heart create(Long memberId, Long photoGuideId) {

        PhotoGuide photoGuide = findPhotoGuideOrThrow(photoGuideId);
        Member member = findMemberOrThrow(memberId);

        return Heart.create(photoGuide, member);
    }

    public void delete(Long memberId, Long heartId) {
        final Heart heart = findHeartOrThrow(heartId);
        if (heart.isCreator(memberId)) {
            heartRepository.deleteById(heartId);
        } else {
            throw new BadRequestException("해당 피드백 삭제 권한이 없습니다.");
        }
    }


    private PhotoGuide findPhotoGuideOrThrow(Long photoGuideId) {
        return photoGuideRepository.findById(photoGuideId).orElseThrow(() -> {
            throw new BadRequestException("존재하지 않는 가이드입니다.");
        });
    }

    private Member findMemberOrThrow(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new BadRequestException("존재하지 않는 멤버입니다.");
        });
    }

    private Heart findHeartOrThrow(Long heartId){
        return heartRepository.findById(heartId).orElseThrow(() -> {
            throw new BadRequestException("존재하지 않는 좋아요입니다.");
        });
    }
}
