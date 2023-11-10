package ra.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FIreBase {
    public static void main(String[] args) {
        try {
            // Đường dẫn đến tệp JSON Service Account Key
            String serviceAccountKeyPath = "C:\\Users\\hung1\\OneDrive\\Desktop\\demo-UploadFile-ConFigPath\\src\\main\\webapp\\assets\\service_account.json";

            // Đường dẫn đến tệp cần upload
            String localFilePath = "C:\\Users\\hung1\\OneDrive\\Desktop\\demo-UploadFile-ConFigPath\\src\\main\\webapp\\assets\\uploads\\th (1).jpg";

            // Tên bucket Firebase Storage
            String bucketName = "webapp-e7186.appspot.com";

            // Khởi tạo Firebase Storage
            Storage storage = initializeFirebaseStorage(serviceAccountKeyPath);

            // Upload file
            uploadFileToFirebaseStorage(storage, localFilePath, bucketName);

            System.out.println("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Storage initializeFirebaseStorage(String serviceAccountKeyPath) throws IOException {;
        InputStream serviceAccount = Files.newInputStream(Paths.get(serviceAccountKeyPath));
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }

    private static void uploadFileToFirebaseStorage(Storage storage, String localFilePath, String bucketName) throws IOException {
        Path localPath = Paths.get(localFilePath);
        String fileName = localPath.getFileName().toString();

        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        storage.create(blobInfo, Files.readAllBytes(localPath));
    }
}
