package com.ali.si2.Fragmentos.DePreview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ali.si2.Interfaces.Filtro;
import com.ali.si2.Interfaces.ItemClick;
import com.ali.si2.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentBSCalificar extends BottomSheetDialogFragment {
    Filtro filtro;
    Button boton1,boton2;
    RatingBar rating;


    public FragmentBSCalificar(Filtro filtro) {
        this.filtro = filtro;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_eliminar,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rating = view.findViewById(R.id.calificacion);
        boton1 = view.findViewById(R.id.boton1);
        boton2 = view.findViewById(R.id.boton2);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rating.getRating() != 0){
                    Map<String,String> map = new HashMap<>();
                    map.put("calificar",String.valueOf((int)rating.getRating()));
                    filtro.onFiltro(map);
                    dismiss();
                }else{
                    Toast.makeText(getContext(), "no ha calificado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }



}
