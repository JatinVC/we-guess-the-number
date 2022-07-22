package com.mthree.guessthenumber.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.mthree.guessthenumber.Model.Game;
import com.mthree.guessthenumber.Model.Round;

@Repository
public class RoundDaoImpl implements RoundDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO Round(game_id, guess, round_time) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn)->{
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, round.getGame_id());
            statement.setInt(2, round.getGuess());
            statement.setDate(3, round.getRound_time());
            return statement;
        }, keyHolder);

        round.setId(Integer.parseInt(keyHolder.getKeys().get("round_id").toString()));

        return round;
    }

    @Override
    public List<Round> getAllForGame(Game game) {
        final String sql = "select * from Round where Round.game_id = ?";
        return jdbcTemplate.query(sql, new RoundMapper(), game.getId());
    }

    @Override
    public Round findById(int id) {
        final String sql = "select * from round where round_id = ?";
        return jdbcTemplate.queryForObject(sql, new RoundMapper(), id);
    }
    
    private static final class RoundMapper implements RowMapper<Round>{

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("round_id"));
            round.setGame_id(rs.getInt("game_id"));
            round.setGuess(rs.getInt("guess"));
            round.setRound_time(rs.getDate("round_time"));
            return round;
        }
        
    }
}
