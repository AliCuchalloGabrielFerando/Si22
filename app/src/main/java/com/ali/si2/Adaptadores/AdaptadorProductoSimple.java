package com.ali.si2.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class    AdaptadorProductoSimple extends RecyclerView.Adapter<AdaptadorProductoSimple.ViewHolder> {
    private List<Producto> listaProductos;
    private ItemListenner itemListenner;
    private Context context;


    public AdaptadorProductoSimple(List<Producto> listaProductos, ItemListenner itemListenner, Context context) {
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

        Glide.with(context)
                .load(producto.getUrl_imagen())
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

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
