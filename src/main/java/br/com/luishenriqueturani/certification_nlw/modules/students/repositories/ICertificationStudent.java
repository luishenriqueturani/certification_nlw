package br.com.luishenriqueturani.certification_nlw.modules.students.repositories;

import br.com.luishenriqueturani.certification_nlw.modules.students.entities.CertificationStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface ICertificationStudent extends JpaRepository<CertificationStudent, UUID> {

  @Query("SELECT c FROM certifications c INNER JOIN c.student std WHERE std.email = :email AND c.technology = :technology")
  List<CertificationStudent> findByStudentEmailAndTechnology(String email, String technology);

  @Query("SELECT c FROM certifications c ORDER BY c.grade DESC LIMIT 10")
  List<CertificationStudent> findTop10ByOrderGradeDesc();

}
