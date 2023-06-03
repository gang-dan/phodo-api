package app.gangdan.please.api;

import app.gangdan.please.domain.photoSpot.PhotoSpot;
import app.gangdan.please.dto.photoSpot.request.PhotoSpotListRequestDto;
import app.gangdan.please.dto.photoSpot.request.PhotoSpotRequestDto;
import app.gangdan.please.dto.photoSpot.response.PhotoSpotListResponseDto;
import app.gangdan.please.dto.photoSpot.response.PhotoSpotResponseDto;
import app.gangdan.please.service.hashtag.HashtagService;
import app.gangdan.please.service.photoSpot.PhotoSpotService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Tag(name = "photoSpot", description = "포토 스팟 API")
@RequiredArgsConstructor
@RequestMapping("/api/spots")
public class PhotoSpotController {

    private final PhotoSpotService photoSpotService;
    private final HashtagService hashtagService;

    @Tag(name = "photoSpot")
    @GetMapping("")
    @ApiOperation(value = "위치에 따른 포토 스팟 리스트, 등록된 가이드 개수 조회 api")
    public ResponseEntity<PhotoSpotListResponseDto> getSpotList(@RequestBody @Valid PhotoSpotListRequestDto dto) {

        photoSpotService.getPhotoSpotList(dto.getLatitude(), dto.getLongitude(), dto.getRadius());
        return ResponseEntity.ok(PhotoSpotListResponseDto.from(1L, 1L));
    }


    @Tag(name = "photoSpot")
    @GetMapping("/{photoSpotId}")
    @ApiOperation(value = "포토 스팟 상세 조회 api")
    public ResponseEntity<PhotoSpotResponseDto> getSpotList(@PathVariable("photoSpotId") Long photoSpotId,
                                                            @RequestParam("latitude") Double latitude,
                                                            @RequestParam("longitude") Double longitude) {

        PhotoSpot photoSpot = photoSpotService.getPhotoSpot(photoSpotId);
       // List<String> hashtags = hashtagService.getHashtags(photoSpotId).stream().map(Hashtag::getHashtagName).collect(Collectors.toList());
        return ResponseEntity.ok(PhotoSpotResponseDto.from(
                photoSpot, calculateDistance(latitude, longitude, photoSpot.getLatitude(), photoSpot.getLongitude())));
    }


    private static final double EARTH_RADIUS = 6371000;

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        // 위도와 경도를 라디안(radian) 단위로 변환
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // 두 지점의 위도 및 경도 차이 계산
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Haversine 공식 적용
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance;
    }

}
