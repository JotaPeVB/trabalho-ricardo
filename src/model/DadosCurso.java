package model;

import java.util.List;

public record DadosCurso(DadosProfessor professor, List<DadosAluno> alunos) {
}
