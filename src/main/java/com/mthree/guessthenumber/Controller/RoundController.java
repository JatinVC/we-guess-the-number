package com.mthree.guessthenumber.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mthree.guessthenumber.Model.Game;
import com.mthree.guessthenumber.Model.Round;
import com.mthree.guessthenumber.Service.GameService;
import com.mthree.guessthenumber.Service.RoundService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class RoundController {
    private final RoundService service;
    private final GameService gService;

    public RoundController(RoundService service, GameService gService){
        this.service = service;
        this.gService = gService;
    }

    @PostMapping("/guess")
    public ResponseEntity<String> guess(@RequestBody Round round){
        round.setRound_time(new Date(System.currentTimeMillis()));
        Round newRound = service.newGuess(round);
        return new ResponseEntity<String>(service.checkGuess(newRound), HttpStatus.OK);
    }

    @GetMapping("/rounds/{id}")
    public ResponseEntity<List<Round>> getRoundsForGame(@PathVariable String id){
        Game game = gService.getGame(Integer.parseInt(id));
        return new ResponseEntity<List<Round>>(service.getRounds(game), HttpStatus.OK);
    }
}
