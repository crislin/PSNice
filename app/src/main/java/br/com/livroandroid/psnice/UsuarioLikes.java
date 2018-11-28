package br.com.livroandroid.psnice;

/**
 * Created by livetouch on 28/11/18.
 */

public class UsuarioLikes {

    private String psnId;
    private String tipoVoto;

    public UsuarioLikes(String psnId, String tipoVoto) {
        this.psnId = psnId;
        this.tipoVoto = tipoVoto;
    }

    public UsuarioLikes() {
    }

    public String getTipoVoto() {
        return tipoVoto;
    }

    public void setTipoVoto(String tipoVoto) {
        this.tipoVoto = tipoVoto;
    }

    public String getPsnId() {
        return psnId;
    }

    public void setPsnId(String psnId) {
        this.psnId = psnId;
    }
}
