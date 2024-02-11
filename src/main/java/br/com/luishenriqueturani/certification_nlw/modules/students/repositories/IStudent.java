package br.com.luishenriqueturani.certification_nlw.modules.students.repositories;

import br.com.luishenriqueturani.certification_nlw.modules.students.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IStudent extends JpaRepository<Student, UUID> {
  Optional<Student> findByEmail(String email);
}
