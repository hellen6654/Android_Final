package com.example.ooxx.ooxx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    private Button mBtnStart;
    private Button mBtnEnd;
    private ImageView mImgLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mBtnStart = (Button)findViewById(R.id.btnStart);
        mBtnEnd = (Button)findViewById(R.id.btnEnd);
        mImgLogo =(ImageView)findViewById(R.id.imgLogo);
        //忘記要放哪邊了
        int ooxximg=(int)(Math.random()*9);
        switch (ooxximg) {
            case 0:
                mImgLogo.setImageResource(R.drawable.ooxx0);
                break;
            case 1:
                mImgLogo.setImageResource(R.drawable.ooxx1);
                break;
            case 2:
                mImgLogo.setImageResource(R.drawable.ooxx2);
                break;
            case 3:
                mImgLogo.setImageResource(R.drawable.ooxx3);
                break;
            case 4:
                mImgLogo.setImageResource(R.drawable.ooxx4);
                break;
            case 5:
                mImgLogo.setImageResource(R.drawable.ooxx5);
                break;
            case 6:
                mImgLogo.setImageResource(R.drawable.ooxx6);
                break;
            case 7:
                mImgLogo.setImageResource(R.drawable.ooxx7);
                break;
            case 8:
                mImgLogo.setImageResource(R.drawable.ooxx8);
                break;
            case 9:
                mImgLogo.setImageResource(R.drawable.ooxx9);
                break;
        }
    }
}
