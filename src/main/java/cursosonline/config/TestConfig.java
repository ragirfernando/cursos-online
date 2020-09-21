package cursosonline.config;

import cursosonline.domain.Curso;
import cursosonline.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Date;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {

        Curso curso = new Curso(null, "Curso teste", new Date(), new Date(), 200.20, 30);
        cursoRepository.save(curso);

    }
}
