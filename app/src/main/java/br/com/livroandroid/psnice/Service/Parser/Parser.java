package br.com.livroandroid.psnice.Service.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.livroandroid.psnice.Usuario;

/**
 * Created by livetouch on 13/09/18.
 */

public class Parser {

    public static Usuario parserUsuario(String jsonResponse) throws JSONException {
        Usuario usuario = new Usuario();
        JSONObject jsonObject = new JSONObject(jsonResponse);

        usuario.setPsnId(jsonObject.optString("psn_id"));
        usuario.setAvatar(jsonObject.optString("avatar"));
        usuario.setLevel(Integer.valueOf(jsonObject.optString("level")));
        usuario.setTotal(Integer.valueOf(jsonObject.optString("total")));
        usuario.setPlatinum(Integer.valueOf(jsonObject.optString("platinum")));
        usuario.setGold(Integer.valueOf(jsonObject.optString("gold")));
        usuario.setSilver(Integer.valueOf(jsonObject.optString("silver")));
        usuario.setBronze(Integer.valueOf(jsonObject.optString("bronze")));
        usuario.setProgress(Integer.valueOf(jsonObject.optString("progress")));

        return usuario;
    }
}
