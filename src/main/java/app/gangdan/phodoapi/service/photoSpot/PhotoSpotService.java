package app.gangdan.phodoapi.service.photoSpot;

import app.gangdan.phodoapi.domain.photoSpot.PhotoSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoSpotService {

    private final PhotoSpotRepository photoSpotRepository;

    public void getPhotoSpotList(double latitude, double longitude, Long radius) {

        photoSpotRepository.getSpotList(latitude, longitude, radius);


    }
}
