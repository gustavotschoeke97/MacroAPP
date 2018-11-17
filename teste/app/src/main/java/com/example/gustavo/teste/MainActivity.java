package com.example.gustavo.teste;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    protected Socket socket;
    protected Socket_AsyncTask Enviar;
    Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn = (Button)findViewById(R.id.button);
        Btn.setBackgroundColor(Color.rgb(54,73,158));
        Btn.setTextColor(Color.WHITE);


        Intent intent      = getIntent();
        final String param = intent.getStringExtra("param");
        String pr [ ] = param.split(":");

        final String ip_pr = pr[0];
        final int port_pr = Integer.parseInt(pr[1]);

        Toast.makeText(getBaseContext(),param,Toast.LENGTH_LONG).show();

        Btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                            CreateConnection(ip_pr,port_pr,"1");
                            Btn.setBackgroundColor(Color.rgb(255, 165, 0));
                            Btn.setTextColor(Color.WHITE);
                        break;
                    case MotionEvent.ACTION_UP:
                            CreateConnection(ip_pr,port_pr,"0");
                            Btn.setBackgroundColor(Color.rgb(54,73,158));
                            Btn.setTextColor(Color.WHITE);
                        break;
                }
                return false;
            }
        });

    }

    public void CreateConnection(String ip,int port,String command){
       Enviar =  new Socket_AsyncTask(ip,port,command);
        new Thread() {
            @Override
            public void run() {
                Enviar.execute();
            }
        }.start();

    }



    //class create an object with thread and socket connection
    public class Socket_AsyncTask extends AsyncTask<Void,Void,Void> {
        String ip,commands;
        int port;
        public Socket_AsyncTask(String ip,int port,String commands){
            this.ip       = ip;
            this.port     = port;
            this.commands = commands;
        }
        @Override
        protected Void doInBackground(Void... params){
            try{
                InetAddress inetAddress = InetAddress.getByName(this.ip);
                socket = new java.net.Socket(inetAddress,this.port);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                if(socket.isConnected()) {
                    dataOutputStream.writeBytes(this.commands);
                    Toast.makeText(getBaseContext(),"Comando Enviado",Toast.LENGTH_SHORT).show();
                    dataOutputStream.close();
                    socket.close();


                }
                else{
                    Toast.makeText(getBaseContext(),"Connecting...",Toast.LENGTH_LONG).show();
                }
                socket = null;
            }catch (UnknownHostException e){ Toast.makeText(getBaseContext(), e.getMessage() , Toast.LENGTH_LONG);}catch (IOException e){ Toast.makeText(getBaseContext(), e.getMessage() , Toast.LENGTH_LONG);}catch (Exception e){e.printStackTrace();}
            return null;
        }
    }
}



