package it.corsobackendtree.provaspringjava15.controllers;

import it.corsobackendtree.provaspringjava15.services.ContatoreService;
import it.corsobackendtree.provaspringjava15.services.TrisService;
import it.corsobackendtree.provaspringjava15.services.TrisService2;
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

    /*Esercizi 4-5 con matrice di gioco come intero*/
    @GetMapping("/special/{idPartita}/new/{simboloPlayer}")
    String nuovoGioco2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita,
                      @PathVariable("simboloPlayer") String simboloPlayer){
        return ts.nuovoGioco2(idPartita, simboloPlayer);
    }
    @GetMapping("/special/{idPartita}/move/{posI}/{posJ}")
    String inserisciMossa2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita,
                          @PathVariable("posI") int posI, @PathVariable("posJ") int posJ){
        return ts.gioca2(idPartita,posI,posJ);
    }
    @GetMapping("/special/{idPartita}/back")
    String cancellaUltimaMossa2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita){
        return ts.back2(idPartita);
    }
    @GetMapping("/special/{idPartita}/state")
    String statoPartita2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita){
        return ts.statoAttuale2(idPartita);
    }

    /*Prova rappresentazione matrice di gioco con intero*/
    @GetMapping("/special/interotomatricetris/{interoMatrice}")
    String[][] generaMatrice(@Autowired TrisService2 ts, @PathVariable("interoMatrice") int interoMatrice){
        return ts.generaMatrice(interoMatrice);
    }
}
