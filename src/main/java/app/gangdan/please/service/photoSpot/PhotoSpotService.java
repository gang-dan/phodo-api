package app.gangdan.please.service.photoSpot;

import app.gangdan.please.domain.photoSpot.PhotoSpot;
import app.gangdan.please.domain.photoSpot.PhotoSpotRepository;
import app.gangdan.please.global.exception.BadRequestException;
import app.gangdan.please.service.google.GooglePlaceService;
import app.gangdan.please.service.image.ImageService;
import com.amazonaws.services.ec2.model.DescribeFpgaImagesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoSpotService {

    private final PhotoSpotRepository photoSpotRepository;
    private final GooglePlaceService googlePlaceService;
    private final ImageService imageService;


    public List<PhotoSpot> getPhotoSpotList(double latitude, double longitude, double radius) {

        return photoSpotRepository.getSpotList(latitude, longitude, radius);
    }

    public PhotoSpot getPhotoSpot(Long photoSpotId) {

        return photoSpotRepository.findById(photoSpotId)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 포토스팟 입니다."));
    }

    public PhotoSpot create(Double latitude, Double longitude){

        // PhotoSpot 생성
        return PhotoSpot.create(latitude, longitude, googlePlaceService.getPlaceName(latitude, longitude));
    }
}











