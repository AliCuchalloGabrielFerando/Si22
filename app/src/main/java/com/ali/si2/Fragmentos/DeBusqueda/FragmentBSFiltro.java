package com.ali.si2.Fragmentos.DeBusqueda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentBSFiltro extends BottomSheetDialogFragment {
    Producto producto;
    ItemListenner itemListenner;
    Button boton1,boton2;

    public FragmentBSFiltro(ItemListenner itemListenner) {
        this.itemListenner = itemListenner;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_filtro,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boton1 = view.findViewById(R.id.boton1);
        boton2 = view.findViewById(R.id.boton2);
        producto = new Producto();
        producto.setNombre("hola");
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListenner.onClick(producto);
                dismiss();
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListenner.onClick(producto);
                dismiss();
            }
        });

    }



}
