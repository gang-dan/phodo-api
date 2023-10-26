package app.gangdan.please.dto.photoGuide.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "포토 가이드 등록 요청 객체 v2")
public class PhotoGuideRequestDtoV2 {

    private Long memberId;

    private String originalImage;

//    private byte[] contourImage;
//
//    private byte[] maskImage;
//
//    private byte[] contourTransImage;

    private List<String> tags;

    private Double latitude;

    private Double longitude;

    private String photoSpotName;

}
