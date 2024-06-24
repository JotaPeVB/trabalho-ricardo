package com.projeto_extensao.ProjetoExtensao;

import com.projeto_extensao.ProjetoExtensao.model.Aluno;
import com.projeto_extensao.ProjetoExtensao.model.Professor;
import com.projeto_extensao.ProjetoExtensao.model.Projeto;
import com.projeto_extensao.ProjetoExtensao.repository.AlunoRepository;
import com.projeto_extensao.ProjetoExtensao.repository.MatriculaRepository;
import com.projeto_extensao.ProjetoExtensao.repository.ProfessorRepository;
import com.projeto_extensao.ProjetoExtensao.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProjetoExtensaoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProjetoExtensaoApplication.class, args);

		Aluno aluno = context.getBean(Aluno.class);
//
//		aluno.setNome("Joao");
//		aluno.setCpf("07735986181");
//		aluno.setCurso("Sistemas de Informação");

		AlunoRepository alunoRepository = context.getBean(AlunoRepository.class);
//		alunoRepository.save(aluno);
		alunoRepository.findAlunos().forEach(System.out::println);

		Professor professor = context.getBean(Professor.class);

//		professor.setNome("Gunter");
//		professor.setCpf("07778987541");
//		professor.setCurso("Sistemas de Informação");

		ProfessorRepository professorRepository = context.getBean(ProfessorRepository.class);

//		professorRepository.save(professor);
		professorRepository.findProfessores().forEach(System.out::println);
		if (alunoRepository.pegaAluno(1).isPresent()) {
			System.out.println(alunoRepository.pegaAluno(1).get());
		} else {
			System.out.println("Nao encontrado");
		}
		Projeto projeto = context.getBean(Projeto.class);
//		projeto.setNome("Ricardo Malvadeza");
//
//		ProjetoRepository projetoRepository = context.getBean(ProjetoRepository.class);
//		projetoRepository.save(projeto);

//		MatriculaRepository matriculaRepository = context.getBean(MatriculaRepository.class);
//		matriculaRepository.saveAluno("07735986181", 1);
	}

}
