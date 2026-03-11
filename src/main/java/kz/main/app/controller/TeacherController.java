package kz.main.app.controller;

import kz.main.app.entity.Teacher;
import kz.main.app.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherRepository teacherRepository;

    @GetMapping(value = "/")
    public String getMain(Model model){
        model.addAttribute("teachers", teacherRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/{id}")
    public String getTeacher(@PathVariable int id,
                             Model model){
        model.addAttribute("teacher", teacherRepository.findById(id).get());
        return "details";
    }

    @PostMapping(value = "/delete")
    public String deleteTeacher(@RequestParam int id){
        teacherRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping(value = "/add")
    public String addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addTeacherPage(){
        return "add-page";
    }

    @PostMapping(value = "/update")
    public String updateTeacher(Teacher teacher){
        teacherRepository.save(teacher);
        return "redirect:/";
    }


}
