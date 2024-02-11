package br.com.luishenriqueturani.certification_nlw.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCertificationAnswer {

  private String email;
  private String technology;

  private List<QuestionAnswer> questionAnswers;
}
