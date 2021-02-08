package com.ali.si2.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ali.si2.Adaptadores.AdaptadorProducto;
import com.ali.si2.Interfaces.ItemClick;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Preview;
import com.ali.si2.R;
import com.ali.si2.VistaModelo.VMProducto;

import java.util.ArrayList;
import java.util.List;

public class FragmentTienda extends Fragment implements ItemClick {
    RecyclerView recycler;
    Button boton;
    List<Producto> listaProducto;
    AdaptadorProducto adaptadorProducto;
    SwipeRefreshLayout swipe;
    VMProducto vmProducto;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tienda, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.recycler);
        boton = view.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        listaProducto = new ArrayList<>();
        vmProducto = new ViewModelProvider(this).get(VMProducto.class);
        vmProducto.initRepo(getContext());
        adaptadorProducto = new AdaptadorProducto(listaProducto,this,getContext());
        swipe = view.findViewById(R.id.swip);
        int categoria=1;

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarProductos(categoria);
            }
        });

        recycler.setAdapter(adaptadorProducto);
        cargarProductos(categoria);


    }

    private void cargarProductos(int categoria) {

        vmProducto.getProductos(categoria).observe(this, productos -> {
            listaProducto.clear();
            listaProducto.addAll(productos);
            adaptadorProducto.notifyDataSetChanged();
        });
    }

    @Override
    public void onClick(int posicion) {

        Intent intent = new Intent(getContext(),Preview.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto",listaProducto.get(posicion));
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
