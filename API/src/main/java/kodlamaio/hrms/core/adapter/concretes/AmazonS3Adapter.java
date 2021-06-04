package kodlamaio.hrms.core.adapter.concretes;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import kodlamaio.hrms.core.adapter.abstracts.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class AmazonS3Adapter implements FileService {

    private final AmazonS3 amazonS3;

    @Value("${aws.bucket-name}")
    private String bucketName;

    @Autowired
    public AmazonS3Adapter(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        File file = convertMultiPartFileToFile(multipartFile);
        String fileName = System.currentTimeMillis() + "_" + originalFilename;

        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file);
        amazonS3.putObject(request);

        try {
            Files.delete(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public void deleteFile(String fileKey) {
        DeleteObjectRequest request = new DeleteObjectRequest(bucketName, fileKey);
        amazonS3.deleteObject(request);
    }

    @Override
    public String downloadFile(String fileKey) {
        GetObjectRequest request = new GetObjectRequest(bucketName, fileKey);
        S3Object object = amazonS3.getObject(request);

        return displayTextInputStream(object.getObjectContent());
    }

    private String displayTextInputStream(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = "";
        String readedObject = "";
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            readedObject += line;
        }
        return readedObject;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertedFile;
    }
}
