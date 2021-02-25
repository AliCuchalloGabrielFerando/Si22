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
import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.Modelos.ProductoCarrito;
import com.ali.si2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdaptadorProductoCarrito extends RecyclerView.Adapter<AdaptadorProductoCarrito.ViewHolder> {
    private List<ProductoCarrito> listaProductos;
    private ItemClick itemClick;
    private Context context;

    public AdaptadorProductoCarrito(List<ProductoCarrito> listaProductos, ItemClick itemClick, Context context) {
        this.listaProductos = listaProductos;
        this.itemClick = itemClick;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_carrito, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       ProductoCarrito producto = listaProductos.get(position);
        holder.nombre.setText(producto.getNombre());
        holder.precio.setText(String.valueOf(producto.getPrecio()));
        holder.cantidad.setText("Cantidad: " + producto.getCantidadCompra());
        holder.marca.setText(producto.getNombreMarca());
      /*  if(producto.getDescuento() != 0){
            String descuento =
                    String.valueOf(producto.getPrecio() * ((100 -producto.getDescuento())/100)) ;
            SpannableStringBuilder spanBuilder = new SpannableStringBuilder( descuento+ " Bs");
            StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
            spanBuilder.setSpan(strikethroughSpan,
                    0, descuento.length()+3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.descuento.setText(String.valueOf(producto.getPrecio()));
            holder.precio.setText(spanBuilder);

        }else{*/
            holder.precio.setText(String.valueOf(producto.getPrecio()) +" $");
            holder.descuento.setVisibility(View.GONE);
     //   }

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
        TextView nombre, marca, precio,descuento, cantidad;
        ImageView imagen;
        ImageButton aumentar,disminuir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            marca = itemView.findViewById(R.id.marca);
            descuento = itemView.findViewById(R.id.descuento);
            precio = itemView.findViewById(R.id.precio);
            cantidad = itemView.findViewById(R.id.cantidad);
            imagen = itemView.findViewById(R.id.imagen);
            aumentar = itemView.findViewById(R.id.aumentar);
            disminuir = itemView.findViewById(R.id.disminuir);
            aumentar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listaProductos.get(getAdapterPosition()).getCantidadCompra() < listaProductos.get(getAdapterPosition()).getCantidad()){
                        itemClick.masOMenosProducto(getAdapterPosition(),1);
                    }
                }
            });
            disminuir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listaProductos.get(getAdapterPosition()).getCantidadCompra() > 1){
                        itemClick.masOMenosProducto(getAdapterPosition(),-1);

                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   itemClick.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemClick.onLongClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
