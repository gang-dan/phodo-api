package app.gangdan.please.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class S3Config {

    private String accessKey = "AKIAV4XWM7IFRL2RGIF4";
    private String secretKey = "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";
    private String region = "ap-northeast-2";

    @Bean
    @Primary
    public BasicAWSCredentials awsCredentialsProvider(){
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return basicAWSCredentials;
    }

    @Bean
    public AmazonS3 amazonS3(){
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsProvider()))
                .build();

        return amazonS3;
    }
}
