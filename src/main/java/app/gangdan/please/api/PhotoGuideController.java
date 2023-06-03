package app.gangdan.please.api;

import app.gangdan.please.dto.photoGuide.request.PhotoGuideRequestDto;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideResponseDto;
import app.gangdan.please.global.resolver.RequestMemberId;
import app.gangdan.please.service.photoGuide.PhotoGuideService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "photoGuide", description = "포토 가이드 API")
@RequiredArgsConstructor
@RequestMapping("/api/guide")
public class PhotoGuideController {

    private final PhotoGuideService photoGuideService;

    @Tag(name = "photoGuide")
    @ApiOperation(value = "포토가이드 등록 API")
    @PostMapping("")
    public ResponseEntity<?> register(@RequestPart("requestImage") MultipartFile requestImage,
                                      @Validated @RequestBody PhotoGuideRequestDto req,
                                      @ApiIgnore @RequestMemberId Long memberId) throws IOException {


        //final Long photoGuideId = photoGuideService.register(memberId, requestImage, req.getLatitude(), req.getLatitude(), req.getHashtags());


        //return new ResponseEntity<>(PhotoGuideResponseDto.create(photoGuideId), HttpStatus.CREATED);


        return null;
    }
}
















