package br.com.livroandroid.psnice;

import java.util.List;

/**
 * Created by livetouch on 28/08/18.
 */

public class Jogo {
    private String nome;
    private String imagem;
    private boolean hasPlatinum;
    private int gamePlatinum;
    private int gameGold;
    private int gameSilver;
    private int gameBronze;
    private int gameTotal;
    private int gameTotalEarned;
    private int gameProgress;
    private List<Trofeu> trofeus;
    private String desenvolvedora;
    private String genero;
    private String quantidadeJogadores;

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getQuantidadeJogadores() {
        return quantidadeJogadores;
    }

    public void setQuantidadeJogadores(String quantidadeJogadores) {
        this.quantidadeJogadores = quantidadeJogadores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean getHasPlatinum() {
        return hasPlatinum;
    }

    public void setHasPlatinum(boolean hasPlatinum) {
        this.hasPlatinum = hasPlatinum;
    }

    public int getGamePlatinum() {
        return gamePlatinum;
    }

    public void setGamePlatinum(int gamePlatinum) {
        this.gamePlatinum = gamePlatinum;
    }

    public int getGameGold() {
        return gameGold;
    }

    public void setGameGold(int gameGold) {
        this.gameGold = gameGold;
    }

    public int getGameSilver() {
        return gameSilver;
    }

    public void setGameSilver(int gameSilver) {
        this.gameSilver = gameSilver;
    }

    public int getGameBronze() {
        return gameBronze;
    }

    public void setGameBronze(int gameBronze) {
        this.gameBronze = gameBronze;
    }

    public int getGameTotal() {
        return gameTotal;
    }

    public void setGameTotal(int gameTotal) {
        this.gameTotal = gameTotal;
    }

    public int getGameTotalEarned() {
        return gameTotalEarned;
    }

    public void setGameTotalEarned(int gameTotalEarned) {
        this.gameTotalEarned = gameTotalEarned;
    }

    public int getGameProgress() {
        return gameProgress;
    }

    public void setGameProgress(int gameProgress) {
        this.gameProgress = gameProgress;
    }

    public List<Trofeu> getTrofeus() {
        return trofeus;
    }

    public void setTrofeus(List<Trofeu> trofeus) {
        this.trofeus = trofeus;
    }
}
