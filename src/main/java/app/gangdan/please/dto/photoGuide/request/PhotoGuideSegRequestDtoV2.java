package app.gangdan.please.dto.photoGuide.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "포토 가이드 segmentation 등록 요청 객체 v2")
public class PhotoGuideSegRequestDtoV2 {

    @ApiModelProperty(value = "포토 가이드 컨투어 이미지")
    private String contourImage;

    @ApiModelProperty(value = "포토 가이드 원본 이미지")
    private String originalImage;

    @ApiModelProperty(value = "포토 가이드 마스크 이미지")
    private String maskImage;

    @ApiModelProperty(value = "가로")
    private String width;

    @ApiModelProperty(value = "세로")
    private String height;

    @ApiModelProperty(value = "외곽선 json")
    private String guideLine;

    @ApiModelProperty(value = "위도")
    private Double latitude;

    @ApiModelProperty(value = "경도")
    private Double longitude;

    @ApiModelProperty(value = "포토 스팟 이름")
    private String photoSpotName;

    @ApiModelProperty(value = "해시태그 리스트", example = "유럽갬성, 웅장한 (콤마로 구분)")
    private List<String> hashtags;
}
