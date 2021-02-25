package com.ali.si2.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.si2.Adaptadores.AdaptadorProductoSimple;
import com.ali.si2.Fragmentos.DeBusqueda.FragmentBSFiltro;
import com.ali.si2.Interfaces.Filtro;
import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Preview;
import com.ali.si2.R;
import com.ali.si2.VistaModelo.VMBusqueda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//fecha calificacion y mas vendido
public class FragmentBusqueda extends Fragment implements ItemListenner, Filtro {
    SearchView search;
    ImageView filtro;
    VMBusqueda vmBusqueda;
    List<Producto> listaProductos;
    AdaptadorProductoSimple adaptador;
    RecyclerView recycler;
    String nombre;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busqueda,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.search);
        filtro = view.findViewById(R.id.filtro);
        recycler = view.findViewById(R.id.recycler);
        vmBusqueda = new ViewModelProvider(this).get(VMBusqueda.class);
        vmBusqueda.initRepo(getContext());
        listaProductos = new ArrayList<>();
        adaptador = new AdaptadorProductoSimple(listaProductos, this, getContext());
        recycler.setAdapter(adaptador);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // filtro.setVisibility(View.VISIBLE);
                nombre = query;
                View view = getActivity().getCurrentFocus();
                if (view != null){
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
                }
                vmBusqueda.getProductos(nombre).observe(getViewLifecycleOwner(),productos ->{
                    listaProductos.clear();
                    listaProductos.addAll(productos);
                    adaptador.notifyDataSetChanged();
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentBSFiltro bsFiltro = new FragmentBSFiltro(FragmentBusqueda.this::onFiltro);
                bsFiltro.show(getParentFragmentManager(),"ejemplo");
                View view = getActivity().getCurrentFocus();
                if (view != null){
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
                }
            }
        });
    }



    @Override
    public void onClick(Producto producto) {
        Intent intent = new Intent(getContext(), Preview.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto", producto);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onFiltro(Map<String, String> map) {
        Toast.makeText(getContext(), "vamos vien", Toast.LENGTH_SHORT).show();
        Log.d("tagg",map.get("fecha inicio"));
        Log.d("tagg",map.get("fecha fin"));
        Log.d("tagg",map.get("valorado"));
        Log.d("tagg",map.get("vendido"));
        map.put("nombre",nombre);
        vmBusqueda.getProductosFiltrado(map).observe(getViewLifecycleOwner(),productos -> {
            listaProductos.clear();
            listaProductos.addAll(productos);
            adaptador.notifyDataSetChanged();
        });
    }
}
