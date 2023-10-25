package app.gangdan.please.domain.image.contour;

import app.gangdan.please.domain.image.Image;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"photoGuide"})
@DiscriminatorValue("photoGuide")
@Entity
public class ContourImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_guide_id", nullable = false)
    private PhotoGuide photoGuide;

    protected ContourImage(){}

    public ContourImage(PhotoGuide photoGuide, String imagerUrl){
        super(imagerUrl);
        this.photoGuide = photoGuide;
        photoGuide.getContourImageList().add(this);
    }
}
