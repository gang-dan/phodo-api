package app.gangdan.please.dto.photoGuide.response;

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

    public static PhotoGuideCreateResponseDto create(Long photoGuideId) {
        return PhotoGuideCreateResponseDto.builder()
                .photoGuideId(photoGuideId)
                .build();
    }


}
