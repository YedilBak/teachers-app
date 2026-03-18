package kz.main.app.repository;

import kz.main.app.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomTeacherRepository {

    List<Teacher> getTeachersByGpaMore(Double gpa);

    List<Teacher> getTeachersByGpaAndFullName(Double gpa, String fullName);

    List<Teacher> getTeachersByGpaSort();
}
