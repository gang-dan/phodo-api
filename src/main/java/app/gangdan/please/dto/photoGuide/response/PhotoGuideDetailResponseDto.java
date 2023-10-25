package app.gangdan.please.dto.photoGuide.response;

import app.gangdan.please.domain.hashtag.Hashtag;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "포토 가이드 상세 조회 응답 객체")
public class PhotoGuideDetailResponseDto {

    @ApiModelProperty(value = "포토 가이드 id")
    private Long photoGuideId;

    @ApiModelProperty(value = "멤버 id")
    private Long memberId;

    @ApiModelProperty(value = "포토 가이드 컨투어 이미지")
    private String contourImage;

    @ApiModelProperty(value = "포토 가이드 원본 이미지")
    private String originalImage;

    @ApiModelProperty(value = "포토 가이드 마스크 이미지")
    private String maskImage;

//    @ApiModelProperty(value = "가로")
//    private String width;
//
//    @ApiModelProperty(value = "세로")
//    private String height;

//    @ApiModelProperty(value = "외곽선 json")
//    private String guideLine;

    @ApiModelProperty(value = "위도")
    private Double latitude;

    @ApiModelProperty(value = "경도")
    private Double longitude;

    @ApiModelProperty(value = "포토 스팟 이름")
    private String photoSpotName;

    @ApiModelProperty(value = "해시태그 리스트")
    private List<String> hashtags;

    @ApiModelProperty(value = "좋아요 개수")
    private Long heartNum;


    public static PhotoGuideDetailResponseDto from(PhotoGuide photoGuide){

        return new PhotoGuideDetailResponseDtoBuilder()
                .photoGuideId(photoGuide.getPhotoGuideId())
                .memberId(photoGuide.getMember().getMemberId())
                .contourImage(photoGuide.getGuideImageList().get(0).getImageUrl())
                .originalImage(photoGuide.getOriginalImageList().get(0).getImageUrl())
                .maskImage(photoGuide.getMaskImageList().get(0).getImageUrl())
//                .guideLine(photoGuide.getGuideLine())
//                .width(photoGuide.getGuideImageList().get(0).getWidth())
//                .height(photoGuide.getGuideImageList().get(0).getHeight())
                .latitude(photoGuide.getPhotoSpot().getLatitude())
                .longitude(photoGuide.getPhotoSpot().getLongitude())
                .photoSpotName(photoGuide.getPhotoSpot().getPhotoSpotName())
                .hashtags(photoGuide.getHashtagList().stream().map(Hashtag::getHashtagName).collect(Collectors.toList()))
                .heartNum((long) photoGuide.getHeartList().size())
                .build();
    }


}
