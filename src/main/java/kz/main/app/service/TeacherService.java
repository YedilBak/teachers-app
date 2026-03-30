package kz.main.app.service;

import kz.main.app.entity.Teacher;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    List<Teacher> getAllTeachersByIdAscending();

    List<Teacher> getAllTeachersByWord(String word);
    
    Teacher getTeacherById(int id);

    void deleteById(int id);

    void addTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    @Nullable Object getTeachersByGpaMore(Double gpa);

    @Nullable Object getTeachersByGpaAndFullName(Double gpa, String fullName);

    @Nullable Object getTeachersByGpaSort();

    Page<Teacher> findAllPagination(Pageable pageable);

    Page<Teacher> findByGpaGreaterThan(double gpa, Pageable pageable);
}
