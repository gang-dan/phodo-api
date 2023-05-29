package app.gangdan.please.domain.photoGuide;

import app.gangdan.please.domain.BaseEntity;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "photo_guide")
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PhotoGuide extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoGuideId;

    private String PhotoGuideName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_spot_id")
    private PhotoSpot photoSpot;

}
