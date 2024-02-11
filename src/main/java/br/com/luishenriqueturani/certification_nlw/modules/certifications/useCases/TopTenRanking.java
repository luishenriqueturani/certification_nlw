package br.com.luishenriqueturani.certification_nlw.modules.certifications.useCases;

import br.com.luishenriqueturani.certification_nlw.modules.students.entities.CertificationStudent;
import br.com.luishenriqueturani.certification_nlw.modules.students.repositories.ICertificationStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopTenRanking {

  @Autowired
  private ICertificationStudent certificationStudentRepository;

  public List<CertificationStudent> execute(){
    return this.certificationStudentRepository.findTop10ByOrderGradeDesc();
  }
}
