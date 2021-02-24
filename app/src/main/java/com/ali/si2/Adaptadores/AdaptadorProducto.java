package com.ali.si2.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.si2.Interfaces.ItemClick;
import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolder> {
    private List<Producto> listaProductos;
    private ItemListenner itemListenner;
    private Context context;


    public AdaptadorProducto(List<Producto> listaProductos, ItemListenner itemListenner, Context context) {
        this.listaProductos = listaProductos;
        this.itemListenner = itemListenner;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.nombre.setText(producto.getNombre());
        holder.precio.setText(String.valueOf(producto.getPrecio()));
        holder.calificacion.setText(String.valueOf(producto.getCalificacion()));
        holder.cantidad.setText(String.valueOf(producto.getCantidad()));

        Glide.with(context)
                .load(producto.getUrl_imagen())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(25)))
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, calificacion, cantidad;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            precio = itemView.findViewById(R.id.precio);
            calificacion = itemView.findViewById(R.id.calificacion);
            cantidad = itemView.findViewById(R.id.cantidad);
            imagen = itemView.findViewById(R.id.imagen);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListenner.onClick(listaProductos.get(getAdapterPosition()));
                }
            });
        }
    }
}
