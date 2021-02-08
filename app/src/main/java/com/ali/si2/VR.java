package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ali.si2.Modelos.Producto;

public class VR extends AppCompatActivity {
    Producto producto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_r);
        producto = (Producto) getIntent().getExtras().getSerializable("producto");
        Log.d("aui producto",producto.getNombre());

    }
}