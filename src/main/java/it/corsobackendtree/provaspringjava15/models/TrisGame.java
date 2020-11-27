package it.corsobackendtree.provaspringjava15.models;

import java.util.Random;

public class TrisGame {
    public enum ValoreCella{ VUOTA,X,O}
    public enum GameResp{INDEXERR, NOTVOIDERR, PLAYERWIN, SERVERWIN, TIE, CONTINUE}

    private final ValoreCella[][] game;
    private final ValoreCella valorePlayer;
    private final ValoreCella valoreServer;
    private int lastPlayerI = -1;
    private int lastPlayerJ = -1;
    private int lastServerI = -1;
    private int lastServerJ = -1;
    private int movesCounter;

    public TrisGame(ValoreCella valorePlayer){
        this.valorePlayer = valorePlayer;
        this.valoreServer = (valorePlayer == ValoreCella.O) ? ValoreCella.X : ValoreCella.O;
        movesCounter = 0;
        game = new ValoreCella[3][3];
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                game[i][j] = ValoreCella.VUOTA;
            }
        }
    }

    public GameResp gioca(int i, int j){
        if(i>=3 || i<0 || j>=3 || j<0) return GameResp.INDEXERR;
        if(game[i][j] == ValoreCella.VUOTA){
            movesCounter++;
            game[i][j] = valorePlayer;
            lastPlayerI = i;
            lastPlayerJ = j;
            if(checkEndGame()) return GameResp.PLAYERWIN;
            serverMove();
            if(checkEndGame()) return GameResp.SERVERWIN;
            if(movesCounter == 9) return GameResp.TIE;
            return GameResp.CONTINUE;
        } return GameResp.NOTVOIDERR;
    }

    private void serverMove(){
        Random rndm = new Random();
        int i;
        int j;
        do{
            i = rndm.nextInt(3);
            j = rndm.nextInt(3);
        }while(game[i][j] != ValoreCella.VUOTA);
        game[i][j] = valoreServer;
        lastServerI = i;
        lastServerJ = j;
    }

    public boolean checkEndGame(){
        return (game[0][0] != ValoreCella.VUOTA && game[0][0] == game[0][1] && game[0][1] == game[0][2]) || //righe
                (game[1][0] != ValoreCella.VUOTA && game[1][0] == game[1][1] && game[1][1] == game[1][2]) ||
                (game[2][0] != ValoreCella.VUOTA && game[2][0] == game[2][1] && game[2][1] == game[2][2]) ||
                (game[0][0] != ValoreCella.VUOTA && game[0][0] == game[1][0] && game[1][0] == game[2][0]) || //colonne
                (game[0][1] != ValoreCella.VUOTA && game[0][1] == game[1][1] && game[1][1] == game[2][1]) ||
                (game[0][2] != ValoreCella.VUOTA && game[0][2] == game[1][2] && game[1][2] == game[2][2]) ||
                (game[0][0] != ValoreCella.VUOTA && game[0][0] == game[1][1] && game[1][1] == game[2][2]) || //diagonali
                (game[2][0] != ValoreCella.VUOTA && game[2][0] == game[1][1] && game[1][1] == game[0][2]);
    }

    public boolean back(){
        if(lastPlayerI != -1 && lastPlayerJ != -1){
            game[lastPlayerI][lastPlayerJ] = ValoreCella.VUOTA;
            game[lastServerI][lastServerJ] = ValoreCella.VUOTA;
            lastPlayerI = -1;
            lastPlayerJ = -1;
            lastServerI = -1;
            lastServerJ = -1;
            return true;
        } return false;
    }

    public String stringGame(){
        StringBuilder str = new StringBuilder();
        str.append("Tuo simbolo: "+valorePlayer+"\n");
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                str.append("|");
                if(game[i][j] == ValoreCella.VUOTA) str.append(" ");
                else if(game[i][j] == ValoreCella.X) str.append("X");
                else str.append("O");
                str.append("|");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
