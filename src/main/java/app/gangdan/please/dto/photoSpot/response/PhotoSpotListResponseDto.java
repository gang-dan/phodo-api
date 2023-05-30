package app.gangdan.please.dto.photoSpot.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoSpotListResponseDto {

    @ApiModelProperty(value = "포토스팟 id", example = "1")
    private Long photoSpotId;

    @ApiModelProperty(value = "등록된 포토가이드 개수", example = "5")
    private Long photoGuideNum;

    public static PhotoSpotListResponseDto from(Long photoSpotId, Long photoGuideNum){

        return new PhotoSpotListResponseDtoBuilder()
                .photoSpotId(photoSpotId)
                .photoGuideNum(photoGuideNum)
                .build();
    }

}
