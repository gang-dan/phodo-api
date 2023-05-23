package app.gangdan.phodoapi.domain.photoSpot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PhotoSpotCustomRepositoryImpl implements PhotoSpotCustomRepository {

    @Override
    public List<PhotoSpot> getSpotList(double latitude, double longitude, Long radius) {
        return null;
    }
}
