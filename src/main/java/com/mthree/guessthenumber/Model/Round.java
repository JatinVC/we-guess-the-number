package com.mthree.guessthenumber.Model;

import java.sql.Date;
import java.util.Objects;

public class Round {
    private int id;
    private int game_id;
    private int guess;
    private Date round_time;


    public Round() {
    }

    public Round(int id, int game_id, int guess, Date round_time) {
        this.id = id;
        this.game_id = game_id;
        this.guess = guess;
        this.round_time = round_time;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame_id() {
        return this.game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getGuess() {
        return this.guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public Date getRound_time() {
        return this.round_time;
    }

    public void setRound_time(Date round_time) {
        this.round_time = round_time;
    }

    public Round id(int id) {
        setId(id);
        return this;
    }

    public Round game_id(int game_id) {
        setGame_id(game_id);
        return this;
    }

    public Round guess(int guess) {
        setGuess(guess);
        return this;
    }

    public Round round_time(Date round_time) {
        setRound_time(round_time);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Round)) {
            return false;
        }
        Round round = (Round) o;
        return id == round.id && game_id == round.game_id && guess == round.guess && Objects.equals(round_time, round.round_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, game_id, guess, round_time);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", game_id='" + getGame_id() + "'" +
            ", guess='" + getGuess() + "'" +
            ", round_time='" + getRound_time() + "'" +
            "}";
    }

}
