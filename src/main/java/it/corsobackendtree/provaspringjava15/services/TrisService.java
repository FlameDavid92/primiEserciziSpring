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
        switch (gameResp) {
            case INDEXERR -> ret += "Indici non corretti!\n";
            case NOTVOIDERR -> ret += "Cella già occupata!\n";
            case PLAYERWIN -> {
                ret += "Hai vinto!!!\n";
                ret += "Vai a /" + idPartita + "/new per rigiocare!\n";
                games.put(idPartita, null);
            }
            case SERVERWIN -> {
                ret += "Hai perso!\n";
                ret += "Vai a /" + idPartita + "/new per rigiocare!\n";
                games.put(idPartita, null);
            }
            case TIE -> {
                ret += "Partita conclusa in parità.\n";
                ret += "Vai a /" + idPartita + "/new per rigiocare!\n";
                games.put(idPartita, null);
            }
        }
        return ret+game.stringGame();
    }

    public String nuovoGioco(int idPartita, String simboloPlayer){
        if(simboloPlayer.length() > 1) return "Simbolo player non corretto!";
        TrisGame.ValoreCella valorePlayer = null;
        if(simboloPlayer.toLowerCase().equals("o")) valorePlayer = TrisGame.ValoreCella.O;
        else if(simboloPlayer.toLowerCase().equals("x")) valorePlayer = TrisGame.ValoreCella.X;

        if(valorePlayer == null) return "Simbolo player non corretto!";

        String ret="";
        if(games.put(idPartita, new TrisGame(valorePlayer)) == null){
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

    public String[][] generaMatrice(int interoMatrice){
        String[][] matrice = new String[3][3];
        int tempInt = interoMatrice;
        for(int i=8; i>=0; i--){
            int val = tempInt%10;
            matrice[i/3][i%3] = (val == 2) ? "X" : ((val == 1) ? "O" : " ");
            tempInt /= 10;
        }
        return matrice;
    }
}
