package br.com.luishenriqueturani.certification_nlw.seed;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateSeed {

  private final JdbcTemplate jdbcTemplate;

  public CreateSeed(DataSource dataSource){
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public static void main(String[] args) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://192.168.1.34:5432/nlw");
    dataSource.setUsername("casaos");
    dataSource.setPassword("casaos");

    CreateSeed createSeed = new CreateSeed(dataSource);

    createSeed.run(args);
  }

  public void run(String... args){
    executeFile("src/main/resources/create.sql");
  }

  private void executeFile(String filepath){
    try {
      String sqlScript = new String(Files.readAllBytes(Paths.get(filepath)));

      jdbcTemplate.execute(sqlScript);

      System.out.println("Finalizado com sucesso");

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
