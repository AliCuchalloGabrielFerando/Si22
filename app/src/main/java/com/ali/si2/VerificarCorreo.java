package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ali.si2.VistaModelo.VMUser;

public class VerificarCorreo extends AppCompatActivity {
    EditText correo;
    VMUser vmUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_correo);
        correo = findViewById(R.id.correo);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(this);
    }

    public void verificar(View view) {

        if (!correo.getText().toString().isEmpty() && correo.getText().toString().contains("@")) {
            Log.d("aqui",correo.getText().toString());
            vmUser.validar(correo.getText().toString()).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){
                        Log.d("error","paso por la acrividad verificar");
                        Intent intent = new Intent(getApplicationContext(), RecuperarCuentaFinal.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("correo",correo.getText().toString());
                        bundle.putSerializable("bandera","false");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(VerificarCorreo.this, "intente de nuevo", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}