package ra.service;

import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {
    private final String bucketName = "webapp-e7186.appspot.com"; // tên bucket
    private String pathUpload = "C:\\Users\\hung1\\OneDrive\\Desktop\\demo-UploadFile-ConFigPath\\src\\main\\webapp\\assets\\uploads\\";

    @Autowired
    private Storage storage;
    public String uploadFile(MultipartFile file){
        File fileUpload = new File(pathUpload);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
            String localFilePath  = pathUpload+File.separator + file.getOriginalFilename();
        // coppy file upload đén thư mục chỉ đinh
        try {
            FileCopyUtils.copy(file.getBytes(),new File(localFilePath));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uploadFileToFirebaseStorage(storage, localFilePath, bucketName);
    }
    private String  uploadFileToFirebaseStorage(Storage storage, String localFilePath, String bucketName)  {
        Path localPath = Paths.get(localFilePath);
        String fileName = localPath.getFileName().toString();

        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        // Thiết lập quyền truy cập công cộng
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
