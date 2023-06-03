package app.gangdan.please.api;

import app.gangdan.please.dto.test.HelloResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "hello", description = "테스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Tag(name = "hello")
    @ApiOperation(value = "test!!")
    @GetMapping("")
    public ResponseEntity<?> testHello(){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "hello")
    @ApiOperation(value = "test!!")
    @GetMapping("/v2")
    public ResponseEntity<HelloResponseDto> testHelloV2(@RequestParam("text") String text){

        return ResponseEntity.ok(HelloResponseDto.from(text));
    }
}
