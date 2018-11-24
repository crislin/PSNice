package br.com.livroandroid.psnice;

/**
 * Created by gazip on 24/11/2018.
 */

public class Cadastro {
    private String psnId;
    private String Senha;

    public Cadastro(String psnId, String senha) {
        this.psnId = psnId;
        Senha = senha;
    }

    public Cadastro(){}

    public String getPsnId() {
        return psnId;
    }

    public void setPsnId(String psnId) {
        this.psnId = psnId;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }
}
