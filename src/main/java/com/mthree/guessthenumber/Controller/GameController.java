package com.mthree.guessthenumber.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mthree.guessthenumber.Model.Game;
import com.mthree.guessthenumber.Service.GameService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class GameController {

    private final GameService service;

    public GameController(GameService service){
        this.service = service;
    }

    @PostMapping("/begin")
    public ResponseEntity<Game> beginGame(){
        return new ResponseEntity<Game>(service.beginGame(), HttpStatus.OK);
    }

    @GetMapping("/game")
    public ResponseEntity<List<Game>> getAll(){
        return new ResponseEntity<List<Game>>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGame(@PathVariable int id){
        return new ResponseEntity<Game>(service.getGame(id), HttpStatus.OK);
    }
}
