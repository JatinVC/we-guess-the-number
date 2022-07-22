package com.mthree.guessthenumber.Service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mthree.guessthenumber.Dao.GameDao;
import com.mthree.guessthenumber.Model.Game;

@Service
public class GameService {
    private final GameDao dao;

    public GameService(GameDao dao){
        this.dao = dao;
    }

    public Game beginGame(){
        Game newGame = new Game();
        newGame.setAnswer(generateAnswer());
        newGame.setStatus("in_progress");

        dao.add(newGame);
        return newGame;
    }

    private int generateAnswer(){
        Random number = new Random();
        String answer = "";
        
        for(int i=0; i<4; i++){
            answer += Integer.toString(number.nextInt(10)+1);
        }

        return Integer.parseInt(answer);
    }

    public List<Game> getAll(){
        return dao.getAll();
    }

    public Game getGame(int id){
        return dao.findById(id);
    }
}
