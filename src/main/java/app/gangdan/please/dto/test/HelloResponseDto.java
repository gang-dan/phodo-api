package app.gangdan.please.dto.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "테스트 응답 객체")
public class HelloResponseDto {

    @ApiModelProperty(value = "테스트 text", example = "sefsf")
    private String text;

    public static HelloResponseDto from(String text){

        return new HelloResponseDtoBuilder()
                .text(text)
                .build();
    }
}
