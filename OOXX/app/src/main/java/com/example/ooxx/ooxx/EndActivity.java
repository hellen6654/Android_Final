package com.example.ooxx.ooxx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    private TextView mtxtwhoWin;
    private TextView mtxtisSingle;
    int whoWin;
    boolean isSingle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Bundle b = this.getIntent().getExtras();
        isSingle=b.getBoolean("isSingle");
        whoWin=b.getInt("whoWin");
        mtxtisSingle=(TextView)findViewById(R.id.txtisSingle);
        mtxtwhoWin=(TextView)findViewById(R.id.txtWhoWin);
        mtxtwhoWin.setText(String.valueOf(whoWin));
        mtxtisSingle.setText(String.valueOf(isSingle));
    }
}
