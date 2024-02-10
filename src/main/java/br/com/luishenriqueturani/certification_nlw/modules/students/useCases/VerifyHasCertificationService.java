package br.com.luishenriqueturani.certification_nlw.modules.students.useCases;

import br.com.luishenriqueturani.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.luishenriqueturani.certification_nlw.modules.students.entities.CertificationStudent;
import br.com.luishenriqueturani.certification_nlw.modules.students.repositories.ICertificationStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerifyHasCertificationService {

  @Autowired
  private ICertificationStudent repository;

  public boolean execute(VerifyHasCertificationDTO vhc){

    List<CertificationStudent> csList = this.repository.findByStudentEmailAndTechnology(vhc.getEmail(), vhc.getTechnology());

    return !csList.isEmpty();
  }
}
