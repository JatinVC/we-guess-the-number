package com.mthree.guessthenumber.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.mthree.guessthenumber.Model.Game;

@Repository
public class GameDaoImpl implements GameDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {
        final String insertQuery = "INSERT INTO Game(game_status, answer) VALUES (?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getStatus());
            statement.setInt(2, game.getAnswer());

            return statement;
        }, keyHolder);

        game.setId(Integer.parseInt((String) keyHolder.getKeys().get("game_id")));

        return game;
    }

    @Override
    public List<Game> getAll() {
        final String getAllSql = "Select * from Game;";
        return jdbcTemplate.query(getAllSql, new GameMapper());
    }

    @Override
    public Game findById(int id) {
        final String getSql = "select * from Game where game_id = ?";
        return jdbcTemplate.queryForObject(getSql, new GameMapper(), id);
    }

    @Override
    public boolean finishGame(Game game) {
        final String sql = "UPDATE Game SET game_status = 'finished' WHERE game_id = ?";
        return jdbcTemplate.update(sql, game.getId()) > 0;
    }
    
    private static final class GameMapper implements RowMapper<Game>{

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("game_id"));
            game.setStatus(rs.getString("game_status"));
            game.setAnswer(rs.getInt("answer"));

            return game;
        }
        
    }
}
