package app.gangdan.please.service.image;

import app.gangdan.please.domain.image.original.OriginalImage;
import app.gangdan.please.domain.image.original.OriginalImageRepository;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoSpot.PhotoSpot;
import app.gangdan.please.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Transactional
@RequiredArgsConstructor
@Service
public class ImageService {

    private final S3Uploader s3Uploader;

    private final OriginalImageRepository originalImageRepository;

    /**
     * 포토 가이드 original 이미지 저장
     */
    public OriginalImage saveOriginalImage(PhotoGuide photoGuide, MultipartFile imageFile) throws IOException {

        //S3 이미지 업로드
        String imageUrl = s3Uploader.s3UploadOriginalImage(photoGuide, imageFile);

        //originalImage 저장
        OriginalImage originalImage = new OriginalImage(photoGuide, imageUrl);
        return originalImageRepository.save(originalImage);
    }

    /**
     * 포토 가이드 original 이미지 저장 v2
     */
    public void saveOriginalImageV2(PhotoGuide photoGuide, String base64) {

        try {
            // Base64 디코딩
            byte[] imageBytes = Base64.getDecoder().decode(base64);

            // 바이트 배열을 File 객체로 변환
            File imageFile = createImageFileFromBytes(imageBytes, String.valueOf(photoGuide.getPhotoGuideId()));

            // s3 이미지 업로드
            String imageUrl = s3Uploader.s3UploadOriginalImageV2(photoGuide, imageFile);

            //originalImage 저장
            OriginalImage originalImage = new OriginalImage(photoGuide, imageUrl);
            originalImageRepository.save(originalImage);

            System.out.println("File 객체로 변환되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("파일 객체로 변환 중 오류가 발생했습니다.");
        }

    }

    private static File createImageFileFromBytes(byte[] imageBytes, String fileName) throws IOException {
        File imageFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(imageFile);
             ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = bais.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
        return imageFile;
    }



}
