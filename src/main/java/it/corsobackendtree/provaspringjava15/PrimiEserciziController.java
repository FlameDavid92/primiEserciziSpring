package it.corsobackendtree.provaspringjava15;

import it.corsobackendtree.provaspringjava15.services.ContatoreService;
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
}
