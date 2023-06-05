package app.gangdan.please.service.segmentation;

import app.gangdan.please.dto.segmentation.SegmentationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional
@RequiredArgsConstructor
public class SegmentationService {

    public String callSegmentation(String folderName, String fileName, String accessToken) {

        String apiEndpoint = "http://phododo-env.eba-vn2bdd4z.ap-northeast-2.elasticbeanstalk.com/colab/guide";  // Colab API의 엔드포인트 URL
        HttpHeaders headers = new HttpHeaders();
        // 필요한 경우에 헤더를 추가할 수 있습니다
        headers.add("Authorization", accessToken);

        HttpEntity<SegmentationRequestDto> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiEndpoint,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        return responseEntity.getBody();
    }

}
