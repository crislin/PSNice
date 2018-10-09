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

import br.com.livroandroid.psnice.BaseTask;
import br.com.livroandroid.psnice.Service.Parser.Parser;
import br.com.livroandroid.psnice.Task;
import br.com.livroandroid.psnice.Usuario;

import static br.com.livroandroid.psnice.Service.WebService.IS_FAKE;

/**
 * Created by livetouch on 13/09/18.
 */

public class PSNiceService {

    public static Usuario getUsuario(Context context) throws JSONException {
        Usuario usuario;
        String jsonResponse;
        try {
            if (IS_FAKE){
                jsonResponse = loadJSONFromAsset(context, "PSNice.json");
                usuario = Parser.parserUsuario(jsonResponse);
                if (usuario != null){
                    return usuario;
                }
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

