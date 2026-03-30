package kz.main.app.service;

import kz.main.app.entity.Course;
import kz.main.app.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoursesService {

    List<Course> getALlCourses();

    Course findById(int courseId);
}
