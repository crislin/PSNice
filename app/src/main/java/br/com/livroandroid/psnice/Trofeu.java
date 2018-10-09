package br.com.livroandroid.psnice;

/**
 * Created by livetouch on 28/08/18.
 */

public class Trofeu {
    private String nome;
    private String descricao;
    private String tipo;
    private boolean hidden;
    private boolean conquistado;
    private int imagemTrofeu;

    public Trofeu(int imagemTrofeu, String nome, String descricao){
        this.imagemTrofeu = imagemTrofeu;
        this.nome = nome;
        this.descricao = descricao;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isConquistado() {
        return conquistado;
    }

    public void setConquistado(boolean conquistado) {
        this.conquistado = conquistado;
    }

    public int getImagemTrofeu() {
        return imagemTrofeu;
    }

    public void setImagemTrofeu(int imagemTrofeu) {
        this.imagemTrofeu = imagemTrofeu;
    }
}
