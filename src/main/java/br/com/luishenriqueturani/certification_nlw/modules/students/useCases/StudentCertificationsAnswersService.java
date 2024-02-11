package br.com.luishenriqueturani.certification_nlw.modules.students.useCases;

import br.com.luishenriqueturani.certification_nlw.modules.questions.entities.Question;
import br.com.luishenriqueturani.certification_nlw.modules.questions.repositories.IQuestion;
import br.com.luishenriqueturani.certification_nlw.modules.students.dto.StudentCertificationAnswer;
import br.com.luishenriqueturani.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.luishenriqueturani.certification_nlw.modules.students.entities.AnswersCertification;
import br.com.luishenriqueturani.certification_nlw.modules.students.entities.CertificationStudent;
import br.com.luishenriqueturani.certification_nlw.modules.students.entities.Student;
import br.com.luishenriqueturani.certification_nlw.modules.students.repositories.ICertificationStudent;
import br.com.luishenriqueturani.certification_nlw.modules.students.repositories.IStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationsAnswersService {

  @Autowired
  private IStudent studentRepository;

  @Autowired
  private IQuestion questionRepository;

  @Autowired
  private ICertificationStudent certificationStudentRepository;

  @Autowired
  private VerifyHasCertificationService verifyHasCertificationService;

  public CertificationStudent execute(StudentCertificationAnswer sca) throws Exception {

    boolean hasCertification = this.verifyHasCertificationService.execute( new VerifyHasCertificationDTO(
        sca.getEmail(), sca.getTechnology()
    ) );

    if(hasCertification) throw new Exception("Já possui certificação");

    Optional<Student> student = studentRepository.findByEmail( sca.getEmail() );

    UUID studentId;
    if(student.isEmpty()) {
      Student studentCreated = Student.builder().email(sca.getEmail() ).build();
      studentRepository.save(studentCreated);
      studentId = studentCreated.getId();
    }else{
      studentId = student.get().getId();
    }

    List<Question> questions = questionRepository.findByTechnology(sca.getTechnology() );

    List<AnswersCertification> answersCertifications = new ArrayList<>();

    AtomicInteger correctAnswers = new AtomicInteger(0);

    sca.getQuestionAnswers().forEach(questionAnswer -> {
      Question questionFiltered = questions.stream().filter(
          question -> question.getId().equals( questionAnswer.getQuestionId() )
      ).findFirst().get();

      var findCorrectAlternative = questionFiltered.getAlternatives().stream().filter( alternative -> alternative.isCorrect() ).findFirst().get();

      questionAnswer.setCorrect( findCorrectAlternative.getId().equals(questionAnswer.getAlternativeId()) );

      if(questionAnswer.isCorrect()) correctAnswers.getAndIncrement();

      answersCertifications.add(AnswersCertification
          .builder()
          .answerId(questionAnswer.getAlternativeId())
          .questionId(questionAnswer.getQuestionId())
          .isCorrect(questionAnswer.isCorrect())
          .build()
      );

    });


    CertificationStudent certificationStudent = CertificationStudent
        .builder()
        .technology(sca.getTechnology())
        .studentId(studentId)
        .grate(correctAnswers.get())
        .build();

    answersCertifications.stream().forEach( answersCertification -> {
      answersCertification.setCertificationId(certificationStudent.getId());
      answersCertification.setCertificationStudent(certificationStudent);
    } );

    certificationStudent.setAnswersCertification(answersCertifications);

    return certificationStudentRepository.save(certificationStudent);
  }
}
