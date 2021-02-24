package com.ali.si2.Utilidades;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Utility {
    public static String readToken(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("userToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        return token;
    }
    public static void logD(){
        Log.d("TAGG","exitoso por aqui pasa");
    }
}
