package cadastrocursos.config;

import cadastrocursos.domain.*;
import cadastrocursos.enums.StatusCurso;
import cadastrocursos.repository.CursoRepository;
import cadastrocursos.repository.PerfilRepository;
import cadastrocursos.repository.PessoaRepository;
import cadastrocursos.repository.UsuarioRepository;
import cadastrocursos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;


@Configuration
@Profile("desenvolvimento")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public void run(String... args) throws Exception {
        Endereco endereco = new Endereco(null, "77024686", "Quadra 102 sul", "Qi 1", 12, "Plano diretor sul","Palmas", "TO");

        Pessoa pessoa1 = new Pessoa(null, "Carlos Eduardo", "524145", "53698741241", "63999999999", "54125411", "carlo@gmail", LocalDate.now(), "SSP");
        Pessoa pessoa2 = new Pessoa(null, "Carlos Eduardo", "524145", "53698741242", "63999999999", "54125411" , "carlo@gmail", LocalDate.now(), "SSP");
        Pessoa pessoa3 = new Pessoa(null, "Carlos Eduardo", "524145", "53698741243", "63999999999", "54125411" , "carlo@gmail", LocalDate.now(), "SSP");
        Pessoa pessoa4 = new Pessoa(null, "Carlos Eduardo", "524145", "53698741244", "63999999999", "54125411" , "carlo@gmail", LocalDate.now(), "SSP");
        pessoa1.setEndereco(endereco);
        pessoa2.setEndereco(endereco);
        pessoa3.setEndereco(endereco);
        pessoa4.setEndereco(endereco);
        pessoaRepository.saveAll(Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4));


        Perfil perfil1 = new Perfil(null, "ROLE_ADMIN");
        Perfil perfil2 = new Perfil(null, "ROLE_USER");
        perfilRepository.saveAll(Arrays.asList(perfil1, perfil2));

        Usuario usuario1 = new Usuario(null, "ragir", "$2a$10$O322Z0Oru9vtqVqOfNGLCeOULhLuGK5CN1BB/kcjwoR1Pcy2wyv4W", false);
        Usuario usuario2 = new Usuario(null, "admin", "$2a$10$W6o7gFaxjjCUpqbNbm/Kieu8fKpbZucPrRAe8Yv1Ivo0R.iDDnhUK", true);
        usuario1.getPerfis().add(perfil2);
        usuario2.getPerfis().add(perfil1);
        usuario2.getPerfis().add(perfil2);

        usuario1.setPessoa(pessoa1);
        usuario2.setPessoa(pessoa2);
        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));


        Curso curso = new Curso(null, "Curso teste", LocalDate.now(), LocalDate.now(), 200.20, 30, StatusCurso.INSCRICOES_ABERTAS);
        HorarioAula horarioAula1 = new HorarioAula(null, LocalDate.now(), new Date(), new Date(), new Date(), new Date());
        curso.getAlunos().add(pessoa1);
        curso.getAlunos().add(pessoa2);

        curso.getInstrutor().add(pessoa3);
        curso.getInstrutor().add(pessoa4);

        curso.setHorarioAula(horarioAula1);

        cursoRepository.save(curso);

    }
}
