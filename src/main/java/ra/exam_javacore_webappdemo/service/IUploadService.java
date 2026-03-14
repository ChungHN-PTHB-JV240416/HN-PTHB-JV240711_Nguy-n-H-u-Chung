package ra.exam_javacore_webappdemo.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface IUploadService {
    String uploadImage(MultipartFile file) throws IOException;
}