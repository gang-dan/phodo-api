package app.gangdan.please.api;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import app.gangdan.please.dto.photoGuide.request.PhotoGuideRequestDto;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideCreateResponseDto;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideDetailResponseDto;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideResponseDto;
import app.gangdan.please.dto.photoSpot.response.PhotoSpotListResponseDto;
import app.gangdan.please.global.resolver.RequestMemberId;
import app.gangdan.please.service.photoGuide.PhotoGuideService;
import app.gangdan.please.service.photoSpot.PhotoSpotService;
import com.drew.imaging.ImageProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Tag(name = "photoGuide", description = "포토 가이드 API")
@RequiredArgsConstructor
@RequestMapping("/api/guide")
public class PhotoGuideController {

    private final PhotoGuideService photoGuideService;

    @Tag(name = "photoGuide")
    @ApiOperation(value = "포토 가이드 등록 API")
    @PostMapping("")
    public ResponseEntity<PhotoGuideCreateResponseDto> create(@RequestPart("requestImage") MultipartFile requestImage,
                                                                @Validated @RequestBody PhotoGuideRequestDto req,
                                                                @ApiIgnore @RequestMemberId Long memberId) throws IOException, ImageProcessingException {

        final Long photoGuideId = photoGuideService.create(memberId, requestImage, req.getLatitude(), req.getLatitude(), req.getHashtags(), req.getPhotoGuideName()).getPhotoGuideId();
        return new ResponseEntity<>(PhotoGuideCreateResponseDto.create(photoGuideId), HttpStatus.CREATED);
    }

    @Tag(name = "photoGuide")
    @GetMapping("/list/{photoSpotId}")
    @ApiOperation(value = "포토 스팟에 등록된 포토 가이드 목록 조회 api", notes = "포토 가이드 보러가기 -> 목록")
    public ResponseEntity<List<PhotoGuideResponseDto>> getSpotList(@PathVariable("photoSpotId") Long photoSpotId) {

        List<PhotoGuideResponseDto> result = new ArrayList<>();
        result = photoGuideService.getPhotoGuideList(photoSpotId).stream().map(PhotoGuideResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Tag(name = "photoGuide")
    @GetMapping("/{photoGuideId}")
    @ApiOperation(value = "포토 가이드 상세 조회 api")
    public ResponseEntity<PhotoGuideDetailResponseDto> getPhotoGuide(@PathVariable("photoGuideId") Long photoGuideId) {

        PhotoGuide photoGuide = photoGuideService.getPhotoGuide(photoGuideId);
        return ResponseEntity.ok(PhotoGuideDetailResponseDto.from(photoGuide));
    }

}

