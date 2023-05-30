package app.gangdan.please.domain.image.original;

import app.gangdan.please.domain.image.Image;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"photoGuide"})
@DiscriminatorValue("photoGuide")
@Entity
public class OriginalImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_guide_id", nullable = false)
    private PhotoGuide photoGuide;

    protected OriginalImage(){}

    public OriginalImage(PhotoGuide photoGuide, String imagerUrl){
        super(imagerUrl);
        this.photoGuide = photoGuide;
    }

}
