package br.com.luishenriqueturani.certification_nlw.modules.questions.repositories;

import br.com.luishenriqueturani.certification_nlw.modules.questions.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IQuestion extends JpaRepository<Question, UUID> {

  List<Question> findByTechnology(String technology);
}
