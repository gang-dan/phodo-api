package app.gangdan.please.dto.photoSpot.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "위치에 따른 포토 스팟 리스트, 등록된 가이드 개수 요청 객체")
public class PhotoSpotListRequestDto {

    @ApiModelProperty(value = "위도", example = "5.5", required = true)
    private double latitude;

    @ApiModelProperty(value = "경도", example = "5.5", required = true)
    private double longitude;

    @ApiModelProperty(value = "반지름(단위 : m)", example = "20", required = true)
    private double radius;

}
