package principal;

import model.DadosAluno;
import model.DadosCurso;
import model.DadosProfessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);

        DadosAluno aluno = new DadosAluno("033", "2032333", "Joao"
                , "trabalho", "Sistemas de Informacao", 700);

        DadosAluno aluno1 = new DadosAluno("0334", "203233", "Joao G"
                , "trabalho", "Gestao Ambiental", 700);

        DadosProfessor professor = new DadosProfessor("03332", "23323", "Carlos", "trabalho5"
        , "Sistemas de Informacao", "ESETI");

        DadosProfessor professor2 = new DadosProfessor("03332", "2333", "Ricardo", "IA"
                , "Sistemas de Informacao", "ESETI");


        List<DadosAluno> dadosAlunos = new ArrayList<>();
        dadosAlunos.add(aluno);
        dadosAlunos.add(aluno1);

        System.out.println("Insira o nome de um trabalho para buscar todos os alunos participantes");
        var nomeProjeto = leitura.nextLine();
        var listaParticipantesAlunos = dadosAlunos.stream()
                        .filter(a -> a.nomeProjeto().toLowerCase().equalsIgnoreCase(nomeProjeto.toLowerCase()))
                                .collect(Collectors.toList());
        System.out.println(listaParticipantesAlunos);


        System.out.println("Insira o nome de um curso para listar os discentes e os doscentes de um curso");
        var nomeCurso = leitura.nextLine();
        var listarAlunosCurso = dadosAlunos.stream()
                .filter(a -> a.nomeCurso().toLowerCase().contains(professor.cursoResponsavel().toLowerCase()))
                .collect(Collectors.toList());
        DadosCurso curso = new DadosCurso(professor, listarAlunosCurso);
        System.out.println(curso);

    }
}
