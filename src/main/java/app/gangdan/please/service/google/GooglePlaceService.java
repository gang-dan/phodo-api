package app.gangdan.please.service.google;

import app.gangdan.please.dto.google.GooglePlacesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GooglePlaceService {

    @Value("${map.api.key}")
    private String apiKey;

    public String getPlaceName(double latitude, double longitude) {
        String apiUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json" +
                "?input=" + latitude + "," + longitude +
                "&inputtype=textquery" +
                "&fields=name" +
                "&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        GooglePlacesResponse response = restTemplate.getForObject(apiUrl, GooglePlacesResponse.class);

        if (response != null && response.getCandidates() != null && response.getCandidates().size() > 0) {
            return response.getCandidates().get(0).getName();
        }

        return null;
    }
}
