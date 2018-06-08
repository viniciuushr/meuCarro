package com.exemplo.meucarro;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class FragmentCarro extends Fragment {
    //conexao bluetooth
    private String DEVICE_ADDRESS ; //MAC Address do modulo bluetooth
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    EditText txt_mac;
    ImageButton btn_destravar, btn_travar, btn_motor,btn_farol , btn_buzina, btn_ajuda, btn_conectar, btn_desconectar;
    String command; //variavel string  que vai armazenar o valor a ser transmitido para o modulo bluetooth

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //retornando o arquivo de layout
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_carro, container, false);

        txt_mac = (EditText) view.findViewById(R.id.txtMac) ;

        btn_destravar = (ImageButton) view.findViewById(R.id.btnDestravar);
        btn_travar = (ImageButton)view.findViewById(R.id.btnTravar);
        btn_motor = (ImageButton)view.findViewById(R.id.btnMotor);
        btn_farol = (ImageButton) view.findViewById(R.id.btnFarol);
        btn_buzina = (ImageButton) view.findViewById(R.id.btnBuzina);
        btn_ajuda = (ImageButton)view.findViewById(R.id.btnAjuda);
        btn_conectar = (ImageButton) view.findViewById(R.id.btnConectar);
        btn_desconectar = (ImageButton) view.findViewById(R.id.btnDesconectar);

            //Metodo OnTouchListener para o botao destravar (pressionar botao)
            btn_destravar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (socket != null) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN quando pressionado para baixo
                        {
                            command = "D";

                            try {
                                outputStream.write(command.getBytes()); //transmite o valor do comando para o modulo bluetooth
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP quando solta o botao
                        {
                            command = "d";
                            try {
                                outputStream.write(command.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        Toast.makeText(getContext(), "O Dispositivo Deve Estar Conectado!", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }

            });

            //Metodo OnTouchListener para o botao travar (pressionar botao)
            btn_travar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (socket != null) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN quando pressionado para baixo
                        {
                            command = "T";

                            try {
                                outputStream.write(command.getBytes()); //transmite o valor do comando para o modulo bluetooth
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP quando solta o botao
                        {
                            command = "t";
                            try {
                                outputStream.write(command.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            Toast.makeText(getContext(), "O Dispositivo Deve Estar Conectado!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return false;
                }

            });

        //Metodo OnTouchListener para o botao partida (pressionar botao)
        btn_motor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (socket != null) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN quando pressionado para baixo
                    {
                        command = "P";

                        try {
                            outputStream.write(command.getBytes()); //transmite o valor do comando para o modulo bluetooth
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (event.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP quando solta o botao
                    {
                        command = "p";
                        try {
                            outputStream.write(command.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(getContext(), "O Dispositivo Deve Estar Conectado!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

        });

            //Metodo OnTouchListener para o botao farol (pressionar botao)
            btn_farol.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(socket != null) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN quando pressionado para baixo
                        {
                            command = "F";

                            try {
                                outputStream.write(command.getBytes()); //transmite o valor do comando para o modulo bluetooth
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP quando solta o botao
                        {
                            command = "f";
                            try {
                                outputStream.write(command.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else {
                        Toast.makeText(getContext(), "O Dispositivo Deve Estar Conectado!", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }

            });

            //Metodo OnTouchListener para o botao buzina (pressionar botao)
            btn_buzina.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (socket != null) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN quando pressionado para baixo
                        {
                            command = "B";

                            try {
                                outputStream.write(command.getBytes()); //transmite o valor do comando para o modulo bluetooth
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (event.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP quando solta o botao
                        {
                            command = "b";
                            try {
                                outputStream.write(command.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        Toast.makeText(getContext(), "O Dispositivo Deve Estar Conectado!", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }

            });

        btn_ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());
                dialogo.setTitle("Ajuda!");
                dialogo.setMessage("1- Aperte cada botão apenas uma vez e aguarde a resposta. 2 - Não deixe o câmbio engatado.");
                dialogo.setNeutralButton("OK", null);
                dialogo.show();
            }
        });

        //Botao que conecta o dispositivo ao modulo bluetooth quando pressionado
        btn_conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DEVICE_ADDRESS =txt_mac.getText().toString();
                if(BTinit())
                {
                    BTconnect();
                }
            }
        });

        btn_desconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(socket != null) {
                    try{
                        // Immediately close this socket, and release all associated resources.
                        socket.close();
                        socket = null;
                        Toast.makeText(getContext(), "Dispositivo Desconectado!", Toast.LENGTH_SHORT).show();
                    } catch(IOException e){

                        //Log.e("ERRO AO DESCONECTAR", "O erro foi" + e.getMessage());
                        Toast.makeText(getContext(), "Erro! - A Conexão Permanece Estabelecida", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getContext(), "Não Há Conexão Estabelecida Para Desconectar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    //Inicializa o modulo bluetooth do celular
    public boolean BTinit()
    {
        boolean found = false;

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null) //Checa se o dispositivo suporta bluetooth
        {
            Toast.makeText(getContext(), "Dispositivo nao suporta bluetooth", Toast.LENGTH_SHORT).show();
        }

        if(!bluetoothAdapter.isEnabled()) //Checa se o bluetooth esta ativado. Se nao pede pra ligar
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        if(bondedDevices.isEmpty()) //Checa se o dispositivo esta pareado
        {
            Toast.makeText(getContext(), "O Dispositivo Deve Estar Pareado!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(BluetoothDevice iterator : bondedDevices)
            {
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public boolean BTconnect()
    {
        boolean connected = true;

        try
        {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); //Creates um soquete pata manejar a conexao
            socket.connect();

            Toast.makeText(getContext(),"Dispositivo Conectado!", Toast.LENGTH_LONG).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            connected = false;
        }

        if(connected)
        {
            try
            {
                outputStream = socket.getOutputStream(); //pega a variavel outputstream do soquete
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        return connected;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setando o titulo da toolbar da fragment
        getActivity().setTitle("Carro");
    }
}
