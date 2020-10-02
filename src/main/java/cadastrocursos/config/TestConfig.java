package cadastrocursos.config;

import cadastrocursos.domain.*;
import cadastrocursos.enums.StatusCurso;
import cadastrocursos.repository.CursoRepository;
import cadastrocursos.repository.MenuRepository;
import cadastrocursos.repository.RolelRepository;
import cadastrocursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;


@Configuration
@Profile("producao")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolelRepository rolelRepository;

    @Override
    public void run(String... args) throws Exception {
        Endereco endereco = new Endereco(null, "77024686", "Quadra 102 sul", "Qi 1", 12, "Plano diretor sul", "Palmas", "TO");

        Usuario usuario1 = new Usuario(
                null,
                "roberto",
                "$2a$10$RqzpUg4K5/3eaxBJ0tjON.JvkTAvj4fa/1W9NvLKtZ798rICa9byy",
                "Roberto Carlos",
                "11111111111",
                "63111111111",
                "11111111",
                "robertocarlos@gmail",
                LocalDate.now(),
                "SSP"
        );
        Usuario usuario2 = new Usuario(
                null,
                "ana",
                "$2a$10$RqzpUg4K5/3eaxBJ0tjON.JvkTAvj4fa/1W9NvLKtZ798rICa9byy",
                "Ana Maria",
                "22222222222",
                "63222222222",
                "22222222",
                "ananmaria@gmail",
                LocalDate.now(),
                "SSP"
        );
        Usuario usuario3 = new Usuario(
                null,
                "maria",
                "$2a$10$RqzpUg4K5/3eaxBJ0tjON.JvkTAvj4fa/1W9NvLKtZ798rICa9byy",
                "Maria Eduarda",
                "33333333333",
                "63333333333",
                "33333333",
                "maraiaeduarda@gmail",
                LocalDate.now(),
                "SSP"
        );
        Usuario usuario4 = new Usuario(
                null,
                "carlos",
                "$2a$10$209u3RTwd00zfdRuf/Db6.JW2fnDrKL810EJHFujyqe6SN2SNmROK",
                "Carlos Eduardo",
                "53698741244",
                "63999999999",
                "54125411",
                "carloseduardo@gmail",
                LocalDate.now(),
                "SSP"
        );
        Usuario usuario5 = new Usuario(
                null,
                "gustavo",
                "$2a$10$ywWGADU85VR/jWgWAomIfuV5jruzeROkUs2ZxPQ5Mv0o0n2CiKscq",
                "Gustavo Miranda",
                "53698741255",
                "63555555555",
                "55555555",
                "gustavomiranda@gmail",
                LocalDate.now(),
                "SSP"
        );
        Usuario usuario6 = new Usuario(
                null,
                "leticia",
                "$2a$10$Y4RCr5Dh0TyhS9y2CPqeMOprDwY8gFEgSBHAKNxlJohRHTpB7O5o2",
                "Leticia Maria",
                "66698741244",
                "63666666666",
                "99999999",
                "leticiamaria@gmail",
                LocalDate.now(),
                "SSP"
        );

        Usuario usuario7 = new Usuario(
                null,
                "ragir",
                "$2a$10$Y4RCr5Dh0TyhS9y2CPqeMOprDwY8gFEgSBHAKNxlJohRHTpB7O5o2",
                "Ragir Fernando",
                "223265874123",
                "63368741257",
                "85747896",
                "ragirfernando@gmail",
                LocalDate.now(),
                "SSP"
        );

        usuario1.setEndereco(endereco);
        usuario2.setEndereco(endereco);
        usuario3.setEndereco(endereco);
        usuario4.setEndereco(endereco);
        usuario5.setEndereco(endereco);
        usuario6.setEndereco(endereco);
        usuario7.setEndereco(endereco);
        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6, usuario7));

        Menu menu1 = new Menu(null, "/cursosAlunos");
        Menu menu2 = new Menu(null, "/meusCursosAlunos");

        Menu menu3 = new Menu(null, "/cursosInstrutores");
        Menu menu4 = new Menu(null, "/meusCursosInstrutores");

        Menu menu5 = new Menu(null, "/cursosAdmins");
        Menu menu6 = new Menu(null, "/meusCursosAdmins");

        Menu menu7 = new Menu(null, "/desenvolvedor");

        menuRepository.saveAll(Arrays.asList(menu1, menu2, menu3, menu4, menu5, menu6, menu7));

        Role role1 = new Role(null, "ROLE_ADMIN");
        Role role2 = new Role(null, "ROLE_ALUNO");
        Role role3 = new Role(null, "ROLE_INSTRUTOR");
        Role role4 = new Role(null, "ROLE_DESENVOLVEDOR");

        role1.getMenus().add(menu5);
        role1.getMenus().add(menu6);
        role2.getMenus().add(menu1);
        role2.getMenus().add(menu2);
        role3.getMenus().add(menu3);
        role3.getMenus().add(menu4);
        role4.getMenus().add(menu7);
        rolelRepository.saveAll(Arrays.asList(role1, role2, role3, role4));

        usuario1.getPerfis().add(role2);
        usuario2.getPerfis().add(role2);
        usuario3.getPerfis().add(role2);
        usuario4.getPerfis().add(role1);
        usuario4.getPerfis().add(role2);
        usuario4.getPerfis().add(role3);
        usuario5.getPerfis().add(role3);
        usuario6.getPerfis().add(role3);

        usuario7.getPerfis().add(role1);
        usuario7.getPerfis().add(role2);
        usuario7.getPerfis().add(role3);
        usuario7.getPerfis().add(role4);
        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6, usuario7));


        HorarioAula horarioAula1 = new HorarioAula(null, LocalDate.now(), new Date(), new Date(), new Date(), new Date());
        HorarioAula horarioAula2 = new HorarioAula(null, LocalDate.now(), new Date(), new Date(), new Date(), new Date());

        Curso curso = new Curso(null, "Curso teste", LocalDate.now(), LocalDate.now(), 200.20, 30, StatusCurso.INSCRICOES_ABERTAS);
        Curso curso1 = new Curso(null, "Curso teste 2", LocalDate.now(), LocalDate.now(), 200.20, 30, StatusCurso.INSCRICOES_ABERTAS);

        curso1.getAlunos().add(usuario1);
        curso.getAlunos().add(usuario2);
        curso.getAlunos().add(usuario3);
        curso.getAlunos().add(usuario4);

        curso.getInstrutor().add(usuario5);
        curso.getInstrutor().add(usuario6);

        curso.setHorarioAula(horarioAula1);
        curso1.setHorarioAula(horarioAula2);
        cursoRepository.saveAll(Arrays.asList(curso, curso1));
    }
}
