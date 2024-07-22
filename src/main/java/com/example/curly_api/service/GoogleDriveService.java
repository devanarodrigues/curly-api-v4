package com.example.curly_api.service;

import com.example.curly_api.config.ImageUploadResponse;
import com.example.curly_api.repository.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import jakarta.servlet.MultipartConfigElement;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
@AllArgsConstructor
public class GoogleDriveService {

    private final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACOUNT_KEY_PATH = getPathToGoogleCredentials();
    private UserRepository userRepository;

    // pegando credenciais
    private static String getPathToGoogleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "cred.json");
        return filePath.toString();
    }

    @Bean // definir tamanho da imagem
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize(DataSize.parse("40MB"));

        factory.setMaxRequestSize(DataSize.parse("40MB"));

        return factory.createMultipartConfig();

    }

    // serviço de upload
    public ImageUploadResponse uploadImageToDrive(File file, String userId, MultipartFile imgFile) {
        ImageUploadResponse res = new ImageUploadResponse();

        try {
            String folderId = "1qfrXmE44U_67WQ4zWyYVESfJPkNdDoN6";
            Drive drive = createDriveService();

            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();

            fileMetaData.setName(file.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));

            FileContent mediaContent = new FileContent("image/**", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute();

            String imageUrl = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
            System.out.print("IMAGE URL: " + imageUrl);
            file.delete();

            res.setStatus(200);
            res.setMessage("Image sucessfuly uploaded to drive ");
            res.setUrl(imageUrl);

            var entity = userRepository.findById(userId);
            if (entity.isPresent()) {
                var user = entity.get();

                if (!imgFile.isEmpty()) {
                    user.setFoto(imageUrl);
                    userRepository.save(user);
                }
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
            res.setStatus(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }


    private Drive createDriveService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .build();
    }
}
