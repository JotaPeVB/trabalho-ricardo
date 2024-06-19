package com.projeto_extensao.ProjetoExtensao.repository;

import com.projeto_extensao.ProjetoExtensao.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorRepository {

    private JdbcTemplate template;
    private RowMapper<Professor> mapper = new RowMapper<Professor>() {
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


    public JdbcTemplate getTemplate() {
        return template;
    }
    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public Professor save(Professor professor) {
        String sql = "insert into professor (nome, cpf, curso) values (?,?,?);";
        template.update(sql, professor.getNome(), professor.getCpf(), professor.getCurso());
        return professor;
    }

    public List<Professor> findProfessores(){
        String sql = "select * from professor;";

        List<Professor> professores = template.query(sql, mapper);
        return professores;
    }

    public Professor update(Professor professor, int id) {
        String sql = "UPDATE professor SET nome = ?, cpf = ?, curso = ? WHERE id = ?;";

        template.update(sql, professor.getNome(), professor.getCpf(), professor.getCurso(), id);
        return professor;
    }

    public Optional<Professor> pegaProfessor(int id) {
        String sql = "select * from professor where id = ?";
        Professor professor = null;
        try {
            professor = template.queryForObject(sql, new Object[]{id}, mapper);
        } catch (DataAccessException e) {
            e.getStackTrace();
        }
        return Optional.ofNullable(professor);
    }


    public void delete(int id) {
        String sql = "delete from professor where id = ?;";

        template.update(sql, id);
    }
}
