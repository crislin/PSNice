package br.com.livroandroid.psnice;

/**
 * Created by livetouch on 03/10/18.
 */

public class Comentario {
    private String idComentario;
    private String comentario;
    private String data;
    private int totalVotos;

    public Comentario(String idComentario, String comentario, String data, int totalVotos) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.data = data;
        this.totalVotos = totalVotos;
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }
}
