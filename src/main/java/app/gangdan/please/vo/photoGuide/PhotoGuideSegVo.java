package app.gangdan.please.vo.photoGuide;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PhotoGuideSegVo {

    private Double height;

    private Double width;

    private MultipartFile guideJsonFile;

}
