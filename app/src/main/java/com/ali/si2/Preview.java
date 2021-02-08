package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ali.si2.Modelos.Producto;

public class Preview extends AppCompatActivity {
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        producto = (Producto) getIntent().getExtras().getSerializable("producto");

    }

    public void agregarACarrito(View view) {

    }

    public void verEn3D(View view) {
        Intent intent = new Intent(this,VR.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto",producto);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}