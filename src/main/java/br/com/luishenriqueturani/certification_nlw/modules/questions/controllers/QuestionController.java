package br.com.luishenriqueturani.certification_nlw.modules.questions.controllers;

import br.com.luishenriqueturani.certification_nlw.modules.questions.dto.AlternativeResult;
import br.com.luishenriqueturani.certification_nlw.modules.questions.dto.QuestionResult;
import br.com.luishenriqueturani.certification_nlw.modules.questions.entities.Alternatives;
import br.com.luishenriqueturani.certification_nlw.modules.questions.entities.Question;
import br.com.luishenriqueturani.certification_nlw.modules.questions.repositories.IQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
public class QuestionController {

  @Autowired
  private IQuestion repository;

  @GetMapping("/technology/{technology}")
  public List<QuestionResult> findByTechnology(@PathVariable String technology){
    List<Question> result = this.repository.findByTechnology(technology);

    return result.stream().map(question -> mapQuestionDTO(question)).collect(Collectors.toList());
  }

  public static QuestionResult mapQuestionDTO(Question question){
    return new QuestionResult(
        question.getId(),
        question.getTechnology(),
        question.getDescription(),
        question.getAlternatives().stream().map(alternatives -> mapAlternativeDTO(alternatives)).collect(Collectors.toList())
        );
  }

  public static AlternativeResult mapAlternativeDTO(Alternatives alternatives){
    return AlternativeResult.builder().id(alternatives.getId()).description(alternatives.getDescription()).build();
  }

}
