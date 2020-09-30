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
@Profile("producao")
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

        Pessoa pessoa1 = new Pessoa(null, "Roberto Carlos", "11111111111", "63111111111", "11111111", "robertocarlos@gmail", LocalDate.now(), "SSP");
        Pessoa pessoa2 = new Pessoa(null, "Ana Maria", "22222222222", "63222222222", "22222222" , "ananmaria@gmail", LocalDate.now(), "SSP");
        Pessoa pessoa3 = new Pessoa(null, "Maria Eduarda", "33333333333", "63333333333", "33333333" , "maraiaeduarda@gmail", LocalDate.now(), "SSP");
        Pessoa pessoa4 = new Pessoa(null, "Carlos Eduardo", "53698741244", "63999999999", "54125411" , "carloseduardo@gmail", LocalDate.now(), "SSP");

        Pessoa pessoa5 = new Pessoa(null, "Gustavo Miranda", "53698741255", "63555555555", "55555555" , "gustavomiranda@gmail", LocalDate.now(), "SSP");
        Pessoa pessoa6 = new Pessoa(null, "Leticia Maria", "66698741244", "63666666666", "99999999" , "leticiamaria@gmail", LocalDate.now(), "SSP");


        pessoa1.setEndereco(endereco);
        pessoa2.setEndereco(endereco);
        pessoa3.setEndereco(endereco);
        pessoa4.setEndereco(endereco);
        pessoa5.setEndereco(endereco);
        pessoa6.setEndereco(endereco);
        pessoaRepository.saveAll(Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4, pessoa5, pessoa6));

        Perfil perfil1 = new Perfil(null, "ROLE_ADMIN");
        Perfil perfil2 = new Perfil(null, "ROLE_ALUNO");
        Perfil perfil3 = new Perfil(null, "ROLE_INSTRUTOR");
        perfilRepository.saveAll(Arrays.asList(perfil1, perfil2, perfil3));

        Usuario usuario1 = new Usuario(null, "roberto", "$2a$10$RqzpUg4K5/3eaxBJ0tjON.JvkTAvj4fa/1W9NvLKtZ798rICa9byy");
        Usuario usuario2 = new Usuario(null, "ana", "$2a$10$RqzpUg4K5/3eaxBJ0tjON.JvkTAvj4fa/1W9NvLKtZ798rICa9byy");
        Usuario usuario3 = new Usuario(null, "maria", "$2a$10$RqzpUg4K5/3eaxBJ0tjON.JvkTAvj4fa/1W9NvLKtZ798rICa9byy");
        Usuario usuario4 = new Usuario(null, "carlos", "$2a$10$209u3RTwd00zfdRuf/Db6.JW2fnDrKL810EJHFujyqe6SN2SNmROK");

        Usuario usuario5 = new Usuario(null, "gustavo", "$2a$10$ywWGADU85VR/jWgWAomIfuV5jruzeROkUs2ZxPQ5Mv0o0n2CiKscq");
        Usuario usuario6 = new Usuario(null, "leticia", "$2a$10$Y4RCr5Dh0TyhS9y2CPqeMOprDwY8gFEgSBHAKNxlJohRHTpB7O5o2");

        usuario1.getPerfis().add(perfil2);
        usuario2.getPerfis().add(perfil2);
        usuario3.getPerfis().add(perfil2);
        usuario4.getPerfis().add(perfil1);
        usuario4.getPerfis().add(perfil2);
        usuario4.getPerfis().add(perfil3);

        usuario5.getPerfis().add(perfil3);
        usuario6.getPerfis().add(perfil3);

        usuario1.setPessoa(pessoa1);
        usuario2.setPessoa(pessoa2);
        usuario3.setPessoa(pessoa3);
        usuario4.setPessoa(pessoa4);

        usuario5.setPessoa(pessoa4);
        usuario6.setPessoa(pessoa4);
        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6));

        HorarioAula horarioAula1 = new HorarioAula(null, LocalDate.now(), new Date(), new Date(), new Date(), new Date());
        HorarioAula horarioAula2 = new HorarioAula(null, LocalDate.now(), new Date(), new Date(), new Date(), new Date());

        Curso curso = new Curso(null, "Curso teste", LocalDate.now(), LocalDate.now(), 200.20, 30, StatusCurso.INSCRICOES_ABERTAS);
        Curso curso1 = new Curso(null, "Curso teste 2", LocalDate.now(), LocalDate.now(), 200.20, 30, StatusCurso.INSCRICOES_ABERTAS);

        curso1.getAlunos().add(pessoa1);

        curso.getAlunos().add(pessoa2);
        curso.getAlunos().add(pessoa3);
        curso.getAlunos().add(pessoa4);

        curso.getInstrutor().add(pessoa5);
        curso.getInstrutor().add(pessoa6);

        curso.setHorarioAula(horarioAula1);
        curso1.setHorarioAula(horarioAula2);

        cursoRepository.saveAll(Arrays.asList(curso, curso1));

    }
}
