package app.gangdan.please.dto.photoGuide.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel(value = "포토 가이드 등록 요청 객체")
public class PhotoGuideRequestDto {

    @ApiModelProperty(value = "위도", example = "5.5", required = true)
    private double latitude;

    @ApiModelProperty(value = "경도", example = "5.5", required = true)
    private double longitude;

    @ApiModelProperty(value = "해시태그 목록", example = "웅장한, 재밌는, 필수 (콤마로 구분할게요~)")
    private String hashtags;


}
