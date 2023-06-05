package app.gangdan.please.dto.photoGuide.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "포토 가이드 segmentation 등록 요청 객체")
public class PhotoGuideSegRequestDto {

    @ApiModelProperty(value = "포토 가이드 이름", example = "이렇게 찍으면 무조건 인생샷")
    private String photoGuideName;

    @ApiModelProperty(value = "해시태그 목록", example = "웅장한, 재밌는, 필수 (콤마로 구분할게요~)")
    private String hashtags;
}
