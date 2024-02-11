package br.com.luishenriqueturani.certification_nlw.modules.certifications.controllers;

import br.com.luishenriqueturani.certification_nlw.modules.certifications.useCases.TopTenRanking;
import br.com.luishenriqueturani.certification_nlw.modules.students.entities.CertificationStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class Ranking {

  @Autowired
  private TopTenRanking topTenRanking;

  @GetMapping("/top10")
  public List<CertificationStudent> topTen(){

    return this.topTenRanking.execute();

  }
}
