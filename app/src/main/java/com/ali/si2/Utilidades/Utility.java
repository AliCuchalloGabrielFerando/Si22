package com.ali.si2.Utilidades;

import android.content.Context;
import android.content.SharedPreferences;

public class Utility {
    public static String readToken(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("userToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        return token;
    }
}
