package app.gangdan.please.api;

import app.gangdan.please.domain.heart.Heart;
import app.gangdan.please.dto.heart.response.HeartCreateResponseDto;
import app.gangdan.please.global.resolver.RequestMemberId;
import app.gangdan.please.service.heart.HeartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@Tag(name = "heart", description = "포토 가이드 좋아요 API")
@RequiredArgsConstructor
@RequestMapping("/api/heart")
public class HeartController {

    private final HeartService heartService;

    @Tag(name = "heart")
    @ApiOperation(value = "좋아요 생성 api")
    @PostMapping(value="/{photoGuideId}")
    public ResponseEntity<HeartCreateResponseDto> createHeart(@ApiIgnore @RequestMemberId Long memberId,
                                                                 @PathVariable("photoGuideId") Long photoGuideId){
        Heart heart = heartService.create(memberId, photoGuideId);
        return new ResponseEntity<>(HeartCreateResponseDto.create(heart), HttpStatus.CREATED);
    }

    @Tag(name = "heart")
    @ApiOperation(value = "좋아요 삭제 api")
    @DeleteMapping("/{heartId}")
    public ResponseEntity<?> deleteHeart(@ApiIgnore @RequestMemberId Long memberId,
                                            @PathVariable("heartId") Long heartId) {
        heartService.delete(memberId, heartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
