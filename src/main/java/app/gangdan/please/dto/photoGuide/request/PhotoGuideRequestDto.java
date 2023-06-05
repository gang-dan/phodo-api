package app.gangdan.please.dto.photoGuide.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel(value = "포토 가이드 등록 요청 객체")
public class PhotoGuideRequestDto {

    @ApiModelProperty(value = "포토 가이드 이름", example = "이렇게 찍으면 무조건 인생샷")
    private String photoGuideName;

    @ApiModelProperty(value = "위도", example = "5.5", required = true)
    private Double latitude;

    @ApiModelProperty(value = "경도", example = "5.5", required = true)
    private Double longitude;

}
