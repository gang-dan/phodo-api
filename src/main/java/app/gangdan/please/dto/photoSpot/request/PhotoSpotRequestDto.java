package app.gangdan.please.dto.photoSpot.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "포토 스팟 상세 조회 요청 객체")
public class PhotoSpotRequestDto {

    @ApiModelProperty(value = "위도", example = "5.5", required = true)
    private double latitude;

    @ApiModelProperty(value = "경도", example = "5.5", required = true)
    private double longitude;
}
