package it.corsobackendtree.provaspringjava15.services;

import it.corsobackendtree.provaspringjava15.models.TrisGame;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TrisService {
    public static HashMap<Integer, TrisGame> games = new HashMap<>();

    public String gioca(int idPartita, int i, int j){
        TrisGame game = games.get(idPartita);
        if(game == null) return "Vai a /"+idPartita+"/new per iniziare un nuovo gioco!\n";
        String ret = "";
        TrisGame.GameResp gameResp = game.gioca(i, j);
        switch (gameResp){
            case INDEXERR:
                ret+="Indici non corretti!\n";
                break;
            case NOTVOIDERR:
                ret+="Cella già occupata!\n";
                break;
            case PLAYERWIN:
                ret+="Hai vinto!!!\n";
                ret+="Vai a /"+idPartita+"/new per rigiocare!\n";
                games.put(idPartita,null);
                break;
            case SERVERWIN:
                ret+="Hai perso!\n";
                ret+="Vai a /"+idPartita+"/new per rigiocare!\n";
                games.put(idPartita,null);
                break;
            case TIE:
                ret+="Partita conclusa in parità.\n";
                ret+="Vai a /"+idPartita+"/new per rigiocare!\n";
                games.put(idPartita,null);
        }
        return ret+game.stringGame();
    }

    public String nuovoGioco(int idPartita){
        String ret="";
        if(games.put(idPartita, new TrisGame()) == null){
            ret+="Nuova partita iniziata!\n";
        }else{
            ret+="Partita riavviata!\n";
        }
        return ret+games.get(idPartita).stringGame();
    }

    public String back(int idPartita){
        TrisGame game = games.get(idPartita);
        if(game == null) return "Vai a /"+idPartita+"/new per iniziare un nuovo gioco!\n";
        String ret = "";
        if(!game.back()){
            ret+="Mossa non disponibile!\n";
        }
        return ret+game.stringGame();
    }

    public String statoAttuale(int idPartita){
        TrisGame game = games.get(idPartita);
        if(game == null) return "Vai a /"+idPartita+"/new per iniziare un nuovo gioco!\n";
        return game.stringGame();
    }
}
