package com.ali.si2.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ali.si2.Fragmentos.DeBusqueda.FragmentBSFiltro;
import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.R;

//fecha calificacion y mas vendido
public class FragmentBusqueda extends Fragment implements ItemListenner {
    Button boton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busqueda,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boton = view.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentBSFiltro bsFiltro = new FragmentBSFiltro(FragmentBusqueda.this::onClick);

                bsFiltro.show(getParentFragmentManager(),"ejemplo");
            }
        });

    }

    private void toque() {
    }

    @Override
    public void onClick(Producto producto) {
        boton.setText(producto.getNombre());
    }
}
