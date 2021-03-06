package com.ali.si2.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.si2.Adaptadores.AdaptadorDetalleCompra;

import com.ali.si2.Adaptadores.AdaptadorProductoCarrito;
import com.ali.si2.Fragmentos.DeCarrito.FragmentBSEliminar;
import com.ali.si2.Fragmentos.DeCarrito.SheetTipoPago;
import com.ali.si2.Interfaces.Filtro;
import com.ali.si2.Interfaces.ItemClick;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Modelos.ProductoCarrito;
import com.ali.si2.Preview;
import com.ali.si2.R;
import com.ali.si2.VistaModelo.VMCarrito;
import com.ali.si2.VistaModelo.VMProducto;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentCarrito extends Fragment implements ItemClick {
    List<ProductoCarrito> listaProductos;
    AdaptadorProductoCarrito adaptador;
    VMCarrito vmCarrito;
    RecyclerView recycler;
    Button boton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrito,container,false);
        getActivity().setTitle("Carrito");
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.recycler);
        boton = view.findViewById(R.id.comprar);
        vmCarrito = new ViewModelProvider(this).get(VMCarrito.class);
        vmCarrito.initRepo(getContext());
        listaProductos = new ArrayList<>();
        adaptador =new AdaptadorProductoCarrito(listaProductos,this,getContext());
        recycler.setAdapter(adaptador);
        cargarCarrito();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SheetTipoPago bottomSheet = new SheetTipoPago(listaProductos, new Filtro() {
                    @Override
                    public void onFiltro(Map<String, String> map) {
                        adaptador.notifyDataSetChanged();
                        boton.setVisibility(View.GONE);
                    }
                });
                bottomSheet.show(getChildFragmentManager(),bottomSheet.getTag());
            }
        });

    }

    private void cargarCarrito() {
        vmCarrito.getProductos().observe(getViewLifecycleOwner(),productoCarritos -> {
            listaProductos.clear();
            listaProductos.addAll(productoCarritos);
            if (!listaProductos.isEmpty()){
                boton.setVisibility(View.VISIBLE);
            }
            adaptador.notifyDataSetChanged();
        });
    }

    @Override
    public void onClick(int posicion) {
        ProductoCarrito productoCarrito = listaProductos.get(posicion);
        Producto producto = new Producto(productoCarrito.getId(),productoCarrito.getNombre(),productoCarrito.getDescripcion(),
                productoCarrito.getPrecio(),productoCarrito.getUrl_imagen(),productoCarrito.getUrl_3d(),productoCarrito.getCalificacion(),
                productoCarrito.getCantidad(),productoCarrito.getGarantia_id());
        Intent intent = new Intent(getContext(), Preview.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto",producto);
        bundle.putSerializable("bandera",false);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onLongClick(int posicion) {
        FragmentBSEliminar fragmentBSEliminar = new FragmentBSEliminar(this,posicion);
        fragmentBSEliminar.show(getParentFragmentManager(),"Mostrar");

    }

    @Override
    public void eliminar(int posicion) {
        vmCarrito.eliminarProducto(listaProductos.get(posicion).getId());
        listaProductos.remove(posicion);
        adaptador.notifyDataSetChanged();
    }

    @Override
    public void masOMenosProducto(int posicion, int valor) {
        listaProductos.get(posicion).setCantidadCompra(listaProductos.get(posicion).getCantidadCompra()+valor);
        adaptador.notifyDataSetChanged();
        vmCarrito.actualizarCompraProducto(listaProductos.get(posicion).getId(),valor);
    }
}
