package br.com.livroandroid.psnice.Service.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.Jogo;
import br.com.livroandroid.psnice.Trofeu;
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
        usuario.setRankMundial(Integer.valueOf(jsonObject.optString("worldrank")));
        usuario.setRankPais(Integer.valueOf(jsonObject.optString("regionrank")));
        usuario.setEficiencia(Float.valueOf(jsonObject.optString("completion_percentage")));
        usuario.setTotalTrofeuPossivel(Integer.valueOf(jsonObject.optString("total_possible_trophies")));
        usuario.setPs3Games(Integer.valueOf(jsonObject.optString("total_game_ps3")));
        usuario.setPs4Games(Integer.valueOf(jsonObject.optString("total_game_ps4")));
        usuario.setPsVitaGames(Integer.valueOf(jsonObject.optString("total_game_vita")));

        return usuario;
    }

    public static List<Jogo> parserJogos(String jsonResponse) throws JSONException {
        List<Jogo> lista = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray jsonArray = (JSONArray) jsonObject.get("jogos");
        for (int i = 0; i < jsonArray.length(); i++) {
            Jogo jogo = new Jogo();
            JSONObject jsonObjectJogo = jsonArray.getJSONObject(i);
            jogo.setNome(jsonObjectJogo.optString("nome"));
            jogo.setImagem(jsonObjectJogo.optString("imagem"));
            jogo.setIdJogo(jsonObjectJogo.optString("id"));
            jogo.setAPs4Game(jsonObjectJogo.optBoolean("ps4_label"));
            jogo.setAPs3Game(jsonObjectJogo.optBoolean("ps3_label"));
            jogo.setAPsVitaGame(jsonObjectJogo.optBoolean("psv_label"));
            jogo.setHasPlatinum(Boolean.parseBoolean(jsonObjectJogo.optString("has_platinum")));
            jogo.setGamePlatinum(Integer.valueOf(jsonObjectJogo.optString("game_platinum")));
            jogo.setGameGold(Integer.valueOf(jsonObjectJogo.optString("game_gold")));
            jogo.setGameSilver(Integer.valueOf(jsonObjectJogo.optString("game_silver")));
            jogo.setGameBronze(Integer.valueOf(jsonObjectJogo.optString("game_bronze")));
            jogo.setGameTotal(Integer.valueOf(jsonObjectJogo.optString("game_total")));
            jogo.setGameTotalEarned(Integer.valueOf(jsonObjectJogo.optString("game_total_earned")));
            jogo.setGameProgress(Integer.valueOf(jsonObjectJogo.optString("game_progress")));
            jogo.setDesenvolvedora(jsonObjectJogo.optString("developer"));
            jogo.setGenero(jsonObjectJogo.optString("genre"));
            jogo.setQuantidadeJogadores(jsonObjectJogo.optString("players_upto"));
            lista.add(jogo);
        }
        return lista;
    }

    public static List<Jogo> parserTodosJogos(String jsonResponse) throws JSONException {
        List<Jogo> lista = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray jsonArray = (JSONArray) jsonObject.get("jogos");
        for (int i = 0; i < jsonArray.length(); i++) {
            Jogo jogo = new Jogo();
            JSONObject jsonObjectJogo = jsonArray.getJSONObject(i);
            jogo.setNome(jsonObjectJogo.optString("nome"));
            jogo.setImagem(jsonObjectJogo.optString("imagem"));
            jogo.setIdJogo(jsonObjectJogo.optString("id"));
            jogo.setHasPlatinum(Boolean.parseBoolean(jsonObjectJogo.optString("has_platinum")));
            jogo.setGamePlatinum(Integer.valueOf(jsonObjectJogo.optString("game_platinum")));
            jogo.setGameGold(Integer.valueOf(jsonObjectJogo.optString("game_gold")));
            jogo.setAPs4Game(jsonObjectJogo.optBoolean("ps4_label"));
            jogo.setAPs3Game(jsonObjectJogo.optBoolean("ps3_label"));
            jogo.setAPsVitaGame(jsonObjectJogo.optBoolean("psv_label"));
            jogo.setGameSilver(Integer.valueOf(jsonObjectJogo.optString("game_silver")));
            jogo.setGameBronze(Integer.valueOf(jsonObjectJogo.optString("game_bronze")));
            jogo.setGameTotal(Integer.valueOf(jsonObjectJogo.optString("game_total")));
            jogo.setGameTotalEarned(Integer.valueOf(jsonObjectJogo.optString("game_total_earned")));
            jogo.setGameProgress(Integer.valueOf(jsonObjectJogo.optString("game_progress")));
            jogo.setDesenvolvedora(jsonObjectJogo.optString("developer"));
            jogo.setGenero(jsonObjectJogo.optString("genre"));
            jogo.setQuantidadeJogadores(jsonObjectJogo.optString("players_upto"));
            lista.add(jogo);
        }
        return lista;
    }

    public static List<Usuario> parserTodosUsuarios(String jsonResponse) throws JSONException {
        List<Usuario> lista = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray jsonArray = (JSONArray) jsonObject.get("usuarios");
        for (int i = 0; i < jsonArray.length(); i++) {
            Usuario usuario = new Usuario();
            JSONObject jsonObjectUsuario = jsonArray.getJSONObject(i);
            usuario.setAvatar(jsonObjectUsuario.optString("avatar"));
            usuario.setLevel(jsonObjectUsuario.optInt("level"));
            usuario.setPsnId(jsonObjectUsuario.getString("psn_id"));
            lista.add(usuario);
        }
        return lista;
    }

    public static List<Jogo> parserListaJogos(String jsonResponse) throws JSONException {
        List<Jogo> lista = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray jsonArray = (JSONArray) jsonObject.get("jogos");
        for (int i = 0; i < jsonArray.length(); i++) {
            Jogo jogo = new Jogo();
            JSONObject jsonObjectJogo = jsonArray.getJSONObject(i);
            jogo.setNome(jsonObjectJogo.optString("nome"));
            jogo.setIdJogo(jsonObjectJogo.optString("id"));
            jogo.setAPs4Game(jsonObjectJogo.optBoolean("ps4_label"));
            jogo.setAPs3Game(jsonObjectJogo.optBoolean("ps3_label"));
            jogo.setAPsVitaGame(jsonObjectJogo.optBoolean("psv_label"));
            jogo.setImagem(jsonObjectJogo.optString("imagem"));
            jogo.setGameTotal(Integer.valueOf(jsonObjectJogo.optString("game_total")));
            jogo.setDesenvolvedora(jsonObjectJogo.optString("developer"));
            jogo.setGenero(jsonObjectJogo.optString("genre"));
            lista.add(jogo);
        }
        return lista;
    }

    public static List<Trofeu> parserTrofeus(String jsonResponse, String nomeJogo) throws JSONException {
        List<Trofeu> lista = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray jsonArray = (JSONArray) jsonObject.get("jogos");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObjectJogo = jsonArray.getJSONObject(i);
            String jogo = jsonObjectJogo.optString("nome");
            if (nomeJogo.equalsIgnoreCase(jogo)){
                JSONArray jsonArrayTrofeus = (JSONArray) jsonObjectJogo.get("trofeus");
                for (int t = 0; t < jsonArrayTrofeus.length(); t++){
                    JSONObject jsonObjectTrofeu = jsonArrayTrofeus.getJSONObject(t);
                    Trofeu trofeu = new Trofeu();
                    trofeu.setNome(jsonObjectTrofeu.optString("nome"));
                    trofeu.setDescricao(jsonObjectTrofeu.optString("descricao"));
                    trofeu.setImagemTrofeu(jsonObjectTrofeu.optString("imagem_trofeu"));
                    trofeu.setTipo(jsonObjectTrofeu.optString("tipo"));
                    trofeu.setHidden(Boolean.parseBoolean(jsonObjectTrofeu.optString("hidden")));
                    trofeu.setEarned(Boolean.parseBoolean(jsonObjectTrofeu.optString("earned")));
                    trofeu.setDayEarned(jsonObjectTrofeu.optString("day_earned"));
                    trofeu.setHourEarned(jsonObjectTrofeu.optString("hour_earned"));
                    lista.add(trofeu);
                }
            }
        }
        return lista;
    }

    public static List<Trofeu> parserTrofeusVazio(String jsonResponse, String nomeJogo) throws JSONException {
        List<Trofeu> lista = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray jsonArray = (JSONArray) jsonObject.get("jogos");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObjectJogo = jsonArray.getJSONObject(i);
            String jogo = jsonObjectJogo.optString("nome");
            if (nomeJogo.equalsIgnoreCase(jogo)){
                JSONArray jsonArrayTrofeus = (JSONArray) jsonObjectJogo.get("trofeus");
                for (int t = 0; t < jsonArrayTrofeus.length(); t++){
                    JSONObject jsonObjectTrofeu = jsonArrayTrofeus.getJSONObject(t);
                    Trofeu trofeu = new Trofeu();
                    trofeu.setNome(jsonObjectTrofeu.optString("nome"));
                    trofeu.setDescricao(jsonObjectTrofeu.optString("descricao"));
                    trofeu.setImagemTrofeu(jsonObjectTrofeu.optString("imagem_trofeu"));
                    trofeu.setTipo(jsonObjectTrofeu.optString("tipo"));
                    trofeu.setHidden(Boolean.parseBoolean(jsonObjectTrofeu.optString("hidden")));
                    trofeu.setEarned(Boolean.parseBoolean(jsonObjectTrofeu.optString("earned")));
                    trofeu.setDayEarned(jsonObjectTrofeu.optString("day_earned"));
                    trofeu.setHourEarned(jsonObjectTrofeu.optString("hour_earned"));
                    lista.add(trofeu);
                }
            }
        }
        return lista;
    }
}
