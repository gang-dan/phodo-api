package app.gangdan.please.dto.heart.response;

import app.gangdan.please.domain.heart.Heart;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.dto.photoGuide.response.PhotoGuideCreateResponseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeartCreateResponseDto {

    @ApiModelProperty(value = "좋아요 id")
    @NotNull
    private Long heartId;

    public static HeartCreateResponseDto create(Heart heart) {
        return HeartCreateResponseDto.builder()
                .heartId(heart.getHeartId())
                .build();
    }
}
