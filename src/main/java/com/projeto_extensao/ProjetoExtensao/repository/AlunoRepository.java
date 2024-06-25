package com.projeto_extensao.ProjetoExtensao.repository;

import com.projeto_extensao.ProjetoExtensao.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
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

    public Aluno save(Aluno aluno) {
        String sql = "insert into aluno (nome, cpf, curso) values (?,?,?);";
        template.update(sql, aluno.getNome(), aluno.getCpf(), aluno.getCurso());
        return aluno;
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

    public Aluno update(Aluno aluno, int id) {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, curso = ? WHERE id = ?;";

        template.update(sql, aluno.getNome(), aluno.getCpf(), aluno.getCurso(), id);
        return aluno;
    }

    public void delete(int id) {
        String checkSql = "select count(*) from projeto_aluno where aluno_id = ?";
        int count = template.queryForObject(checkSql, Integer.class, id);

        if (count > 0) {
            String deleteProjetoAlunoSql = "delete from projeto_aluno where aluno_id = ?";
            template.update(deleteProjetoAlunoSql, id);
        }

        String deleteAlunoSql = "delete from aluno where id = ?";
        template.update(deleteAlunoSql, id);
    }

}
