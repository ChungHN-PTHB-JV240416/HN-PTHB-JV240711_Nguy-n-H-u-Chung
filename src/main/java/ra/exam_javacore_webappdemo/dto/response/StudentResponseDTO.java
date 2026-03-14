package ra.exam_javacore_webappdemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {

    private Integer studentId;

    private String studentName;

    private boolean sex;

    private LocalDate birthday;

    private String address;

    private String phoneNumber;

    private String imageUrl;
}
