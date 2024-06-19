package com.projeto_extensao.ProjetoExtensao.APIS;


import com.projeto_extensao.ProjetoExtensao.model.Professor;
import com.projeto_extensao.ProjetoExtensao.repository.AlunoRepository;
import com.projeto_extensao.ProjetoExtensao.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/professores", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIProfessor {

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    @ResponseBody
    public Professor criar(@RequestBody Professor professor){
        return professorRepository.save(professor);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Professor atualizar(@PathVariable("id") Integer id, @RequestBody Professor professor) {
        return professorRepository.update(professor, id);
    }

    @GetMapping
    @ResponseBody
    public List<Professor> findAll() {
        return professorRepository.findProfessores();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        professorRepository.delete(id);
    }

}

