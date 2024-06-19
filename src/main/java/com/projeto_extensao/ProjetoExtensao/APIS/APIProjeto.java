package com.projeto_extensao.ProjetoExtensao.APIS;


import com.projeto_extensao.ProjetoExtensao.model.Projeto;
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

        @PostMapping
        @ResponseBody
        public Projeto criar(@RequestBody Projeto projeto){
            return projetoRepository.save(projeto);
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

        @DeleteMapping("/{id}")
        @ResponseBody
        public void delete(@PathVariable("id") Integer id) {
            projetoRepository.delete(id);
        }


}
