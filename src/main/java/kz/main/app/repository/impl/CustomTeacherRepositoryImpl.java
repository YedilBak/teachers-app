package kz.main.app.repository.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.main.app.entity.Teacher;
import kz.main.app.repository.CustomTeacherRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomTeacherRepositoryImpl implements CustomTeacherRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Teacher> getTeachersByGpaMore(Double gpa) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);

        Predicate predicate = cb.greaterThan(root.get("gpa"), gpa);

        query.select(root).where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Teacher> getTeachersByGpaAndFullName(Double gpa, String fullName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);

        List<Predicate> predicates = new ArrayList<>();

        if(gpa!=null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("gpa"), gpa));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        if(fullName!=null && !fullName.isEmpty()){
            predicates.add(criteriaBuilder.like(root.get("fullName"), fullName));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Teacher> getTeachersByGpaSort() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);

        query.select(root).orderBy(cb.desc(root.get("gpa")));

        return entityManager.createQuery(query).getResultList();
    }
}
