package com.ali.si2.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.si2.Interfaces.ItemClick;
import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Categoria;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.R;

import java.util.List;

public class AdaptadorPadre extends RecyclerView.Adapter<AdaptadorPadre.ViewHolder> {
    List<List<Producto>> listaPadreProductos;
    ItemListenner itemListenner;
    List<Categoria> categorias;
    Context context;


    public AdaptadorPadre(List<List<Producto>> listaPadreProductos, ItemListenner itemListenner, List<Categoria> categorias, Context context) {
        this.listaPadreProductos = listaPadreProductos;
        this.itemListenner = itemListenner;
        this.categorias = categorias;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<Producto> listaProductos = listaPadreProductos.get(position);
        AdaptadorProducto adaptadorProducto = new AdaptadorProducto(listaProductos,itemListenner,context);
        holder.recyclerHijo.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerHijo.setAdapter(adaptadorProducto);
        holder.categoria.setText("");
        holder.categoria.setText(categorias.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return listaPadreProductos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoria;
        RecyclerView recyclerHijo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoria = itemView.findViewById(R.id.categoria);
            recyclerHijo = itemView.findViewById(R.id.recycler_hijo);
        }
    }
}
