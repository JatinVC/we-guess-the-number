package com.mthree.guessthenumber.Dao;

import java.util.List;

import com.mthree.guessthenumber.Model.Game;

public interface GameDao {
    Game add(Game game);

    List<Game> getAll();

    Game findById(int id);

    boolean finishGame(Game game);
}