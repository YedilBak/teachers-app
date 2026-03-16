package kz.main.app.repository;

import kz.main.app.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("SELECT teacher FROM Teacher teacher " +
            "WHERE teacher.fullName ilike concat('%', :search, '%')" +
            "OR teacher.company.name ilike concat('%', :search, '%')")
    List<Teacher> getTeacherList(String search);
}
