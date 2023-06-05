package app.gangdan.please.dto.photoGuide.response;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "포토 가이드 상세 조회 응답 객체")
public class PhotoGuideDetailResponseDto {

    @ApiModelProperty(value = "포토 가이드 id")
    private Long photoGuideId;

    @ApiModelProperty(value = "멤버 id")
    private Long memberId;

    @ApiModelProperty(value = "포토 가이드 적용된 이미지")
    private String guideImage;

    @ApiModelProperty(value = "포토 가이드 원본 이미지")
    private String originalImage;

    @ApiModelProperty(value = "포토 가이드 마스크 이미지")
    private String maskImage;

    @ApiModelProperty(value = "외곽선 json")
    private String guideLine;

    @ApiModelProperty(value = "위도")
    private Double latitude;

    @ApiModelProperty(value = "경도")
    private Double longitude;

    @ApiModelProperty(value = "포토 스팟 이름")
    private String photoSpotName;

    @ApiModelProperty(value = "해시태그 리스트")
    private List<String> hashtags;

    @ApiModelProperty(value = "좋아요 개수")
    private Long likeNum;


    public static PhotoGuideDetailResponseDto from(PhotoGuide photoGuide){

        return new PhotoGuideDetailResponseDto.PhotoGuideDetailResponseDtoBuilder()
                .photoGuideId(photoGuide.getPhotoGuideId())
                .guideImage(photoGuide.getGuideImageList().get(0).getImageUrl())
                .build();
    }


}
