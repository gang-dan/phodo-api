package app.gangdan.phodoapi.dto.photoSpot.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


@Data
@ApiModel(value = "위치에 따른 포토스팟 리스트, 등록된 가이드 개수 요청 객체")
public class PhotoSpotListRequestDto {

    @ApiModelProperty(value = "위도", example = "KITCHEN", required = true)
    private double latitude;

    private double longitude;

    private Long radius;

}
