package br.com.livroandroid.psnice;

/**
 * Created by livetouch on 03/10/18.
 */

public class Comentario {
    private String idComentario;
    private String comentario;
    private String data;
    private int likes;
    private int deslikes;
    private String key;
    private String avatar;

    public Comentario(String idComentario, String comentario, String data, int likes, int deslikes, String avatar) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.data = data;
        this.likes = likes;
        this.deslikes = deslikes;
        this.avatar = avatar;
    }

    public Comentario(){}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDeslikes() {
        return deslikes;
    }

    public void setDeslikes(int deslikes) {
        this.deslikes = deslikes;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
