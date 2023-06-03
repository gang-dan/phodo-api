package app.gangdan.please.domain.photoGuide;

import app.gangdan.please.domain.BaseEntity;
import app.gangdan.please.domain.hashtag.Hashtag;
import app.gangdan.please.domain.image.guide.GuideImage;
import app.gangdan.please.domain.image.original.OriginalImage;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<Hashtag> hashtagList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "photoGuide", fetch = FetchType.LAZY)
    private List<OriginalImage> originalImageList = new ArrayList<>();

    //public static PhotoGuide

}
