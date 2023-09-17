package app.gangdan.please.service.google;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.model.File;
import com.google.api.client.http.FileContent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

public class GoogleDriveService {

//    private static final String APPLICATION_NAME = "Your Application Name";
//    private static final String FOLDER_ID = "your-folder-id"; // 구글 드라이브 폴더 ID
//
//    private Drive driveService;
//
//    // driveService 초기화 로직 생략
//
//    public void uploadFileToDrive(MultipartFile multipartFile) throws IOException {
//        File fileMetadata = new File();
//        //fileMetadata.
//        //fileMetadata.setName(multipartFile.getOriginalFilename());
//        //fileMetadata.setParents(Collections.singletonList(FOLDER_ID));
//
//        java.io.File tempFile = convertMultipartFileToFile(multipartFile);
//        FileContent mediaContent = new FileContent(multipartFile.getContentType(), tempFile);
//
//        File uploadedFile = driveService.files().insert(fileMetadata)
//                .setFields("id")
//                .execute();
//
//        tempFile.delete();
//
//        System.out.println("File uploaded. File ID: " + uploadedFile.getId());
//    }
//
//    private java.io.File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
//        java.io.File convertedFile = new java.io.File(multipartFile.getOriginalFilename());
//        multipartFile.transferTo(convertedFile);
//        return convertedFile;
//    }
}


