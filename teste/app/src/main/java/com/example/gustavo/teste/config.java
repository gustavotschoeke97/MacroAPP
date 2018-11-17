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


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.setBackgroundColor(Color.rgb(255, 165, 0));
                bt.setTextColor(Color.WHITE);
                OpenActivity(this);
            }
        });
    }


    public void OpenActivity(View.OnClickListener view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("param",txt1.getText().toString()+":"+txt2.getText().toString());
        startActivity(intent);
        finish();
    }
}
