package com.exemplo.meucarro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentCombustivel extends Fragment {

    Button btn_calcular;
    TextView txt_resultado;
    EditText txt_etanol, txt_gasolina;
    ConstraintLayout constraintLayout_res;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_combustivel, container, false);

        btn_calcular = (Button) view.findViewById(R.id.btnCalcular);
        txt_etanol = (EditText)view.findViewById(R.id.txtEtanol);
        txt_gasolina = (EditText)view.findViewById(R.id.txtGasolina);
        txt_resultado = (TextView)view.findViewById(R.id.txtResultado);
        constraintLayout_res = (ConstraintLayout) view.findViewById(R.id.constraintLayout_res);


            btn_calcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (txt_etanol.length() > 0 && txt_gasolina.length() > 0) {
                        double etanol = Double.parseDouble(txt_etanol.getText().toString());
                        double gasolina = Double.parseDouble(txt_gasolina.getText().toString());
                        double res = (etanol / gasolina) * 100;
                        if (res >= 70) {
                            constraintLayout_res.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                            txt_resultado.setText("Abasteça com Gasolina!");
                        } else {
                            constraintLayout_res.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                            txt_resultado.setText("Abasteça com Etanol!");
                        }
                    } else {
                        Toast.makeText(getContext(), "Preencha os Campos Corretamente!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Calculo Combustível");
    }
}
