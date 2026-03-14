package ra.exam_javacore_webappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.exam_javacore_webappdemo.model.Student;
import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByStudentNameContainingIgnoreCase(String studentName);

}