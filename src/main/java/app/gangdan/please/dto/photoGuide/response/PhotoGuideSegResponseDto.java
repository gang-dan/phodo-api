package app.gangdan.please.dto.photoGuide.response;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "포토 가이드 segmentation 응답 객체")
public class PhotoGuideSegResponseDto {

    @ApiModelProperty(value = "포토 가이드 id")
    private Long photoGuideId;

    public static PhotoGuideSegResponseDto from(Long photoGuideId){

        return new PhotoGuideSegResponseDtoBuilder()
                .photoGuideId(photoGuideId)
                .build();
    }

}
