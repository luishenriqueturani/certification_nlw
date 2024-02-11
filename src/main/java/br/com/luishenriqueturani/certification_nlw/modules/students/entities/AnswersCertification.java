package br.com.luishenriqueturani.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "answers_certification_students")
public class AnswersCertification {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "certification_id")
  private UUID certificationId;

  @ManyToOne
  @JoinColumn(name = "certification_id", insertable = false, updatable = false)
  @JsonBackReference
  private CertificationStudent certificationStudent;

  @Column(name = "student_id")
  private UUID studentId;

  @ManyToOne
  @JoinColumn(name = "student_id", insertable = false, updatable = false)
  private Student student;

  @Column(name = "question_id")
  private UUID questionId;

  @Column(name = "answer_id")
  private UUID answerId;

  @Column
  private boolean isCorrect;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
