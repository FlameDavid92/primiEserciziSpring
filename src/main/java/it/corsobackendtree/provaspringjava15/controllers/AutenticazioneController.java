package it.corsobackendtree.provaspringjava15.controllers;

import it.corsobackendtree.provaspringjava15.services.DatabaseService;
import it.treebackend.exercisespring.view.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AutenticazioneController {

    @PostMapping("/registrazione")
    ResponseEntity<String> registrazione (@RequestBody User body, @Autowired DatabaseService dbService){
        HttpHeaders headers = new HttpHeaders();
        if(dbService.registrazione(body)) return new ResponseEntity<>("Registrazione avvenuta con successo!", headers, HttpStatus.OK);
        return new ResponseEntity<>("Registrazione fallita: username già presente.", headers, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody User body, @Autowired DatabaseService dbService, HttpServletResponse response){
        String username = dbService.login(body);
        HttpHeaders headers = new HttpHeaders();
        if(username == null){
            return new ResponseEntity<>("Login fallito.",headers, HttpStatus.BAD_REQUEST);
        }else{
            Cookie cookie = new Cookie("triscookie", username);
            response.addCookie(cookie);
            return new ResponseEntity<>("Login effettuato.",headers, HttpStatus.OK);
        }
    }

    @GetMapping("/")
    public String readCookie(@CookieValue(value = "triscookie", defaultValue = "") String username) {
        if(username == "") return "Effettua il login.";
        else return "Il tuo username è " + username;
    }
}
