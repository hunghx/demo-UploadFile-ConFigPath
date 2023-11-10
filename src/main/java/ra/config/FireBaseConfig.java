package ra.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class FireBaseConfig {
    private final String serviceAccountKeyPath = "C:\\Users\\hung1\\OneDrive\\Desktop\\demo-UploadFile-ConFigPath\\src\\main\\resources\\service_account.json";

    @Bean
    public Storage storage() throws IOException {;
        InputStream serviceAccount = Files.newInputStream(Paths.get(serviceAccountKeyPath));
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }

}
