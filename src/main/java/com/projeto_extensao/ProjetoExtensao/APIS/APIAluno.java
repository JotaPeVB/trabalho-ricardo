package com.projeto_extensao.ProjetoExtensao.APIS;

import com.projeto_extensao.ProjetoExtensao.model.Aluno;
import com.projeto_extensao.ProjetoExtensao.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping(value = "/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIAluno {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @ResponseBody
    public Aluno criar(@RequestBody Aluno aluno){
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Aluno atualizar(@PathVariable("id") Integer id, @RequestBody Aluno aluno) {
        return alunoRepository.update(aluno, id);
    }

    @GetMapping
    @ResponseBody
    public List<Aluno> findAll() {
        return alunoRepository.findAlunos();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        alunoRepository.delete(id);
    }
}
