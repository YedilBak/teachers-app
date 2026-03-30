package kz.main.app.service.impl;

import kz.main.app.entity.Teacher;
import kz.main.app.repository.CustomTeacherRepository;
import kz.main.app.repository.TeacherRepository;
import kz.main.app.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MAIN")
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CustomTeacherRepository customTeacherRepository;

    @Override
    public List<Teacher> getAllTeachersByIdAscending() {
        return teacherRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Teacher> getAllTeachersByWord(String word) {
        return teacherRepository.getTeacherList(word);
    }

    @Override
    public Teacher getTeacherById(int id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public @Nullable Object getTeachersByGpaMore(Double gpa) {
        return customTeacherRepository.getTeachersByGpaMore(gpa);
    }

    @Override
    public @Nullable Object getTeachersByGpaAndFullName(Double gpa, String fullName) {
        return customTeacherRepository.getTeachersByGpaAndFullName(gpa, fullName);
    }

    @Override
    public @Nullable Object getTeachersByGpaSort() {
        return customTeacherRepository.getTeachersByGpaSort();
    }

    @Override
    public Page<Teacher> findAllPagination(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Page<Teacher> findByGpaGreaterThan(double gpa, Pageable pageable) {
        return teacherRepository.findByGpaGreaterThan(gpa, pageable);
    }
}
