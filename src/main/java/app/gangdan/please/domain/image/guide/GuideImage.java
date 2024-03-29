package app.gangdan.please.domain.image.guide;

import app.gangdan.please.domain.image.Image;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"photoGuide"})
@DiscriminatorValue("photoGuide")
@Entity
public class GuideImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_guide_id", nullable = false)
    private PhotoGuide photoGuide;

    private String width;

    private String height;

    protected GuideImage(){}

    public GuideImage(PhotoGuide photoGuide, String imagerUrl){
        super(imagerUrl);
        this.photoGuide = photoGuide;
        photoGuide.getGuideImageList().add(this);
    }
}
