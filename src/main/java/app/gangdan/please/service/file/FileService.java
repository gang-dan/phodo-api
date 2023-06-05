package app.gangdan.please.service.file;

import app.gangdan.please.domain.file.FileRepository;
import app.gangdan.please.domain.file.guide.GuideFile;
import app.gangdan.please.domain.file.guide.GuideFileRepository;
import app.gangdan.please.domain.photoGuide.PhotoGuide;
import app.gangdan.please.domain.photoGuide.PhotoGuideRepository;
import app.gangdan.please.global.exception.BadRequestException;
import app.gangdan.please.s3.S3Uploader;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class FileService {

    private final AmazonS3 amazonS3;
    private final PhotoGuideRepository photoGuideRepository;
    private final GuideFileRepository guideFileRepository;
    private final S3Uploader s3Uploader;


    /**
     * photoGuide 외곽선 json 파일 업로드
     */
    public GuideFile saveGuideJsonFile(MultipartFile guideJsonFile, Long photoGuideId) throws IOException {

        PhotoGuide photoGuide = findPhotoGuideOrThrow(photoGuideId);
        return guideFileRepository.save(new GuideFile(photoGuide, s3Uploader.s3UploadGuideFile(photoGuide, guideJsonFile)));
    }

//
//    /**
//     * 공지사항 첨부파일 리스트 업데이트
//     */
//    public List<NoticeFile> updateNoticeFiles(List<MultipartFile> noticeFiles, long noticeId) throws IOException {
//
//        Notice notice = findNoticeOrThrow(noticeId);
////        if(notice.getMember().getMemberId() != memberId){
////            throw new CustomException("공지사항을 수정할 권한이 없습니다.");
////        }
//
//        List<Long> fileIdList = noticeFileRepository.findByNoticeId(notice.getNoticeId());
//        if(!fileIdList.isEmpty()){
//            for(Long fileId : fileIdList){
//                File file = fileRepository.findById(fileId).orElseThrow(() -> new NoSuchIdException("요청하신 파일은 존재하지 않습니다."));
//                file.updateIsDeleted();
//            }
//        }
//
//        List<String> fileUrlList = s3Uploader.s3UploadOfNoticeFiles(notice, noticeFiles);
//        List<NoticeFile> noticeFileList = new ArrayList<>();
//        if(!fileUrlList.isEmpty()){
//            for(String fileUrl : fileUrlList){
//                NoticeFile noticeFile = noticeFileRepository.save(new NoticeFile(notice, fileUrl));
//                noticeFileList.add(noticeFile);
//            }
//        }
//        return noticeFileList;
//    }
//
//
//    /**
//     * 공지사항 첨부파일 리스트 삭제
//     */
//    public void deleteNoticeFiles(Long noticeId, DeleteRequestDto req) {
//
//        for(Long id : req.getIdList()){
//            noticeFileRepository.deleteById(id);
//        }
//
//    }


    private PhotoGuide findPhotoGuideOrThrow(Long photoGuideId){
        return photoGuideRepository.findById(photoGuideId).orElseThrow(() -> {
            throw new BadRequestException("요청하신 포토가이드가 존재하지 않습니다.");
        });
    }

}
