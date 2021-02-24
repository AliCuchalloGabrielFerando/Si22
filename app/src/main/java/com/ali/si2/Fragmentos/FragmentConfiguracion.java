package com.ali.si2.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.ali.si2.Login;
import com.ali.si2.Logout;
import com.ali.si2.R;


public class FragmentConfiguracion extends PreferenceFragmentCompat {

    public FragmentConfiguracion() {
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.configuracion,rootKey);



    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        //return super.onPreferenceTreeClick(preference);
        String key=preference.getKey();




        if(key.compareTo("preferencia")==0){
            startActivity(new Intent(getContext(), com.ali.si2.Preferencias.class));
        }
        if(key.compareTo("ajustes")==0){

            startActivity(new Intent(getContext(), Logout.class));
        }

        return true;
    }

   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_perfil,container,false);
      return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }*/
}