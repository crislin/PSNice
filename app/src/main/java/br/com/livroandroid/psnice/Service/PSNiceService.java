package br.com.livroandroid.psnice.Service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.BaseTask;
import br.com.livroandroid.psnice.Jogo;
import br.com.livroandroid.psnice.Service.Parser.Parser;
import br.com.livroandroid.psnice.Task;
import br.com.livroandroid.psnice.Trofeu;
import br.com.livroandroid.psnice.Usuario;

import static br.com.livroandroid.psnice.Service.WebService.IS_FAKE;

/**
 * Created by livetouch on 13/09/18.
 */

public class PSNiceService {

    public static Usuario getUsuario(Context context, String psnId) throws JSONException {
        Usuario usuario;
        String jsonResponse;
        try {
            String fileName = "";
            if (psnId.equalsIgnoreCase("vinte_dos_22")){
                fileName = "UsuarioCassiano.json";
            }
            if (psnId.equalsIgnoreCase("debli23")){
                fileName = "UsuarioAndre.json";
            }
            if (psnId.equalsIgnoreCase("Thigo23")){
                fileName = "UsuarioThiago.json";
            }
            jsonResponse = loadJSONFromAsset(context, fileName);
            usuario = Parser.parserUsuario(jsonResponse);
            if (usuario != null){
                return usuario;
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public static List<Jogo> getJogos(Context context, String psnId) throws JSONException {
        List<Jogo> lista = new ArrayList<>();
        String jsonResponse;
        try {
            String fileName = "";
            if (psnId.equalsIgnoreCase("vinte_dos_22")){
                fileName = "UsuarioCassiano.json";
            }
            if (psnId.equalsIgnoreCase("debli23")){
                fileName = "UsuarioAndre.json";
            }
            if (psnId.equalsIgnoreCase("Thigo23")){
                fileName = "UsuarioThiago.json";
            }
            jsonResponse = loadJSONFromAsset(context, fileName);
            lista = Parser.parserJogos(jsonResponse);
            if (lista != null){
                return lista;
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public static List<Jogo> getTodosJogos(Context context) throws JSONException {
        List<Jogo> lista = new ArrayList<>();
        String jsonResponse;
        try {
            jsonResponse = loadJSONFromAsset(context, "TodosOsJogosv2.json");
            lista = Parser.parserTodosJogos(jsonResponse);
            if (lista != null){
                return lista;
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public static List<Trofeu> getTrofeus(Context context, String nomeJogo, String psnId) throws JSONException {
        List<Trofeu> lista = new ArrayList<>();
        String jsonResponse;
        try {
            String fileName = "";
            if (psnId.equalsIgnoreCase("vinte_dos_22")){
                fileName = "UsuarioCassiano.json";
            }
            if (psnId.equalsIgnoreCase("debli23")){
                fileName = "UsuarioAndre.json";
            }
            if (psnId.equalsIgnoreCase("Thigo23")){
                fileName = "UsuarioThiago.json";
            }
            jsonResponse = loadJSONFromAsset(context, fileName);
            lista = Parser.parserTrofeus(jsonResponse, nomeJogo);
            if (lista != null){
                return lista;
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public static List<Trofeu> getTrofeus(Context context, String nomeJogo) throws JSONException {
        List<Trofeu> lista = new ArrayList<>();
        String jsonResponse;
        try {
            jsonResponse = loadJSONFromAsset(context, "TodosOsJogosv2.json");
            lista = Parser.parserTrofeusVazio(jsonResponse, nomeJogo);
            if (lista != null){
                return lista;
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public static List<Usuario> getListaUsuarios(Context context) throws JSONException {
        List<Usuario> lista = new ArrayList<>();
        String jsonResponse;
        try {
            jsonResponse = loadJSONFromAsset(context, "TodosUsuarios.json");
            lista = Parser.parserTodosUsuarios(jsonResponse);
            if (lista != null){
                return lista;
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public static List<Jogo> getListaJogos(Context context) throws JSONException {
        List<Jogo> lista = new ArrayList<>();
        String jsonResponse;
        try {
            jsonResponse = loadJSONFromAsset(context, "TodosJogosPesquisav2.json");
            lista = Parser.parserListaJogos(jsonResponse);
            if (lista != null){
                return lista;
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static Bitmap downloadfile(String fileurl) {
        Bitmap bmImg = null;
        URL myfileurl = null;
        try {
            myfileurl = new URL(fileurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myfileurl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            int length = conn.getContentLength();
            if (length > 0) {
                int[] bitmapData = new int[length];
                byte[] bitmapData2 = new byte[length];
                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);
                return bmImg;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Task baixaImagem(String url){
        return new BaseTask() {
            @Override
            public void execute() throws Exception {

            }

            @Override
            public void updateView() {

            }
        };
    }
}

