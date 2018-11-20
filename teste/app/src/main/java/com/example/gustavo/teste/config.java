package com.example.gustavo.teste;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class config extends AppCompatActivity {
    Button bt;
    EditText txt1,txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        bt   = (Button)findViewById(R.id.button2);
        txt1 = (EditText)findViewById(R.id.editText);
        txt2 = (EditText)findViewById(R.id.editText2);

        bt.setBackgroundColor(Color.rgb(54,73,158));
        bt.setTextColor(Color.WHITE);

        // Call the other activity, passing parameters into a intent
        bt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        bt.setBackgroundColor(Color.rgb(255, 165, 0));
                        bt.setTextColor(Color.WHITE);
                        if (txt1.getText().toString().equals("")|| txt2.getText().toString().equals("")){
                            Toast.makeText(getBaseContext(),"Preencher todos os campo !",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            OpenActivity();
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        bt.setBackgroundColor(Color.rgb(54,73,158));
                        bt.setTextColor(Color.WHITE);
                        break;
                }
                return false;
            }
        });
    }

    //intent method. finish() the config Activity
    public void OpenActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("param",txt1.getText().toString()+":"+txt2.getText().toString());
        startActivity(intent);
        finish();
    }
}
