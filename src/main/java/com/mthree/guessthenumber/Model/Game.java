package com.mthree.guessthenumber.Model;

import java.util.Objects;

public class Game {
    private int id;
    private String status;
    private int answer;


    public Game() {
    }

    public Game(int id, String status, int answer) {
        this.id = id;
        this.status = status;
        this.answer = answer;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAnswer() {
        return this.answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Game id(int id) {
        setId(id);
        return this;
    }

    public Game status(String status) {
        setStatus(status);
        return this;
    }

    public Game answer(int answer) {
        setAnswer(answer);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return id == game.id && Objects.equals(status, game.status) && answer == game.answer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, answer);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", status='" + getStatus() + "'" +
            ", answer='" + getAnswer() + "'" +
            "}";
    }

}
