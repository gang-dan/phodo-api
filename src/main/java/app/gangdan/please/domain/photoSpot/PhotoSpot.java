package app.gangdan.please.domain.photoSpot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private double longitude;

    private double latitude;

}
