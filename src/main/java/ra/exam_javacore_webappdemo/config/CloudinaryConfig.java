package ra.exam_javacore_webappdemo.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dtrnyjb3z",
                "api_key", "214328375931477",
                "api_secret", "d7AaiHyAiX21IgmqvDGjkxDjqaY"
        ));
    }
}