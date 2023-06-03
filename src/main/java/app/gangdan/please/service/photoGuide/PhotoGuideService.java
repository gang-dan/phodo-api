package app.gangdan.please.service.photoGuide;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoGuide.PhotoGuideRepository;
import app.gangdan.please.domain.photoSpot.PhotoSpotRepository;
import app.gangdan.please.dto.google.GooglePlacesResponse;
import app.gangdan.please.service.google.GooglePlaceService;
import app.gangdan.please.service.photoSpot.PhotoSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoGuideService {

    GooglePlaceService googlePlaceService;
    PhotoSpotService photoSpotService;
    PhotoGuideRepository photoGuideRepository;
    PhotoSpotRepository photoSpotRepository;

    public PhotoGuide register(Long memberId, MultipartFile requestImage, double latitude, double latitude1, String hashtags) {

        // 등록된 photoSpot인지 확인
        String photoSpotName = googlePlaceService.getPlaceName(latitude, latitude);
        if(photoSpotRepository.findByName(photoSpotName) != null){

            // 등록되지 않은 photoSpot -> 생성부터
            //photoSpotService.register()
        }

        // 이미 등록된 photoSpot -> PhotoSpot 가져와서 가이드 추가 (연관관계)
       // PhotoGuide photoGuide = PhotoGuide.create()




        return null;
    }
}
