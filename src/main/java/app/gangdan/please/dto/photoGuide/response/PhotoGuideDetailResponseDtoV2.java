package app.gangdan.please.dto.photoGuide.response;

import app.gangdan.please.domain.hashtag.Hashtag;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "포토 가이드 상세 조회 응답 객체 v2")
public class PhotoGuideDetailResponseDtoV2 {

    @ApiModelProperty(value = "포토 가이드 id")
    private Long photoGuideId;

    @ApiModelProperty(value = "멤버 id")
    private Long memberId;

    @ApiModelProperty(value = "포토 가이드 original 이미지")
    private String originalImage;

    @ApiModelProperty(value = "포토 가이드 contour 이미지")
    private String contourImage;

    @ApiModelProperty(value = "포토 가이드 contourTrans 이미지")
    private String contourTransImage;

    @ApiModelProperty(value = "포토 가이드 mask 이미지")
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
    private Integer heartNum;


    public static PhotoGuideDetailResponseDtoV2 from(PhotoGuide photoGuide){

        return new PhotoGuideDetailResponseDtoV2.PhotoGuideDetailResponseDtoV2Builder()
                .photoGuideId(photoGuide.getPhotoGuideId())
                .memberId(photoGuide.getMember().getMemberId())
                .originalImage(getOriginalImage(photoGuide))
                .contourImage(getContourImage(photoGuide))
                .contourTransImage(getContourTransImage(photoGuide))
                .maskImage(getMaskImage(photoGuide))
//                .guideLine(photoGuide.getGuideLine())
//                .width(photoGuide.getGuideImageList().get(0).getWidth())
//                .height(photoGuide.getGuideImageList().get(0).getHeight())
                .latitude(photoGuide.getPhotoSpot().getLatitude())
                .longitude(photoGuide.getPhotoSpot().getLongitude())
                .photoSpotName(photoGuide.getPhotoSpot().getPhotoSpotName())
                .hashtags(photoGuide.getHashtagList().stream().map(Hashtag::getHashtagName).collect(Collectors.toList()))
                .heartNum(photoGuide.getHeartList().size())
                .build();
    }

    private static String getOriginalImage(PhotoGuide photoGuide){

        if(photoGuide.getOriginalImageList().size() == 0){
            return "";
        }else {
            return photoGuide.getOriginalImageList().get(0).getImageUrl();
        }
    }

    private static String getContourImage(PhotoGuide photoGuide){

        if(photoGuide.getContourImageList().size() == 0){
            return "";
        }else {
            return photoGuide.getContourImageList().get(0).getImageUrl();
        }
    }

    private static String getContourTransImage(PhotoGuide photoGuide){

        if(photoGuide.getContourTransImageList().size() == 0){
            return "";
        }else {
            return photoGuide.getContourTransImageList().get(0).getImageUrl();
        }
    }

    private static String getMaskImage(PhotoGuide photoGuide){

        if(photoGuide.getMaskImageList().size() == 0){
            return "";
        }else {
            return photoGuide.getMaskImageList().get(0).getImageUrl();
        }
    }
}
