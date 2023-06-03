package app.gangdan.please.domain.photoSpot;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photo_spot")
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PhotoSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoSpotId;

    private String photoSpotName;

    private Double longitude;

    private Double latitude;

    @Builder.Default
    @OneToMany(mappedBy = "photoSpot", fetch = FetchType.LAZY)
    private List<PhotoGuide> photoGuideList = new ArrayList<>();

    public static PhotoSpot create(Double latitude, Double longitude, String photoSpotName) {

        final PhotoSpot photoSpot = PhotoSpot.builder()
                .photoSpotName(photoSpotName)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        return photoSpot;
    }

}
