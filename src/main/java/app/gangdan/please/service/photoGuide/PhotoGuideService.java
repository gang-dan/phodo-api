package app.gangdan.please.service.photoGuide;

import app.gangdan.please.domain.hashtag.Hashtag;
import app.gangdan.please.domain.member.Member;
import app.gangdan.please.domain.member.MemberRepository;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoGuide.PhotoGuideRepository;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import app.gangdan.please.domain.photoSpot.PhotoSpotRepository;
import app.gangdan.please.global.exception.BadRequestException;
import app.gangdan.please.service.google.GooglePlaceService;
import app.gangdan.please.service.photoSpot.PhotoSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoGuideService {

    private final GooglePlaceService googlePlaceService;
    private final PhotoSpotService photoSpotService;
    private final PhotoGuideRepository photoGuideRepository;
    private final PhotoSpotRepository photoSpotRepository;
    private final MemberRepository memberRepository;

    /**
     * 포토 가이드 생성
     */
    public PhotoGuide create(Long memberId, MultipartFile requestImage, Double latitude, Double longitude, String hashtags, String photoGuideName) {

        // 등록된 photoSpot인지 확인
        String photoSpotName = googlePlaceService.getPlaceName(latitude, latitude);
        PhotoSpot photoSpot;

        if(photoSpotRepository.findByName(photoSpotName) != null){

            // 등록되지 않은 photoSpot -> 생성부터
            photoSpot = photoSpotService.create(memberId, requestImage, latitude, longitude);

        } else {

            // 등록된 photoSpot -> 가져오기
            photoSpot = photoSpotRepository.findByName(googlePlaceService.getPlaceName(latitude, longitude));
        }

        // SegmentationService <-> model 호출 로직

        // output S3 저장 로직

        // photoGuide 생성
        PhotoGuide photoGuide = PhotoGuide.create(photoSpot, findMemberOrThrow(memberId), photoGuideName);
        photoGuideRepository.save(photoGuide);

        // hashtag 생성
        List<String> hashtagNames = new ArrayList<>();
        String[] hashtagNameList = hashtags.split(",");

        for (String hashtagName : hashtagNameList) {
            Hashtag.create(photoGuide, hashtagName);
        }
        return photoGuide;
    }

    /**
     * 포토 가이드 리스트 조회
     */
    public List<PhotoGuide> getPhotoGuideList(Long photoSpotId) {

        return photoGuideRepository.findByPhotoSpotId(photoSpotId);
    }

    /**
     * 포토 가이드 상세 조회
     */
    public PhotoGuide getPhotoGuide(Long photoGuideId) {

        return photoGuideRepository.findById(photoGuideId).orElseThrow(() -> {
            throw new BadRequestException("존재하지 않는 가이드 입니다.");
        });
    }


    private Member findMemberOrThrow(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new BadRequestException("존재하지 않는 멤버입니다.");
        });
    }

}
