package app.gangdan.please.dto.photoSpot.response;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"photoSpotId", "photoGuideNum", "latitude", "longitude"})
public class PhotoSpotListResponseDto {

    @ApiModelProperty(value = "포토 스팟 id", example = "1")
    private Long photoSpotId;

    @ApiModelProperty(value = "등록된 포토 가이드 개수", example = "5")
    private Long photoGuideNum;

    @ApiModelProperty(value = "위도", example = "5.5")
    private double latitude;

    @ApiModelProperty(value = "경도", example = "5.5")
    private double longitude;

    public static PhotoSpotListResponseDto from(PhotoSpot photoSpot){

        return new PhotoSpotListResponseDtoBuilder()
                .photoSpotId(photoSpot.getPhotoSpotId())
                .photoGuideNum((long) photoSpot.getPhotoGuideList().size())
                .longitude(photoSpot.getLongitude())
                .latitude(photoSpot.getLatitude())
                .build();
    }

}
