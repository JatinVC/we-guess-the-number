package com.mthree.guessthenumber.Dao;

import java.util.List;

import com.mthree.guessthenumber.Model.Game;
import com.mthree.guessthenumber.Model.Round;

public interface RoundDao {
    Round add(Round round);

    List<Round> getAllForGame(Game game);

    Round findById(int id);
}
