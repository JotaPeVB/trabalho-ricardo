package com.projeto_extensao.ProjetoExtensao.APIS;


import com.projeto_extensao.ProjetoExtensao.model.Aluno;
import com.projeto_extensao.ProjetoExtensao.model.Professor;
import com.projeto_extensao.ProjetoExtensao.model.Projeto;
import com.projeto_extensao.ProjetoExtensao.repository.MatriculaRepository;
import com.projeto_extensao.ProjetoExtensao.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/projeto", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIProjeto {
        @Autowired
        private ProjetoRepository projetoRepository;
        @Autowired
        private MatriculaRepository matriculaRepository;

        @PostMapping
        @ResponseBody
        public Projeto criar(@RequestBody Projeto projeto){
            return projetoRepository.save(projeto);
        }

        @PostMapping("/{id}/matricularAluno")
        @ResponseBody
        public void matricularAluno(@PathVariable("id") Integer id, @RequestBody String cpf){
                matriculaRepository.saveAluno(cpf, id);
        }

        @PostMapping("/{id}/matricularProfessor")
        @ResponseBody
        public void matricularProfessor(@PathVariable("id") Integer id, @RequestBody String cpf){

                matriculaRepository.saveProfessor(cpf, id);
        }

        @PutMapping("/{id}")
        @ResponseBody
        public Projeto atualizar(@PathVariable("id") Integer id, @RequestBody Projeto projeto) {
            return projetoRepository.update(projeto, id);
        }

        @GetMapping
        @ResponseBody
        public List<Projeto> findAll() {
            return projetoRepository.findProjeto();
        }

        @GetMapping("/{id}/alunos")
        @ResponseBody
        public List<Aluno> findAlunosMatriculados(@PathVariable("id") Integer id) {
                return matriculaRepository.findAlunos(id);
        }

        @GetMapping("/{id}/professores")
        @ResponseBody
        public List<Professor> findProfessoresMatriculados(@PathVariable("id") Integer id) {
                return matriculaRepository.findProfessores(id);
        }

        @DeleteMapping("/{id}")
        @ResponseBody
        public void delete(@PathVariable("id") Integer id) {
                projetoRepository.delete(id);
        }

        @DeleteMapping("/{id}/desmatricularProfessor")
        @ResponseBody
        public void desmatricularProfessor(@PathVariable("id") Integer id, @RequestBody String cpf) {
                matriculaRepository.desmatricularProfessores(cpf, id);
        }

        @DeleteMapping("/{id}/desmatricularAluno")
        @ResponseBody
        public void desmatricularAluno(@PathVariable("id") Integer id, @RequestBody String cpf) {
                matriculaRepository.desmatricularAlunos(cpf,id);
        }

}
