package kodlamaio.hrms.core.adapter.abstracts;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile multipartFile);
    String downloadFile(String fileKey);
    void deleteFile(String fileKey);
}
