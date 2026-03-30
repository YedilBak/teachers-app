package kz.main.app.service.impl;

import kz.main.app.entity.Course;
import kz.main.app.entity.Teacher;
import kz.main.app.repository.CoursesRepository;
import kz.main.app.repository.TeacherRepository;
import kz.main.app.service.CoursesService;
import kz.main.app.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;

    @Override
    public List<Course> getALlCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Course findById(int courseId) {
        return coursesRepository.findById(courseId).get();
    }
}
