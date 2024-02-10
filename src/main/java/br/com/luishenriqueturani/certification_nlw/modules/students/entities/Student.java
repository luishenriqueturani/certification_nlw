package br.com.luishenriqueturani.certification_nlw.modules.students.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String email;

  /*
  * OneToOne - um desta classe para um de outra
  * OneToMany - um desta classe para muitos de outra
  * ManyToOne - muitos dessa classe para um de outra
  * ManyToMany - muitos dessa classe para muitos de outra
  * */

  @OneToMany(mappedBy = "student")
  private List<CertificationStudent> certificationsStudent;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
