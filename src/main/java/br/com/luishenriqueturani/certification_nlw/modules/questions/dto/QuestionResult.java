package br.com.luishenriqueturani.certification_nlw.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {


  private UUID id;
  private String technology;
  private String description;

  private List<AlternativeResult> alternativeResults;

}
