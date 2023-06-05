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
import app.gangdan.please.vo.photoGuide.PhotoGuideSegVo;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.exif.GpsDirectory;
import lombok.RequiredArgsConstructor;
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
import java.io.IOException;
import java.util.Collection;
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
    public PhotoGuide createPhotoGuide(Long memberId, MultipartFile requestImage, Double latitude, Double longitude) throws IOException, ImageProcessingException {

        String photoSpotName = googlePlaceService.getPlaceName(latitude, latitude);
        PhotoSpot photoSpot;

        // image metadata -> 위도, 경도 추출
        byte[] bytes = requestImage.getBytes();
        Metadata metadata = ImageMetadataReader.readMetadata(new ByteArrayInputStream(bytes));

        // Gps 디렉토리에서 위도, 경도 추출
        GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

//        double imageLatitude = (double) 0L;
//        double imageLongitude = (double) 0L;
//
//        if (Double.parseDouble(String.valueOf(directory.getGeoLocation().getLatitude())) != null){
//            imageLatitude = Double.parseDouble(String.valueOf(directory.getGeoLocation().getLatitude()));
//        }
//
//        if (directory.getGeoLocation().getLatitude() != null){
//            imageLongitude = Double.parseDouble(String.valueOf(directory.getGeoLocation().getLatitude()));
//        }

        Double imageLatitude = 30.0;
        Double imageLongitude = 30.0;

//        if (directory.getGeoLocation().getLatitude() != null) {
//            imageLatitude = Double.parseDouble(String.valueOf(directory.getGeoLocation().getLatitude()));
//            imageLongitude = Double.parseDouble(String.valueOf(directory.getGeoLocation().getLongitude()));
//        }
//

//
//        imageLatitude = Double.parseDouble(String.valueOf(directory.getGeoLocation().getLatitude()));
//        Double imageLongitude = Double.parseDouble(String.valueOf(directory.getGeoLocation().getLongitude()));

//        if (imageLatitude == null){
//            imageLatitude = 30.0;
//        }
//
//        if (imageLongitude == null){
//            imageLongitude = 30.0;
//        }


        if(photoSpotRepository.findByName(photoSpotName) != null){
            photoSpot = photoSpotService.create(imageLatitude, imageLongitude); // 등록되지 않은 photoSpot -> 생성

        } else {
            photoSpot = photoSpotRepository.findByName(googlePlaceService.getPlaceName(latitude, longitude)); // 등록된 photoSpot -> select
        }

        // photoGuide 생성
        PhotoGuide photoGuide = PhotoGuide.create(photoSpot, findMemberOrThrow(memberId));
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

        Pageable pageable = PageRequest.of(0, 18);
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
