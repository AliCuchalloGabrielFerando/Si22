package com.ali.si2.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ali.si2.Login;
import com.ali.si2.R;
import com.ali.si2.VistaModelo.VMUser;

public class FragmentPerfil extends Fragment {
    VMUser vmUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vmUser = new ViewModelProvider(this).get(VMUser.class);
        vmUser.initRepo(getContext());
        (view.findViewById(R.id.boton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir(view);
            }
        });

    }
    public void salir(View view) {

        vmUser.logout().observe(this,value->{
            startActivity(new Intent(getContext(), Login.class));
            getActivity().finish();
        });
    }
}
