package com.projeto_extensao.ProjetoExtensao.repository;

import com.projeto_extensao.ProjetoExtensao.model.Aluno;
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
public class AlunoRepository {

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

    public void save(Aluno aluno) {
        String sql = "insert into aluno (nome, cpf, curso) values (?,?,?);";
        template.update(sql, aluno.getNome(), aluno.getCpf(), aluno.getCurso());
    }

    public List<Aluno> findAlunos(){
        String sql = "select * from aluno;";

        List<Aluno> alunos = template.query(sql, mapper);
        return alunos;
    }

    public Optional<Aluno> pegaAluno(int id) {
        String sql = "select * from aluno where id = ?";
        Aluno aluno = null;
        try {
            aluno = template.queryForObject(sql, new Object[]{id}, mapper);
        } catch (DataAccessException e) {
            e.getStackTrace();
        }
        return Optional.ofNullable(aluno);
    }

    public void update(Aluno aluno, int id) {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, curso = ? WHERE id = ?;";

        template.update(sql, aluno.getNome(), aluno.getCpf(), aluno.getCurso(), id);
    }

    public void delete(int id) {
        String sql = "delete from aluno where id = ?;";

        template.update(sql, id);
    }

}
