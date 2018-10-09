package br.com.livroandroid.psnice;

/**
 * Created by livetouch on 28/08/18.
 */

public class Jogo {
    private String nome;
    private int imagemJogo;
    private int totalTrofeus;
    private int bronze;
    private int silver;
    private int gold;
    private int platinum;
    private int porcentagem;
    private boolean havePlatinum;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagemJogo() {
        return imagemJogo;
    }

    public void setImagemJogo(int imagemJogo) {
        this.imagemJogo = imagemJogo;
    }

    public int getTotalTrofeus() {
        return totalTrofeus;
    }

    public void setTotalTrofeus(int totalTrofeus) {
        this.totalTrofeus = totalTrofeus;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPlatinum() {
        return platinum;
    }

    public void setPlatinum(int platinum) {
        this.platinum = platinum;
    }

    public int getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    public boolean isHavePlatinum() {
        return havePlatinum;
    }

    public void setHavePlatinum(boolean havePlatinum) {
        this.havePlatinum = havePlatinum;
    }
}
