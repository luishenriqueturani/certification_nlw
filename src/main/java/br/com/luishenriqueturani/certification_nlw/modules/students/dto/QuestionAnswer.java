package br.com.luishenriqueturani.certification_nlw.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionAnswer {
  private UUID questionId;
  private UUID alternativeId;
  private boolean isCorrect;
}
