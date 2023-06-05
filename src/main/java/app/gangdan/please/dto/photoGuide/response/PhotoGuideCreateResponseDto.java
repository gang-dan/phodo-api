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
public class PhotoGuideCreateResponseDto {

    @ApiModelProperty(value = "포토 가이드 id")
    @NotNull
    private Long photoGuideId;

    @ApiModelProperty(value = "외곽선 json 파일")
    private String guideJsonFile;

    @ApiModelProperty(value = "이미지 세로")
    private Double height;

    @ApiModelProperty(value = "이미지 가로")
    private Double width;

    @ApiModelProperty(value = "포토 스팟 이름")
    private String photoSpotName;


    public static PhotoGuideCreateResponseDto create(PhotoGuide photoGuide, Double height, Double width) {
        return PhotoGuideCreateResponseDto.builder()
                .photoGuideId(photoGuide.getPhotoGuideId())
                .guideJsonFile(photoGuide.getGuideFileList().get(0).getFileUrl())
                .height(height)
                .width(width)
                .build();
    }


}
