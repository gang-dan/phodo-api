package app.gangdan.please.vo.photoSpot;

import app.gangdan.please.domain.hashtag.Hashtag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PhotoSpotResponseVo {

    private String photoSpotImage;

    private String photoSpotName;

    private Long photoGuideNum;

    private Double myDistance;

    private List<Hashtag> hashtags;
}
