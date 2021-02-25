package com.ali.si2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ali.si2.Adaptadores.AdaptadorProducto;
import com.ali.si2.Adaptadores.AdaptadorProductoSimple;
import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Agregado;
import com.ali.si2.Modelos.Garantia;
import com.ali.si2.Modelos.Marca;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Modelos.Promocion;
import com.ali.si2.VistaModelo.VMProducto;
import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import static com.ali.si2.R.color.color_primary;

public class Preview extends AppCompatActivity implements ItemListenner {
    Producto producto;
    Marca marcas;
    Garantia garantias;
    Promocion promociones;
    Boolean bandera;
    ImageView imagen;
    ImageButton agregarACarrito;
    Agregado agregado;
    RatingBar rating;
    TextView nombre, precio, cantidad, promocion, descripcion, garantia;
    Chip empresa, marca;
    List<Producto> listaProductos;
    AdaptadorProductoSimple adaptador;
    RecyclerView recycler;
    VMProducto vmProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        rating = findViewById(R.id.calificacion);
        imagen = findViewById(R.id.imagen);
        nombre = findViewById(R.id.nombre);
        precio = findViewById(R.id.precio);
        cantidad = findViewById(R.id.cantidad);
        promocion = findViewById(R.id.descuento);
        descripcion = findViewById(R.id.descripcion);
        garantia = findViewById(R.id.garantia);
        empresa = findViewById(R.id.empresa);
        marca = findViewById(R.id.marca);
        agregarACarrito = findViewById(R.id.carrito);
        agregado = new Agregado();
        vmProducto = new ViewModelProvider(this).get(VMProducto.class);
        vmProducto.initRepo(this);
        //poner rayado todo el contenido del texview
        //descuento.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        producto = (Producto) getIntent().getExtras().getSerializable("producto");
        bandera = (Boolean) getIntent().getExtras().getSerializable("bandera");
        if (bandera) {
            marcas = (Marca) getIntent().getExtras().getSerializable("marca");
        }
        listaProductos = new ArrayList<>();
        adaptador = new AdaptadorProductoSimple(listaProductos, this, this);
        recycler = findViewById(R.id.recycler);
        recycler.setAdapter(adaptador);
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        cargarPreview();
        cargarSimilares();
    }

    private void cargarPreview() {
        Glide.with(this).load(producto.getUrl_imagen()).into(imagen);
        nombre.setText("");
        nombre.setText(producto.getNombre());
        cantidad.setText(String.valueOf(producto.getCantidad()));
        descripcion.setText("");
        descripcion.setText(producto.getDescripcion());
        marca.setText("");

         vmProducto.datosExtraDeProducto(producto.getId()).observe(this,stringObjectHashMap -> {
                marcas = (Marca) stringObjectHashMap.get("marca");
                garantias = (Garantia) stringObjectHashMap.get("garantia");
                promociones = (Promocion) stringObjectHashMap.get("promocion");
                agregado = (Agregado) stringObjectHashMap.get("agregado");

                if (!bandera) {
                    marca.setText(marcas.getNombre());
                }
                if(agregado.getAgregado()){
                    agregarACarrito.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimary));
                }

                garantia.setText("");
                garantia.setText(String.valueOf(garantias.getTiempo()));
                rating.setRating(producto.getCalificacion());

                promocion.setText("");
                if(promociones != null){
                    String descuento =
                           String.valueOf(producto.getPrecio() * ((100 -promociones.getDescuento())/100)) ;
                    SpannableStringBuilder spanBuilder = new SpannableStringBuilder( descuento+ " Bs");
                    StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                    spanBuilder.setSpan(strikethroughSpan,
                            0, descuento.length()+3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    promocion.setText(String.valueOf(producto.getPrecio()));
                    precio.setText(spanBuilder);

                }else{
                    precio.setText(String.valueOf(producto.getPrecio()));
                }

            });


    }

    private void cargarSimilares() {
        vmProducto.getProductos(producto).observe(this, productos -> {
            listaProductos.clear();
            listaProductos.addAll(productos);
            adaptador.notifyDataSetChanged();
        });

    }

    public void agregarACarrito(View view) {
        if(!agregado.getAgregado()){
            agregarACarrito.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimary));
            vmProducto.agregarACarrito(producto.getId());
            agregado.setAgregado(true);
        }
        //  agregarACarrito.setBackground(this.getResources().getDrawable(R.drawable.border_redondeado_primary));
    }

    public void verEn3D(View view) {
        Intent intent = new Intent(this, VR.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto", producto);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onClick(Producto producto) {
        Intent intent = new Intent(this, Preview.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto", producto);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}