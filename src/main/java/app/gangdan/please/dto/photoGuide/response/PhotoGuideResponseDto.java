package app.gangdan.please.dto.photoGuide.response;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import app.gangdan.please.dto.photoSpot.response.PhotoSpotListResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "포토 가이드 리스트 조회 응답 객체")
public class PhotoGuideResponseDto {

    @ApiModelProperty(value = "포토 가이드 id")
    @NotNull
    private Long photoGuideId;

    @ApiModelProperty(value = "포토 가이드 이미지")
    private String photoGuideImage;

    public static PhotoGuideResponseDto from(PhotoGuide photoGuide){

        return new PhotoGuideResponseDtoBuilder()
                .photoGuideId(photoGuide.getPhotoGuideId())
                .photoGuideImage(photoGuide.getOriginalImageList().get(0).getImageUrl())
                .build();
    }

}
