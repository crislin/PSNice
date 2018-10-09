package br.com.livroandroid.psnice.Application;

import android.app.Application;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import br.com.livroandroid.psnice.ConfiguracaoFirebase;

/**
 * Created by livetouch on 03/09/18.
 */



public class ApplicationPsNice extends Application {


    String idNice;

    @Override
    public void onCreate() {
        super.onCreate();
        ConfiguracaoFirebase.getFirebaseAutenticacao();
    }

    private ChildEventListener getEventFireBase() {
        return new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    if (dataSnapshot.getKey().equalsIgnoreCase("psnice-9121d")) {
                        DataSnapshot safe = dataSnapshot.child("psnId");
                        String idNice = (String) safe.getValue();
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public String getIdNice() {
        return idNice;
    }

    public void setIdNice(String idNice) {
        this.idNice = idNice;
    }

//    @GlideModule
//    public final class MyAppGlideModule extends AppGlideModule {}

}


