package com.exemplo.meucarro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentSos extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_sos, container, false);

        ImageButton btPolicia = (ImageButton) view.findViewById(R.id.btnPolicia);
        ImageButton btSamu = (ImageButton) view.findViewById(R.id.btnSamu);
        ImageButton btBombeiros = (ImageButton) view.findViewById(R.id.btnBombeiros);

        btPolicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefone = "190";
                Uri uri = Uri.parse("tel:"+telefone);
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });

        btSamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefone = "192";
                Uri uri = Uri.parse("tel:"+telefone);
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });

        btBombeiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefone = "193";
                Uri uri = Uri.parse("tel:"+telefone);
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("SOS");
    }
}
