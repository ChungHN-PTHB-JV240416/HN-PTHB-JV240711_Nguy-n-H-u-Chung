package ra.exam_javacore_webappdemo.service;

import ra.exam_javacore_webappdemo.dto.request.StudentRequestDTO;
import ra.exam_javacore_webappdemo.dto.response.StudentResponseDTO;

import java.util.List;

public interface IStudentService {
    List<StudentResponseDTO> findAll();
    void save(StudentRequestDTO requestDTO);
    StudentResponseDTO findById(Integer id);
    void update(Integer id, StudentRequestDTO requestDTO);
    void delete(Integer id);
    List<StudentResponseDTO> searchByName(String name);
}