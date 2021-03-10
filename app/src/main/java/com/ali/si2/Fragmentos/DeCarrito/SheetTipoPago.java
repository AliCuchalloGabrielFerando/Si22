package com.ali.si2.Fragmentos.DeCarrito;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.si2.Adaptadores.AdaptadorDetalleCompra;
import com.ali.si2.Fragmentos.FragmentBusqueda;
import com.ali.si2.Interfaces.Filtro;
import com.ali.si2.Modelos.ProductoCarrito;
import com.ali.si2.Modelos.Tarjeta;
import com.ali.si2.R;
import com.ali.si2.VistaModelo.VMCompra;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.w3c.dom.EntityReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SheetTipoPago  extends BottomSheetDialogFragment {
//    EditText numero,anio,mes,cvv;
List<ProductoCarrito> listaProductos;
    VMCompra vmCompra;
    BottomSheetDialog bottomSheet;
    Stack<View> stack;
    Filtro filtro;
    public SheetTipoPago(List<ProductoCarrito> listaProductos, Filtro filtro) {
        this.listaProductos = listaProductos;
        this.filtro=filtro;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        stack=new Stack<>();
        bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        //inflating layout
        View view = View.inflate(getContext(), R.layout.bottom_sheet_metodo_pago, null);
        vmCompra=new ViewModelProvider(this).get(VMCompra.class);
        vmCompra.initRepo(getContext());


        /*numero=view.findViewById(R.id.numero);
        anio=view.findViewById(R.id.anio);
        mes=view.findViewById(R.id.mes);
        cvv=view.findViewById(R.id.cvv);*/
        view.findViewById(R.id.cerrar).setOnClickListener(v -> {dismiss();});
        view.findViewById(R.id.tarjeta).setOnClickListener(v -> {regsitrarTarjeta();stack.push(view);});
        view.findViewById(R.id.paypal).setOnClickListener(v -> {comprarPaypal();stack.push(view);});

        bottomSheet.setContentView(view);


        return bottomSheet;
    }

    public void regsitrarTarjeta(){
        EditText numero,anio,mes,cvv;
        ProgressBar progressBar;
        View view = View.inflate(getContext(), R.layout.bottom_sheet_tarjeta, null);
        bottomSheet.setContentView(view);
        progressBar=view.findViewById(R.id.progress);
        numero=view.findViewById(R.id.numero);
        anio=view.findViewById(R.id.anio);
        mes=view.findViewById(R.id.mes);
        cvv=view.findViewById(R.id.cvv);

        vmCompra.obtenerTarjeta().observe(this, sw->{
            progressBar.setVisibility(View.GONE);
            if (sw!=null){
                comprar(sw);
                stack.push(view);
            }
        });

        view.findViewById(R.id.cerrar).setOnClickListener(v -> {dismiss();});
        view.findViewById(R.id.atras).setOnClickListener(v -> {bottomSheet.setContentView(stack.pop()); });
        view.findViewById(R.id.aceptar).setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            Map<String,Object> map=new HashMap<>();
            map.put("numero",numero.getText().toString());
            map.put("fecha",anio.getText().toString()+"-"+mes.getText().toString()+"-"+"01");
            map.put("cvv",cvv.getText().toString());




            vmCompra.registrarTarjeta(map).observe(this, sw->{
                progressBar.setVisibility(View.GONE);
                if (sw!=null){
                    Toast.makeText(getContext(), "Tarjeta registrada", Toast.LENGTH_SHORT).show();
                    comprar(sw);
                    stack.push(view);
                }else{
                    Toast.makeText(getContext(), "Intente otra vez", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }

    public void comprar(Tarjeta tarjeta) {
        View view = View.inflate(getContext(), R.layout.bottom_sheet_confirmar_pago, null);
        EditText direccion, telefono, card;
        Button pagar=view.findViewById(R.id.pagar);
        direccion = view.findViewById(R.id.direccion);
        telefono = view.findViewById(R.id.telefono);
        card = view.findViewById(R.id.tarjeta);
        String numero=String.valueOf(tarjeta.getNumero());
        card.setText("****"+numero.substring(numero.length()-4));
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map=new HashMap<>();
                map.put("direccion",direccion.getText().toString());
                map.put("telefono",telefono.getText().toString());
                map.put("tipo_pago",1);
                map.put("detalles",toMap(listaProductos));
                map.put("carrito_id",listaProductos.get(0).getCarrito_id());
                reggistrarCompra(map);
            }
        });
        bottomSheet.setContentView(view);

        AdaptadorDetalleCompra adaptadorDetalleCompra=new AdaptadorDetalleCompra(listaProductos);
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorDetalleCompra);

        view.findViewById(R.id.cerrar).setOnClickListener(v -> {dismiss();});
        view.findViewById(R.id.atras).setOnClickListener(v -> {bottomSheet.setContentView(stack.pop()); });
    }
    private List<HashMap<String,Object>> toMap(List<ProductoCarrito> listaProductos) {
        List<HashMap<String,Object>> maps=new ArrayList<>();
        for (ProductoCarrito productoCarrito:listaProductos){
            String descuento=String.valueOf(productoCarrito.getPrecio());
            if(productoCarrito.getDescuento() != 0) {
                double precioss = 100 - productoCarrito.getDescuento();
                precioss = precioss / 100;
                precioss = productoCarrito.getPrecio() * precioss;
                descuento =String.valueOf(precioss);
            }
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",productoCarrito.getId());
            map.put("cantidadCompra",productoCarrito.getCantidadCompra());
            map.put("precio",descuento);
            map.put("promocion_id",productoCarrito.getDescuento_id());
            maps.add(map);
        }
        return maps;
    }
    public void comprarPaypal() {
        View view = View.inflate(getContext(), R.layout.bottom_sheet_confirmar_pago_paypal, null);
        EditText direccion, telefono;
        Button pagar=view.findViewById(R.id.pagar);
        direccion = view.findViewById(R.id.direccion);
        telefono = view.findViewById(R.id.telefono);
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map=new HashMap<>();
                map.put("direccion",direccion.getText().toString());
                map.put("telefono",telefono.getText().toString());
                map.put("tipo_pago",2);
                map.put("detalles",toMap(listaProductos));
                map.put("carrito_id",listaProductos.get(0).getCarrito_id());
                reggistrarCompra(map);
            }
        });
        bottomSheet.setContentView(view);

        AdaptadorDetalleCompra adaptadorDetalleCompra=new AdaptadorDetalleCompra(listaProductos);
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorDetalleCompra);

        view.findViewById(R.id.cerrar).setOnClickListener(v -> {dismiss();});
        view.findViewById(R.id.atras).setOnClickListener(v -> {bottomSheet.setContentView(stack.pop()); });
    }


    public void reggistrarCompra(Map<String,Object> map){
        vmCompra.compra(map).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    Toast.makeText(getContext(), "Compra exitosa", Toast.LENGTH_SHORT).show();
                    listaProductos.clear();
                    filtro.onFiltro(null);
                    dismiss();
                }else {
                    Toast.makeText(getContext(), "Intente otra vez", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
