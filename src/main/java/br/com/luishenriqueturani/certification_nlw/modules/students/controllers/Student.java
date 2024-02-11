package br.com.luishenriqueturani.certification_nlw.modules.students.controllers;

import br.com.luishenriqueturani.certification_nlw.modules.students.dto.StudentCertificationAnswer;
import br.com.luishenriqueturani.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.luishenriqueturani.certification_nlw.modules.students.entities.CertificationStudent;
import br.com.luishenriqueturani.certification_nlw.modules.students.useCases.StudentCertificationsAnswersService;
import br.com.luishenriqueturani.certification_nlw.modules.students.useCases.VerifyHasCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class Student {

  @Autowired
  private VerifyHasCertificationService verifyHasCertificationService;

  @Autowired
  private StudentCertificationsAnswersService scas;

  @PostMapping("/verifyIfHasCertification")
  public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO vhc){

    if(verifyHasCertificationService.execute(vhc)) return "Pode";

    return "Não pode";
  }


  public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswer dto ) throws Exception {
    try {
      return ResponseEntity.ok().body(this.scas.execute( dto ));
    }catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
