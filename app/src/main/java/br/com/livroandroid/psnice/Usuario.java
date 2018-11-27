package br.com.livroandroid.psnice;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by livetouch on 13/09/18.
 */

public class Usuario implements Serializable{
    private String psnId;
    private String avatar;
    private int level;
    private int total;
    private int platinum;
    private int gold;
    private int silver;
    private int bronze;
    private int progress;
    private int rankMundial;
    private int rankPais;
    private float eficiencia;
    private int totalTrofeuPossivel;
//    private int ps4Games;
//    private int ps3Games;
//    private int psVitaGames;
//
//    public int getPs4Games() {
//        return ps4Games;
//    }
//
//    public void setPs4Games(int ps4Games) {
//        this.ps4Games = ps4Games;
//    }

//    public int getPs3Games() {
//        return ps3Games;
//    }
//
//    public void setPs3Games(int ps3Games) {
//        this.ps3Games = ps3Games;
//    }
//
//    public int getPsVitaGames() {
//        return psVitaGames;
//    }
//
//    public void setPsVitaGames(int psVitaGames) {
//        this.psVitaGames = psVitaGames;
//    }

    public int getRankMundial() {
        return rankMundial;
    }

    public void setRankMundial(int rankMundial) {
        this.rankMundial = rankMundial;
    }

    public int getRankPais() {
        return rankPais;
    }

    public void setRankPais(int rankPais) {
        this.rankPais = rankPais;
    }

    public float getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(float eficiencia) {
        this.eficiencia = eficiencia;
    }

    public int getTotalTrofeuPossivel() {
        return totalTrofeuPossivel;
    }

    public void setTotalTrofeuPossivel(int totalTrofeuPossivel) {
        this.totalTrofeuPossivel = totalTrofeuPossivel;
    }

    public String getPsnId() {
        return psnId;
    }

    public void setPsnId(String psnId) {
        this.psnId = psnId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPlatinum() {
        return platinum;
    }

    public void setPlatinum(int platinum) {
        this.platinum = platinum;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
