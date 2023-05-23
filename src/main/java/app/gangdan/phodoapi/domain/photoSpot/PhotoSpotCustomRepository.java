package app.gangdan.phodoapi.domain.photoSpot;


import java.util.List;

public interface PhotoSpotCustomRepository {

    List<PhotoSpot> getSpotList(double latitude, double longitude, Long radius);

}
