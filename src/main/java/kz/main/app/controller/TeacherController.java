package kz.main.app.controller;

import kz.main.app.entity.Course;
import kz.main.app.entity.Teacher;
import kz.main.app.repository.CompanyRepository;
import kz.main.app.service.CoursesService;
import kz.main.app.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final CompanyRepository companyRepository;

    @Autowired
    @Qualifier("MAIN")
    private TeacherService teacherService;
    private final CoursesService coursesService;

    @GetMapping(value = "/")
    public String getMain(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachersByIdAscending());
        return "index";
    }

    @GetMapping(value = "/poisk")
    public String getTeachers(Model model,
                              @RequestParam String search) {
        model.addAttribute("teachers", teacherService.getAllTeachersByWord(search));

        return "index";
    }

    @GetMapping(value = "/{id}")
    public String getTeacher(@PathVariable int id,
                             Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        List<Course> courses = coursesService.getALlCourses();
        courses.removeAll(teacher.getCourses());

        model.addAttribute("teacher", teacher);
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("courses", courses);

        return "details";
    }

    @PostMapping(value = "/delete")
    public String deleteTeacher(@RequestParam int id) {
        teacherService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping(value = "/add")
    public String addTeacher(Teacher teacher) {
        teacherService.addTeacher(teacher);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addTeacherPage(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("courses", coursesService.getALlCourses());
        return "add-page";
    }

    @PostMapping(value = "/update")
    public String updateTeacher(Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-course")
    public String deleteCourse(@RequestParam int course_id,
                               @RequestParam int teacher_id) {

        Teacher teacher = teacherService.getTeacherById(teacher_id);
        Course course = coursesService.findById(course_id);

        teacher.getCourses().remove(course);

        teacherService.updateTeacher(teacher);

        return "redirect:/" + teacher_id;

    }

    @PostMapping(value = "/add-course")
    public String addCourse(@RequestParam int course_id,
                            @RequestParam int teacher_id) {

        Teacher teacher = teacherService.getTeacherById(teacher_id);
        Course course = coursesService.findById(course_id);

        teacher.getCourses().add(course);

        teacherService.addTeacher(teacher);

        return "redirect:/" + teacher_id;

    }

    @GetMapping(value = "/gpa-more")
    public String getTeachersByGpaMore(@RequestParam Double gpa,
                                       Model model) {
        model.addAttribute("teachers", teacherService.getTeachersByGpaMore(gpa));

        return "index";
    }

    @GetMapping(value = "/gpa-and-name")
    public String getTeachersByGpaAndName(@RequestParam(required = false) Double gpa,
                                          @RequestParam(required = false) String fullName,
                                          Model model) {
        model.addAttribute("teachers", teacherService.getTeachersByGpaAndFullName(gpa, fullName));

        return "index";
    }

    @GetMapping(value = "/gpa-sort")
    public String getTeachersSortByGpa(Model model) {
        model.addAttribute("teachers", teacherService.getTeachersByGpaSort());

        return "index";
    }

    @GetMapping(value = "/pagination")
    public String getTeacherByPagination(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "6") int size,
                                         @RequestParam(defaultValue = "id") String param,
                                         @RequestParam(defaultValue = "asc") String direction,
                                         Model model) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(param).descending() : Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Teacher> teacherPage = teacherService.findAllPagination(pageable);

        model.addAttribute("teachers", teacherPage.getContent());

        return "index";
    }

    @GetMapping(value = "/pagination-by-gpa")
    public String getTeachersBePaginationByGpa(@RequestParam double gpa,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size,
                                               @RequestParam(defaultValue = "gpa") String param,
                                               @RequestParam(defaultValue = "asc") String direction,
                                               Model model) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(param).descending() : Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Teacher> teacherPage = teacherService.findByGpaGreaterThan(gpa, pageable);

        model.addAttribute("teachers", teacherPage.getContent());

        return "index";
    }


}
