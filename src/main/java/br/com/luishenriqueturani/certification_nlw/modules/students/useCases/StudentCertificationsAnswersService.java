package br.com.luishenriqueturani.certification_nlw.modules.students.useCases;

import br.com.luishenriqueturani.certification_nlw.modules.questions.entities.Question;
import br.com.luishenriqueturani.certification_nlw.modules.questions.repositories.IQuestion;
import br.com.luishenriqueturani.certification_nlw.modules.students.dto.StudentCertificationAnswer;
import br.com.luishenriqueturani.certification_nlw.modules.students.entities.Student;
import br.com.luishenriqueturani.certification_nlw.modules.students.repositories.IStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCertificationsAnswersService {

  @Autowired
  private IStudent studentRepository;

  private IQuestion questionRepository;

  public StudentCertificationAnswer execute(StudentCertificationAnswer sca) throws Exception {

    Optional<Student> student = studentRepository.findByEmail( sca.getEmail() );

    if(student.isEmpty()) throw new Exception("E-mail inválido");

    List<Question> questions = questionRepository.findByTechnology(sca.getTechnology() );

    sca.getQuestionAnswers().forEach(questionAnswer -> {
      Question questionFiltered = questions.stream().filter(
          question -> question.getId().equals( questionAnswer.getQuestionId() )
      ).findFirst().get();

      var findCorrectAlternative = questionFiltered.getAlternatives().stream().filter( alternative -> alternative.isCorrect() ).findFirst().get();

      questionAnswer.setCorrect( findCorrectAlternative.getId().equals(questionAnswer.getAlternativeId()) );

    });

    return sca;
  }
}
