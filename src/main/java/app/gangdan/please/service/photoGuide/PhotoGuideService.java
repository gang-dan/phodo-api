package app.gangdan.please.service.photoGuide;

import app.gangdan.please.domain.hashtag.Hashtag;
import app.gangdan.please.domain.member.Member;
import app.gangdan.please.domain.member.MemberRepository;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoGuide.PhotoGuideRepository;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import app.gangdan.please.domain.photoSpot.PhotoSpotRepository;
import app.gangdan.please.dto.photoGuide.request.PhotoGuideRequestDtoV2;
import app.gangdan.please.dto.photoGuide.request.PhotoGuideSegRequestDtoV2;
import app.gangdan.please.global.exception.BadRequestException;
import app.gangdan.please.global.exception.MemberTokenNotFoundException;
import app.gangdan.please.service.google.GooglePlaceService;
import app.gangdan.please.service.photoSpot.PhotoSpotService;
import app.gangdan.please.vo.photoGuide.PhotoGuideSegVo;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.exif.GpsDirectory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhotoGuideService {

    private final GooglePlaceService googlePlaceService;
    private final PhotoSpotService photoSpotService;
    private final PhotoGuideRepository photoGuideRepository;
    private final PhotoSpotRepository photoSpotRepository;
    private final MemberRepository memberRepository;

    /**
     * 포토 가이드 생성
     */
    public PhotoGuide createPhotoGuide(Long memberId, MultipartFile requestImage) throws IOException, ImageProcessingException {

        PhotoSpot photoSpot;

        // image metadata -> 위도, 경도 추출
        byte[] bytes = requestImage.getBytes();
        Metadata metadata = ImageMetadataReader.readMetadata(new ByteArrayInputStream(bytes));

        // Gps 디렉토리에서 위도, 경도 추출Long memberId, MultipartFile requestImage
        GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        Double imageLatitude = 37.5;
        Double imageLongitude = 126.9;

        // if(Double.parseDouble(String.valueOf(directory.getGeoLocation().getLatitude())) != null){}

        String photoSpotName = googlePlaceService.getPlaceName(imageLatitude, imageLongitude);

        if(photoSpotRepository.findByName(photoSpotName) != null){
            photoSpot = photoSpotService.create(imageLatitude, imageLongitude); // 등록되지 않은 photoSpot -> 생성

        } else {
            photoSpot = photoSpotRepository.findByName(googlePlaceService.getPlaceName(imageLatitude, imageLongitude)); // 등록된 photoSpot -> select
        }

        if(photoSpot == null){
            photoSpot = photoSpotRepository.findByName("용산 아이파크몰");
        }

        // photoGuide 생성
        PhotoGuide photoGuide = PhotoGuide.create(photoSpot, findMemberOrThrow(memberId));
        photoGuideRepository.save(photoGuide);

        return photoGuide;
    }


    /**
     * 포토 가이드 생성 v2
     */
    public PhotoGuide createPhotoGuideV2(PhotoGuideRequestDtoV2 req, Long memberId) throws IOException, ImageProcessingException {

        if (memberId != req.getMemberId()) {
            throw new BadRequestException("가이드를 생성할 권한이 없습니다.");
        }

        PhotoSpot photoSpot;
        // image metadata -> 위도, 경도 추출
//        byte[] bytes = requestImage.getBytes();
//        Metadata metadata = ImageMetadataReader.readMetadata(new ByteArrayInputStream(bytes));
//
//        // Gps 디렉토리에서 위도, 경도 추출Long memberId, MultipartFile requestImage
//        GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
//
//        Double imageLatitude = 37.5;
//        Double imageLongitude = 126.9;

        // if(Double.parseDouble(String.valueOf(directory.getGeoLocation().getLatitude())) != null){}

        String photoSpotName = googlePlaceService.getPlaceName(req.getLatitude(), req.getLongitude());

        if(photoSpotRepository.findByName(photoSpotName) != null){
            photoSpot = photoSpotService.create(req.getLatitude(), req.getLongitude()); // 등록되지 않은 photoSpot -> 생성

        } else {
            photoSpot = photoSpotRepository.findByName(googlePlaceService.getPlaceName(req.getLatitude(), req.getLongitude())); // 등록된 photoSpot -> select
        }

        if(photoSpot == null){
            photoSpot = photoSpotRepository.findByName(req.getPhotoSpotName());
        }

        // photoGuide 생성
        PhotoGuide photoGuide = PhotoGuide.create(photoSpot, findMemberOrThrow(req.getMemberId()));
        photoGuideRepository.save(photoGuide);

        return photoGuide;
    }


    /**
     * yolov5 api 호출
     */
    public PhotoGuideSegVo segment(MultipartFile requestImage) {
//
//        // 1. 호출
//        // Colab API 호출 URL
//        String apiUrl = "https://colab.research.google.com/api/seg";
//
//        // Colab API 호출 요청 설정
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
//
//        // Colab API 호출
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
//
//
        // Colab API 응답 반환
        // return ResponseEntity.ok(response.getBody());


        // 2. output s3에 저장


        return null;
    }

    /**
     * 포토 가이드 생성 - 최종 외곽선 적용
     */
    public PhotoGuide createSegment(Long memberId, Long photoGuideId, String guideLine, String hashtags) throws IOException, ImageProcessingException {

        PhotoGuide photoGuide = findPhotoGuideOrThrow(photoGuideId);

        photoGuide.updateGuideLine(guideLine);

        // hashtag 추가
        String[] hashtagNameList = hashtags.split(",");
        for (String hashtagName : hashtagNameList) {
            Hashtag.create(photoGuide, hashtagName);
        }

        return photoGuide;
    }

    /**
     * 포토 가이드 생성 - 최종 외곽선 적용 v2
     */
    public PhotoGuide createSegmentV2(Long memberId, PhotoGuideSegRequestDtoV2 req) {

        // photoSpot 생성
        PhotoSpot photoSpot = PhotoSpot.create(req.getLatitude(), req.getLongitude(), req.getPhotoSpotName());
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BadRequestException("해당 멤버가 존재하지 않습니다."));

        // photoGuide 생성
        PhotoGuide photoGuide = PhotoGuide.createV2(req, photoSpot, member);

        //


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

    /**
     * 포토 가이드 둘러보기
     */
    public List<PhotoGuide> getAllPhotoGuide() {

        Pageable pageable = PageRequest.of(0, 30);
        return photoGuideRepository.findAll(pageable).getContent();
    }


    private Member findMemberOrThrow(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new BadRequestException("존재하지 않는 멤버입니다.");
        });
    }

    private PhotoGuide findPhotoGuideOrThrow(Long photoGuideId){
        return photoGuideRepository.findById(photoGuideId).orElseThrow(() -> {
            throw new BadRequestException("존재하지 않는 포토 가이드 입니다.");
        });
    }

}
