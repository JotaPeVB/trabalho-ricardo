package com.projeto_extensao.ProjetoExtensao.repository;

import com.projeto_extensao.ProjetoExtensao.model.Projeto;
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
public class ProjetoRepository {

    private JdbcTemplate template;
    private RowMapper<Projeto> mapper = new RowMapper<Projeto>() {
        @Override
        public Projeto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Projeto a = new Projeto();
            a.setId(rs.getInt(1));
            a.setNome(rs.getString(2));

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

    public Projeto save(Projeto projeto) {
        String sql = "insert into projeto_extensao(nome) values (?);";
        template.update(sql, projeto.getNome());
        return projeto;
    }

    public List<Projeto> findProjeto(){
        String sql = "select * from projeto_extensao;";

        List<Projeto> projeto = template.query(sql, mapper);
        return projeto;
    }

    public Projeto update(Projeto projeto, int id) {
        String sql = "UPDATE projeto_extensao SET nome = ? WHERE id = ?;";

        template.update(sql, projeto.getNome(), id);
        return projeto;
    }

    public Optional<Projeto> pegaProjeto(int id) {
        String sql = "select * from projeto_extensao where id = ?";
        Projeto projeto = null;
        try {
            projeto = template.queryForObject(sql, new Object[]{id}, mapper);
        } catch (DataAccessException e) {
            e.getStackTrace();
        }
        return Optional.ofNullable(projeto);
    }


    public void delete(int id) {
        String sql = "delete from projeto_extensao where id = ?;";

        template.update(sql, id);
    }


}
