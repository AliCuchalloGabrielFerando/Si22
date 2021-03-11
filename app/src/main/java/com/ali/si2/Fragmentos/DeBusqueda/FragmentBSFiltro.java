package com.ali.si2.Fragmentos.DeBusqueda;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ali.si2.Interfaces.Filtro;
import com.ali.si2.Interfaces.ItemListenner;
import com.ali.si2.Modelos.Producto;
import com.ali.si2.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FragmentBSFiltro extends BottomSheetDialogFragment {
    Producto producto;
    Filtro filtro;
    Button boton2;
    RadioGroup radioGroup1;
  //  TextView date1, date2;

    public FragmentBSFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_filtro, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boton2 = view.findViewById(R.id.boton2);
     //   date1 = view.findViewById(R.id.date1);
       // date2 = view.findViewById(R.id.date2);
        radioGroup1 = view.findViewById(R.id.radiogroup1);
    //    radioGroup2 = view.findViewById(R.id.radiogroup2);

        cargar();


    }

    private void cargar() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        Chip chip = (Chip) layoutInflater.inflate(R.layout.item_chip, null, false);
        chip.setText("No");
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip.setText("");
                chip.setText("Si");
                if (!chip.isChecked()) {
                    chip.setText("");
                    chip.setText("No");
                }
            }
        });
        radioGroup1.addView(chip);
    /*    Chip chips = (Chip) layoutInflater.inflate(R.layout.item_chip, null, false);
        chips.setText("No");
        chips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chips.setText("");
                chips.setText("Si");
                if (!chips.isChecked()) {
                    chips.setText("");
                    chips.setText("No");
                }
            }
        });
        radioGroup2.addView(chips);
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendario = Calendar.getInstance();
                int año = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get((Calendar.DAY_OF_MONTH));
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fechas = year + "-" + month + "-" + dayOfMonth;
                        date1.setText(fechas);
                    }
                }, año, mes, dia);
                datePickerDialog.show();
            }
        });
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int año = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get((Calendar.DAY_OF_MONTH));
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fechas = year + "-" + month + "-" + dayOfMonth;
                        date2.setText(fechas);
                    }
                }, año, mes, dia);
                datePickerDialog.show();
            }
        });*/
        producto = new Producto();
        producto.setNombre("hola");

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> map = new HashMap<>();
                if( !chip.isChecked())
                    dismiss();
                if (chip.isChecked())
                    map.put("valorado", "true");
                filtro.onFiltro(map);
                dismiss();



               /* if (date1.getText().toString().compareTo("fecha inicio") == 0 && date2.getText().toString().compareTo("fecha fin") == 0) {
                    map.put("fecha inicio",date1.getText().toString());
                    map.put("fecha fin",date2.getText().toString());
                    if (chip.isChecked())
                        map.put("valorado", "true");
                    else map.put("valorado", "false");
                    if (chips.isChecked())
                        map.put("vendido", "true");
                    else map.put("vendido", "false");
                    dismiss();
                    filtro.onFiltro(map);
                } else if (date1.getText().toString().compareTo("fecha inicio") != 0 && date2.getText().toString().compareTo("fecha fin") != 0) {
                    if(date1.getText().toString().compareTo(date2.getText().toString()) <0) {
                        map.put("fecha inicio", date1.getText().toString());
                        map.put("fecha fin", date2.getText().toString());
                        if (chip.isChecked())
                            map.put("valorado", "true");
                        else map.put("valorado", "false");
                        if (chips.isChecked())
                            map.put("vendido", "true");
                        else map.put("vendido", "false");
                        dismiss();
                        filtro.onFiltro(map);
                    }else{
                        Toast.makeText(getContext(), "rango invalido de fechas", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "seleccione fecha", Toast.LENGTH_SHORT).show();
                }
*/


            }
        });
    }


}
