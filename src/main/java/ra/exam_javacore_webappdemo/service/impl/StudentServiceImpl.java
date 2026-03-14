package ra.exam_javacore_webappdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exam_javacore_webappdemo.dto.request.StudentRequestDTO;
import ra.exam_javacore_webappdemo.dto.response.StudentResponseDTO;
import ra.exam_javacore_webappdemo.model.Student;
import ra.exam_javacore_webappdemo.repository.IStudentRepository;
import ra.exam_javacore_webappdemo.service.IStudentService;
import ra.exam_javacore_webappdemo.service.IUploadService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IUploadService uploadService;

    @Override
    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(StudentRequestDTO requestDTO) {
        Student student = new Student();
        student.setStudentName(requestDTO.getStudentName());
        student.setSex(requestDTO.isSex());
        student.setBirthday(requestDTO.getBirthday());
        student.setAddress(requestDTO.getAddress());
        student.setPhoneNumber(requestDTO.getPhoneNumber());

        if (requestDTO.getImage() != null && !requestDTO.getImage().isEmpty()) {
            try {
                String imageUrl = uploadService.uploadImage(requestDTO.getImage());
                student.setImageUrl(imageUrl);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        studentRepository.save(student);
    }

    @Override
    public StudentResponseDTO findById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return mapToResponseDTO(student);
    }

    @Override
    public void update(Integer id, StudentRequestDTO requestDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        student.setStudentName(requestDTO.getStudentName());
        student.setSex(requestDTO.isSex());
        student.setBirthday(requestDTO.getBirthday());
        student.setAddress(requestDTO.getAddress());
        student.setPhoneNumber(requestDTO.getPhoneNumber());

        if (requestDTO.getImage() != null && !requestDTO.getImage().isEmpty()) {
            try {
                String imageUrl = uploadService.uploadImage(requestDTO.getImage());
                student.setImageUrl(imageUrl);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        studentRepository.save(student);
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponseDTO> searchByName(String name) {
        return studentRepository.findByStudentNameContainingIgnoreCase(name).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private StudentResponseDTO mapToResponseDTO(Student student) {
        return StudentResponseDTO.builder()
                .studentId(student.getStudentId())
                .studentName(student.getStudentName())
                .sex(student.isSex())
                .birthday(student.getBirthday())
                .address(student.getAddress())
                .phoneNumber(student.getPhoneNumber())
                .imageUrl(student.getImageUrl())
                .build();
    }
}