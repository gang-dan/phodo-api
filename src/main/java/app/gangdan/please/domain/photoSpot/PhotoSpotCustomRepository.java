package app.gangdan.please.domain.photoSpot;


import java.util.List;

public interface PhotoSpotCustomRepository {

    List<PhotoSpot> getSpotList(double latitude, double longitude, double radius);

}
