package it.corsobackendtree.provaspringjava15.services;

import org.springframework.stereotype.Service;

@Service
public class ContatoreService {
    public static int contatore = 0;
    public int visita(){
        return ++contatore;
    }
}
