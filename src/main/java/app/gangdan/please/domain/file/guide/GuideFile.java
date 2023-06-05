package app.gangdan.please.domain.file.guide;


import app.gangdan.please.domain.file.File;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"photoGuide"})
@DiscriminatorValue("photoGuide")
@Entity
public class GuideFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_guide_id", nullable = false)
    private PhotoGuide photoGuide;

    protected GuideFile(){}

    public GuideFile(PhotoGuide photoGuide, String fileUrl){
        super(fileUrl);
        this.photoGuide = photoGuide;
    }
}
