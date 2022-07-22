package com.mthree.guessthenumber.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mthree.guessthenumber.Dao.GameDao;
import com.mthree.guessthenumber.Dao.RoundDao;
import com.mthree.guessthenumber.Model.Game;
import com.mthree.guessthenumber.Model.Round;

@Service
public class RoundService {
    private final RoundDao dao;
    private final GameDao gDao;

    public RoundService(RoundDao dao, GameDao gDao) {
        this.dao = dao;
        this.gDao = gDao;
    }

    public Round newGuess(Round round) {
        dao.add(round);
        return round;
    }

    public List<Round> getRounds(Game game) {
        return dao.getAllForGame(game);
    }

    public String checkGuess(Round round) {
        Game game = gDao.findById(round.getGame_id());

        if (game.getStatus() != "finished") {
            String guess = Integer.toString(round.getGuess());
            String answer = Integer.toString(game.getAnswer());
            int exactMatch = 0;
            int partialMatch = 0;

            for (int i = 0; i < guess.length(); i++) {
                if (answer.charAt(i) == guess.charAt(i)) {
                    exactMatch++;
                } else if (answer.contains(Character.toString(guess.charAt(i)))) {
                    partialMatch++;
                }
            }

            if (exactMatch == 4) {
                this.finishGame(game);
            }

            return "e:" + exactMatch + ":p:" + partialMatch;
        } else {
            return "Cannot guess, play another game";
        }

    }

    private void finishGame(Game game) {
        gDao.finishGame(game);
    }
}
