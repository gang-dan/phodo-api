package app.gangdan.phodoapi.api;

import app.gangdan.phodoapi.dto.photoSpot.request.PhotoSpotListRequestDto;
import app.gangdan.phodoapi.dto.photoSpot.response.PhotoSpotListResponseDto;
import app.gangdan.phodoapi.global.resolver.RequestMemberId;
import app.gangdan.phodoapi.service.photoSpot.PhotoSpotService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Tag(name = "spot", description = "photo spot API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/spot")
public class PhotoSpotController {

    private final PhotoSpotService photoSpotService;

    @Tag(name = "spot")
    @GetMapping("/{photoSpotId}")
    @ApiOperation(value = "위치에 따른 포토스팟 리스트, 등록된 가이드 개수 조회 api")
    public ResponseEntity<PhotoSpotListResponseDto> getSpotList(@ApiIgnore @RequestMemberId Long memberId,
                                                                @RequestBody @Valid PhotoSpotListRequestDto dto) {

        photoSpotService.getPhotoSpotList(dto.getLatitude(), dto.getLongitude(), dto.getRadius());
        return ResponseEntity.ok(PhotoSpotListResponseDto.from(1L, 1L));
    }

}
