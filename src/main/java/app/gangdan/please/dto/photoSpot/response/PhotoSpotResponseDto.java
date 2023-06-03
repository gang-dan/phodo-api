package app.gangdan.please.dto.photoSpot.response;

import app.gangdan.please.domain.hashtag.Hashtag;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoSpotResponseDto {

    @ApiModelProperty(value = "포토스팟 id", example = "2")
    private Long photoSpotId;

    @ApiModelProperty(value = "포토스팟 대표 이미지 url", example = "url")
    private String photoSpotImage;

    @ApiModelProperty(value = "포토스팟 이름", example = "평화의 광장")
    private String photoSpotName;

    @ApiModelProperty(value = "등록된 포토가이드 개수", example = "6")
    private Long photoGuideNum;

    @ApiModelProperty(value = "나와의 거리(단위 : m)", example = "150")
    private Double myDistance;

    @ApiModelProperty(value = "등록된 해시태그")
    private List<String> hashtags;

    public static PhotoSpotResponseDto from(PhotoSpot photoSpot, double myDistance){

        // TODO : 테스트 필요
        return new PhotoSpotResponseDtoBuilder()
                .photoSpotId(photoSpot.getPhotoSpotId())
                .photoSpotImage(photoSpot.getPhotoGuideList().get(0).getOriginalImageList().get(0).getImageUrl())
                .photoSpotName(photoSpot.getPhotoSpotName())
                .photoGuideNum((long) photoSpot.getPhotoGuideList().size())
                .myDistance(myDistance)
                .hashtags(photoSpot.getPhotoGuideList().stream().map(photoGuide -> photoGuide.getHashtagList().get(0).getHashtagName()).collect(Collectors.toList()))
                .build();
    }
}
