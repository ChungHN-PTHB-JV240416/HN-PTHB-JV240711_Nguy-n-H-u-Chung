package ra.exam_javacore_webappdemo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.exam_javacore_webappdemo.dto.request.StudentRequestDTO;
import ra.exam_javacore_webappdemo.dto.response.StudentResponseDTO;
import ra.exam_javacore_webappdemo.service.IStudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public String list(@RequestParam(required = false) String name, Model model) {
        List<StudentResponseDTO> students;
        if (name != null && !name.trim().isEmpty()) {
            students = studentService.searchByName(name);
        } else {
            students = studentService.findAll();
        }
        model.addAttribute("students", students);
        return "student/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new StudentRequestDTO());
        return "student/add";
    }

    @PostMapping("/add")
    public String addSubmit(@Valid @ModelAttribute("student") StudentRequestDTO student,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "student/add";
        }
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        StudentResponseDTO studentResponse = studentService.findById(id);
        StudentRequestDTO studentRequest = new StudentRequestDTO();
        studentRequest.setStudentName(studentResponse.getStudentName());
        studentRequest.setSex(studentResponse.isSex());
        studentRequest.setBirthday(studentResponse.getBirthday());
        studentRequest.setAddress(studentResponse.getAddress());
        studentRequest.setPhoneNumber(studentResponse.getPhoneNumber());

        model.addAttribute("student", studentRequest);
        model.addAttribute("currentImage", studentResponse.getImageUrl());
        model.addAttribute("studentId", id);
        return "student/edit";
    }

    @PostMapping("/edit/{id}")
    public String editSubmit(@PathVariable("id") Integer id,
                             @Valid @ModelAttribute("student") StudentRequestDTO student,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("studentId", id);
            return "student/edit";
        }
        studentService.update(id, student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}