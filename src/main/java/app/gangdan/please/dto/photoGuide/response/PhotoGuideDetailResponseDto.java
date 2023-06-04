package app.gangdan.please.dto.photoGuide.response;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "포토 가이드 생성 응답 객체")
public class PhotoGuideDetailResponseDto {

    @ApiModelProperty(value = "포토 가이드 id")
    private Long photoGuideId;

    @ApiModelProperty(value = "포토 가이드 적용된 이미지")
    private String guideImage;


    public static PhotoGuideDetailResponseDto from(PhotoGuide photoGuide){

        return new PhotoGuideDetailResponseDto.PhotoGuideDetailResponseDtoBuilder()
                .photoGuideId(photoGuide.getPhotoGuideId())
                .guideImage(photoGuide.getGuideImageList().get(0).getImageUrl())
                .build();
    }


}
