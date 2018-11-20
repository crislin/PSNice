package br.com.livroandroid.psnice;

/**
 * Created by livetouch on 28/08/18.
 */

public class Trofeu {
    private String nome;
    private String descricao;
    private String imagemTrofeu;
    private String tipo;
    private boolean hidden;
    private boolean earned;
    private String dayEarned;
    private String hourEarned;

    public Trofeu(String imagemTrofeu, String nome, String descricao){
        this.imagemTrofeu = imagemTrofeu;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Trofeu(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemTrofeu() {
        return imagemTrofeu;
    }

    public void setImagemTrofeu(String imagemTrofeu) {
        this.imagemTrofeu = imagemTrofeu;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean getEarned() {
        return earned;
    }

    public void setEarned(boolean earned) {
        this.earned = earned;
    }

    public String getDayEarned() {
        return dayEarned;
    }

    public void setDayEarned(String dayEarned) {
        this.dayEarned = dayEarned;
    }

    public String getHourEarned() {
        return hourEarned;
    }

    public void setHourEarned(String hourEarned) {
        this.hourEarned = hourEarned;
    }
}
