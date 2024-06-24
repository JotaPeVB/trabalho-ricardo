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

    private RowMapper<Aluno> mapperAluno = new RowMapper<Aluno>() {
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

    private RowMapper<Professor> mapperProfessor = new RowMapper<Professor>() {
        @Override
        public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Professor a = new Professor();
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
        Integer idPego;
        try {
            String sqlCpf = "SELECT aluno.id FROM aluno WHERE aluno.cpf = ?";
            idPego = template.queryForObject(sqlCpf, new Object[]{cpf}, Integer.class);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        } try {
            if (idPego != null) {
                String sql = "insert into projeto_aluno(aluno_id, projeto_id) values(?, ?)";
                template.update(sql, idPego, id);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveProfessor(String cpf, Integer id) {
        Integer idPego;
        try {
            String sqlCpf = "SELECT professor.id FROM professor WHERE professor.cpf = ?";
            idPego = template.queryForObject(sqlCpf, new Object[]{cpf}, Integer.class);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        } try {
            if (idPego != null) {
                String sql = "insert into projeto_professor(professor_id, projeto_id) values(?, ?)";
                template.update(sql, idPego, id);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Professor> findProfessores(Integer projetoId) {
        String sql = "select * from professor" +
                " join projeto_professor on professor.id = projeto_professor.professor_id" +
                " where projeto_professor.projeto_id = ?;";

        List<Professor> professores = template.query(sql, new Object[]{projetoId}, mapperProfessor);
        return professores;
    }

    public List<Aluno> findAlunos(Integer projetoId) {
        String sql = "select * from aluno " +
                " join projeto_aluno on aluno.id = projeto_aluno.aluno_id " +
                " where projeto_aluno.projeto_id = ?;";

        List<Aluno> alunos = template.query(sql, new Object[]{projetoId}, mapperAluno);
        return alunos;
    }

    public void desmatricularAlunos(Integer cpf) {
        Integer idPego;
        String sqlCpf = "SELECT aluno.id FROM aluno WHERE aluno.cpf = ?";
        idPego = template.queryForObject(sqlCpf, new Object[]{cpf}, Integer.class);
        String sql = "delete from projeto_aluno where projeto_aluno.aluno_id = ?";
        template.update(sql, idPego);
    }

    public void desmatricularProfessores(Integer cpf) {
        Integer idPego;
        String sqlCpf = "SELECT professor.id FROM professor WHERE professor.cpf = ?";
        idPego = template.queryForObject(sqlCpf, new Object[]{cpf}, Integer.class);
        String sql = "delete from projeto_profesor where projeto_professor.professor_id = ?";
        template.update(sql, idPego);
    }




}
