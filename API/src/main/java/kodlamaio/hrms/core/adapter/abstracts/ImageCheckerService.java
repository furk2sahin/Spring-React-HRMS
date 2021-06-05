package kodlamaio.hrms.core.adapter.abstracts;

import org.springframework.web.multipart.MultipartFile;

public interface ImageCheckerService {
    int detectPersonCount(MultipartFile multipartFile);
    int detectModerationLabels(MultipartFile multipartFile);
}
