package com.ali.si2.Adaptadores;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.si2.Interfaces.ItemClick;
import com.ali.si2.Modelos.ProductoCarrito;
import com.ali.si2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdaptadorDetalleCompra extends RecyclerView.Adapter<AdaptadorDetalleCompra.ViewHolder> {
    private List<ProductoCarrito> listaProductos;

    public AdaptadorDetalleCompra(List<ProductoCarrito> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_compra, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       if(position==listaProductos.size()){
           double precio=0;
           int cantidad=0;
           for (ProductoCarrito productoCarrito:listaProductos) {
                precio=precio+productoCarrito.getPrecio();
                cantidad=cantidad+productoCarrito.getCantidad();
           }
           holder.nombre.setText("Total");
           holder.precio.setText(String.valueOf(precio));
           holder.cantidad.setText(String.valueOf(cantidad));
       }else {
           ProductoCarrito producto = listaProductos.get(position);
           holder.nombre.setText(producto.getNombre());
           holder.precio.setText(String.valueOf(producto.getPrecio()));
           holder.cantidad.setText(String.valueOf(producto.getCantidad()));
       }
    }

    @Override
    public int getItemCount() {
        return listaProductos.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, cantidad;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            cantidad = itemView.findViewById(R.id.cantidad);
            precio = itemView.findViewById(R.id.precio);

        }
    }
}
