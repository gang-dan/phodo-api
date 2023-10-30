package app.gangdan.please.api;

import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.dto.photoGuide.request.PhotoGuideRequestDto;
import app.gangdan.please.dto.photoGuide.request.PhotoGuideRequestDtoV2;
import app.gangdan.please.dto.photoGuide.request.PhotoGuideSegRequestDto;
import app.gangdan.please.dto.photoGuide.request.PhotoGuideSegRequestDtoV2;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideCreateResponseDto;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideDetailResponseDto;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideResponseDto;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideSegResponseDto;
import app.gangdan.please.global.resolver.RequestMemberId;
import app.gangdan.please.service.file.FileService;
import app.gangdan.please.service.image.ImageService;
import app.gangdan.please.service.photoGuide.PhotoGuideService;
import app.gangdan.please.vo.photoGuide.PhotoGuideSegVo;
import com.drew.imaging.ImageProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Tag(name = "photoGuide", description = "포토 가이드 API")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/guide")
public class PhotoGuideController {

    private final PhotoGuideService photoGuideService;
    private final FileService fileService;
    private final ImageService imageService;

    @Tag(name = "photoGuide")
    @ApiOperation(value = "포토 가이드 등록 api")
    @PostMapping("")
    @Deprecated
    public ResponseEntity<PhotoGuideCreateResponseDto> createPhotoGuide(@RequestPart("requestImage") MultipartFile requestImage,
                                                                        //@RequestPart("photoGuideRequestDto") PhotoGuideRequestDto req,
                                                                @ApiIgnore @RequestMemberId Long memberId) throws IOException {

        // PhotoGuide 생성
       // PhotoGuide photoGuide = photoGuideService.createPhotoGuide(memberId, requestImage);
       // imageService.saveOriginalImage(photoGuide, requestImage);

        // colab 접근 -> Yolo script 수행
        // PhotoGuideSegVo segVo = photoGuideService.segment(requestImage);
        // return new ResponseEntity<>(PhotoGuideCreateResponseDto.create(photoGuide, segVo.getHeight(), segVo.getWidth()), HttpStatus.CREATED);
        return null;
    }


    @Tag(name = "photoGuide")
    @ApiOperation(value = "포토 가이드 등록 리메이크 api - 토큰 필요")
    @PostMapping("/test")
    public ResponseEntity<PhotoGuideCreateResponseDto> createPhotoGuideTest(@RequestBody PhotoGuideRequestDtoV2 req,
                                                                            @ApiIgnore @RequestMemberId Long memberId) throws IOException, ImageProcessingException {

        log.info("길이확인:::: " + req.getOriginalImage());
        log.info("memberId ::: " + memberId);
        log.info("PhotoGuideRequestDtoV2: {}", req.toString());

        // PhotoGuide 생성
        PhotoGuide photoGuide = photoGuideService.createPhotoGuideV2(req, memberId);

        imageService.saveOriginalImageV2(photoGuide, req.getOriginalImage());
        imageService.saveContourImageV2(photoGuide, req.getContourImage());
        imageService.saveContourTransImageV2(photoGuide, req.getContourTransImage());
        imageService.saveMaskImageV2(photoGuide, req.getMaskImage());

        return ResponseEntity.ok(PhotoGuideCreateResponseDto.create(photoGuide));
    }


    @Tag(name = "photoGuide")
    @ApiOperation(value = "포토 가이드 등록 api - 최종 외곽선")
    @PostMapping("/{photoGuideId}")
    @Deprecated
    public ResponseEntity<PhotoGuideSegResponseDto> createSegment(@PathVariable("photoGuideId") Long photoGuideId,
                                                                  @Validated @RequestBody PhotoGuideSegRequestDto req,
                                                                  @ApiIgnore @RequestMemberId Long memberId) throws IOException {

        //photoGuideService.createSegment(memberId, photoGuideId, req.getGuideLine(), req.getHashtags());

        //fileService.saveGuideJsonFile(guideJsonFile, photoGuideId);

        return new ResponseEntity<>(PhotoGuideSegResponseDto.from(photoGuideId), HttpStatus.CREATED);
    }

    @Tag(name = "photoGuide")
    @ApiOperation(value = "포토 가이드 등록 api - 최종 외곽선 ver2")
    @PostMapping("/v2/{photoGuideId}")
    @Deprecated
    public ResponseEntity<PhotoGuideSegResponseDto> createSegmentV2(@Validated @RequestBody PhotoGuideSegRequestDtoV2 req,
                                                                  @ApiIgnore @RequestMemberId Long memberId) throws IOException {

        photoGuideService.createSegmentV2(memberId, req);

        //fileService.saveGuideJsonFile(guideJsonFile, photoGuideId);

        //return new ResponseEntity<>(PhotoGuideSegResponseDto.from(photoGuideId), HttpStatus.CREATED);
        return null;
    }

    @Tag(name = "photoGuide")
    @GetMapping("/all")
    @ApiOperation(value = "포토 가이드 둘러보기 api", notes = "최대 18개 리턴")
    public ResponseEntity<List<PhotoGuideResponseDto>> getAll() {

        List<PhotoGuideResponseDto> result = new ArrayList<>();
        result = photoGuideService.getAllPhotoGuide().stream().map(PhotoGuideResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
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
    @ApiOperation(value = "포토 가이드 상세 조회 api")   // TODO : 더미데이터 테스트
    @Deprecated
    public ResponseEntity<PhotoGuideDetailResponseDto> getPhotoGuide(@PathVariable("photoGuideId") Long photoGuideId) {

        PhotoGuide photoGuide = photoGuideService.getPhotoGuide(photoGuideId);
        return ResponseEntity.ok(PhotoGuideDetailResponseDto.from(photoGuide));
    }

    @Tag(name = "photoGuide")
    @GetMapping("/v2/{photoGuideId}")
    @ApiOperation(value = "포토 가이드 상세 조회 리메이크 api")   // TODO : 더미데이터 테스트
    public ResponseEntity<PhotoGuideDetailResponseDto> getPhotoGuideV2(@PathVariable("photoGuideId") Long photoGuideId) {

        PhotoGuide photoGuide = photoGuideService.getPhotoGuide(photoGuideId);
        return ResponseEntity.ok(PhotoGuideDetailResponseDto.from(photoGuide));
    }

}

