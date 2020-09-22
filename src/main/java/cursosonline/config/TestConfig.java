package cursosonline.config;

import cursosonline.domain.Curso;
import cursosonline.domain.Endereco;
import cursosonline.domain.Pessoa;
import cursosonline.repository.CursoRepository;
import cursosonline.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Date;


@Configuration
@Profile("desenvolvimento")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public void run(String... args) throws Exception {
        Endereco endereco = new Endereco(null, "77024686", "Quadra 102 sul", "Qi 1", 12, "Plano diretor sul","Palmas", "TO");


        Pessoa pessoa1 = new Pessoa(null, "Carlos Eduardo", "524145", "53698741241", "63999999999", "54125411", new Date(), "SSP");
        Pessoa pessoa2 = new Pessoa(null, "Carlos Eduardo", "524145", "53698741241", "63999999999", "54125411", new Date(), "SSP");
        Pessoa pessoa3 = new Pessoa(null, "Carlos Eduardo", "524145", "53698741241", "63999999999", "54125411", new Date(), "SSP");
        Pessoa pessoa4 = new Pessoa(null, "Carlos Eduardo", "524145", "53698741241", "63999999999", "54125411", new Date(), "SSP");
        pessoa1.setEndereco(endereco);
        pessoa2.setEndereco(endereco);
        pessoa3.setEndereco(endereco);
        pessoa4.setEndereco(endereco);
        pessoaRepository.saveAll(Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4));

        Curso curso = new Curso(null, "Curso teste", new Date(), new Date(), 200.20, 30);
        curso.getAlunos().add(pessoa1);
        curso.getAlunos().add(pessoa2);

        curso.getInstrutor().add(pessoa3);
        curso.getInstrutor().add(pessoa4);

        cursoRepository.save(curso);

    }
}
