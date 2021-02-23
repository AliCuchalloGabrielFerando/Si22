package com.ali.si2.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ali.si2.Adaptadores.AdaptadorPadre;
import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Categoria;
import com.ali.si2.Modelos.Marca;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Preview;
import com.ali.si2.R;
import com.ali.si2.VistaModelo.VMProducto;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FragmentTienda extends Fragment implements ItemListenner {
    List<List<Producto>> listaPadre;
    List<Categoria> categorias;
    AdaptadorPadre adaptadorPadre;
    RecyclerView recyclerPadre;
    Marca marcas;
   // RecyclerView recycler;
    Producto producto;
    ImageView imagen;
    TextView nombre,marca;
    RatingBar rating;
    List<Producto> listaProducto;
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
        recyclerPadre = view.findViewById(R.id.recycler);
        imagen = view.findViewById(R.id.imagen);
        nombre = view.findViewById(R.id.nombre);
        marca = view.findViewById(R.id.marca);
        rating = view.findViewById(R.id.calificacion);
        listaPadre = new ArrayList<>();
        categorias = new ArrayList<>();
        adaptadorPadre = new AdaptadorPadre(listaPadre,this,categorias,getContext());
        recyclerPadre.setAdapter(adaptadorPadre);
        recyclerPadre.setNestedScrollingEnabled(false);

        listaProducto = new ArrayList<>();
        vmProducto = new ViewModelProvider(this).get(VMProducto.class);
        vmProducto.initRepo(getContext());
        //adaptadorProducto = new AdaptadorProducto(listaProducto,getContext());
        swipe = view.findViewById(R.id.swip);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Preview.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto",producto);
                bundle.putSerializable("marca",marcas);
                bundle.putSerializable("bandera",true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarCategoria();
                cargarMasVendido();
            }
        });
        cargarMasVendido();
        cargarCategoria();
//        recycler.setAdapter(adaptadorProducto);
        //cargarProductos(categoria);


    }

    private void cargarMasVendido() {
        vmProducto.getMasVendido().observe(getActivity(),stringObjectHashMap -> {
            producto = (Producto) stringObjectHashMap.get("producto");
            marcas = (Marca) stringObjectHashMap.get("marca");
            Glide.with(getContext()).load(producto.getUrl_imagen())
                    .into(imagen);
            nombre.setText(producto.getNombre());
            marca.setText(marcas.getNombre());
            rating.setRating(producto.getCalificacion());
        });


    }

    private void cargarCategoria() {
        vmProducto.getCategorias().observe(getActivity(),categorias1 -> {
            categorias.clear();
            categorias.addAll(categorias1);
            cargarProductos();
         //   Log.d("aqui",String.valueOf(categorias.size()));
        });
    }


    private void cargarProductos() {
        listaPadre.clear();
        for (Categoria categoria: categorias) {
        vmProducto.getProductos(categoria.getId()).observe(getViewLifecycleOwner(), productos -> {
            if(!productos.isEmpty()) {
                listaPadre.add(productos);
               // Log.d("aqui",String.valueOf(productos.size()));
                if(productos.size()>3)
              //  Log.d("aqui", String.valueOf(productos.get(2).getNombre()));
               /* listaProducto.clear();
                listaProducto.addAll(productos);
                adaptadorProducto.notifyDataSetChanged();*/
                adaptadorPadre.notifyDataSetChanged();
            }
        });
        }
    }



    @Override
    public void onClick(Producto producto) {
        Intent intent = new Intent(getContext(),Preview.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto",producto);
        bundle.putSerializable("bandera",false);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
