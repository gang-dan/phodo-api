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

import java.io.IOException;

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

}
