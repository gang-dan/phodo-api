package app.gangdan.please.s3;


import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private String bucket = "phodo-bucket";
    private final AmazonS3 amazonS3;

//    /**
//     * 시공사 대표 이미지 업르도
//     */
//    public String s3UploadOfMemberImage(Member member, MultipartFile multipartFile) throws IOException {
//
//        //폴더 경로
//        String folderPath = "contractor";
//
//        //파일 이름
//        String frontName = String.valueOf(member.getMemberId());
//        String fileName = createFileName(frontName, multipartFile.getOriginalFilename());
//
//        return s3Upload(folderPath, fileName, multipartFile);
//    }
//
//    /**
//     * 시공사 사업 등록증 이미지 업로드
//     */
//    public String s3UploadOfCompanyRegisterImage(Member member, MultipartFile multipartFile) throws IOException {
//
//        //폴더 경로
//        String folderPath = "companyRegister";
//
//        //파일 이름
//        String frontName = String.valueOf(member.getMemberId());
//        String fileName = createFileName(frontName, multipartFile.getOriginalFilename());
//
//        return s3Upload(folderPath, fileName, multipartFile);
//    }
//
//    /**
//     * 공지사항 파일 리스트 업로드
//     */
//    public List<String> s3UploadOfNoticeFiles(Notice notice, List<MultipartFile> multipartFiles) throws IOException {
//
//        List<String> fileUrlList = new ArrayList<>();
//
//        if(!multipartFiles.isEmpty() && !multipartFiles.get(0).isEmpty()){
//            for(MultipartFile multipartFile : multipartFiles){
//                fileUrlList.add(s3UploadOfNoticeFile(notice, multipartFile));
//            }
//        }
//        return fileUrlList;
//    }
//
//
//    /**
//     * 공지사항 파일 단일 업로드
//     */
//    public String s3UploadOfNoticeFile(Notice notice, MultipartFile multipartFile) throws IOException {
//
//        //폴더 경로
//        String folderPath = "notice";
//
//        //파일 이름
//        String frontName = String.valueOf(notice.getNoticeId());
//        String fileName = createFileName(frontName, multipartFile.getOriginalFilename());
//
//        return s3Upload(folderPath, fileName, multipartFile);
//    }
//
//
//    /**
//     * S3 업로드
//     */
//    private String s3Upload(String folderPath, String fileNm, MultipartFile multipartFile) throws IOException {
//
//        File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
//                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
//
//        //S3에 저장될 위치 + 저장파일명
//        String storeKey = folderPath + "/" + fileNm;
//
//        //s3로 업로드
//        String imageUrl = putS3(uploadFile, storeKey);
//
//        //File 로 전환되면서 로컬에 생성된 파일을 제거
//        removeNewFile(uploadFile);
//
//        return imageUrl;
//    }
//
//    /**
//     * S3 이미지 삭제
//     */
//    public void delete(Image image){
//        try{
//
//            String imageUrl = image.getImageUrl();
//            String storeKey = imageUrl.replace("https://"+bucket+".s3.ap-northeast-2.amazonaws.com/", "");
//
//            System.out.println("imageUrl: " + imageUrl);
//            System.out.println("storeKey: "+ storeKey);
//
//            amazonS3.deleteObject(new DeleteObjectRequest(bucket, storeKey));
//
//        }catch(Exception e) {
//            log.error("delete file error"+e.getMessage());
//        }
//    }
//
//    //S3 업로드
//    private String putS3(File uploadFile, String storeKey) {
//        amazonS3.putObject(new PutObjectRequest(bucket, storeKey, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
//        return amazonS3.getUrl(bucket,storeKey).toString();
//    }
//
//    // 로컬에 저장된 이미지 지우기
//    private void removeNewFile(File targetFile) {
//        if (targetFile.delete()) {
//            log.info("파일이 삭제되었습니다.");
//        } else {
//            log.info("파일이 삭제되지 못했습니다.");
//        }
//    }
//
//
//    // 로컬에 파일 업로드 하기
//    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
//
//        //파일 이름
//        String originalFilename = multipartFile.getOriginalFilename();
//
//        //파일 저장 이름
//        String storeFileName = UUID.randomUUID().toString()+"_"+originalFilename;
//
//        File convertFile = new File(System.getProperty("user.dir") + "/" + storeFileName);
//
//        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
//            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
//                fos.write(multipartFile.getBytes());
//            }
//            return Optional.of(convertFile);
//        }
//
//        return Optional.empty();
//    }
//
//    // file upload 추가
//    public List<String> uploadFile(List<MultipartFile> multipartFile, String frontName) {
//        List<String> fileNames = new ArrayList<>();
//
//        multipartFile.forEach(file -> {
//            String fileName = createFileName(frontName, file.getOriginalFilename());
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(file.getSize());
//            objectMetadata.setContentType(file.getContentType());
//
//            try (InputStream inputStream = file.getInputStream()) {
//                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));
//            } catch (IOException e) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
//            }
//
//            fileNames.add(fileName);
//        });
//
//        return fileNames;
//    }
//
//    private String createFileName(String frontName, String originalFileName) {
//
//        String uuid = UUID.randomUUID().toString();
//
//        return frontName + "_" + uuid + "_" + originalFileName;
//    }

}
