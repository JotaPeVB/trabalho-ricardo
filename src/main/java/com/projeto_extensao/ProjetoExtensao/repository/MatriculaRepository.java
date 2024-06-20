package com.projeto_extensao.ProjetoExtensao.repository;

import com.projeto_extensao.ProjetoExtensao.model.Aluno;
import com.projeto_extensao.ProjetoExtensao.model.Professor;
import com.projeto_extensao.ProjetoExtensao.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MatriculaRepository {

    private List<Aluno> alunos;
    private List<Professor> professores;
    private Projeto projeto;

    private RowMapper<Aluno> mapper = new RowMapper<Aluno>() {
        @Override
        public Aluno mapRow(ResultSet rs, int rowNum) throws SQLException {
            Aluno a = new Aluno();
            a.setId(rs.getInt(1));
            a.setNome(rs.getString(2));
            a.setCurso(rs.getString(4));
            a.setCpf(rs.getString(3));

            return a;
        }
    };

    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }
    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public void saveAluno(String cpf, Integer id) {
        String idPego;
        try {
            String sqlCpf = "SELECT id FROM aluno WHERE cpf = ?";
            idPego = String.valueOf(template.queryForObject(sqlCpf, new Object[]{cpf}, Integer.class));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        } try {
            if (idPego != null) {
                String sql = "insert into aluno_professor_projeto(aluno_id, projeto_id) values(?, ?)";
                template.update(sql, idPego, id);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
