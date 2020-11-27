package it.corsobackendtree.provaspringjava15.controllers;

import it.corsobackendtree.provaspringjava15.services.ContatoreService;
import it.corsobackendtree.provaspringjava15.services.TrisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PrimiEserciziController {

    @GetMapping("/esercizio1")
    int randomInt(){
        return new Random().nextInt();
    }

    @GetMapping("/esercizio2/{parola}")
    String[] parolaLenUpp(@PathVariable("parola") String parola){
        return new String[] {""+parola.length(), parola.toUpperCase()};
    }

    @GetMapping("/esercizio3")
    int contatore(@Autowired ContatoreService cs){
        return cs.visita();
    }

    /*Esercizi 4-5*/
    @GetMapping("/{idPartita}/new/{simboloPlayer}")
    String nuovoGioco(@Autowired TrisService ts, @PathVariable("idPartita") int idPartita,
                      @PathVariable("simboloPlayer") String simboloPlayer){
        return ts.nuovoGioco(idPartita, simboloPlayer);
    }

    @GetMapping("/{idPartita}/move/{posI}/{posJ}")
    String inserisciMossa(@Autowired TrisService ts, @PathVariable("idPartita") int idPartita,
                          @PathVariable("posI") int posI, @PathVariable("posJ") int posJ){
        return ts.gioca(idPartita,posI,posJ);
    }

    @GetMapping("/{idPartita}/back")
    String cancellaUltimaMossa(@Autowired TrisService ts, @PathVariable("idPartita") int idPartita){
        return ts.back(idPartita);
    }

    @GetMapping("/{idPartita}/state")
    String statoPartita(@Autowired TrisService ts, @PathVariable("idPartita") int idPartita){
        return ts.statoAttuale(idPartita);
    }

    @GetMapping("/interotomatricetris/{interoMatrice}")
    String[][] generaMatrice(@Autowired TrisService ts, @PathVariable("interoMatrice") int interoMatrice){
        return ts.generaMatrice(interoMatrice);
    }
}
