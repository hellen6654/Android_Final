package com.example.ooxx.ooxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {
    private Button mBtnSingleStart;
    private Button mBtnDoubleStart;
    private Button mBtnEnd;
    private ImageButton mImgBtnLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mBtnSingleStart = (Button) findViewById(R.id.btnSingleStart);
        mBtnDoubleStart = (Button) findViewById(R.id.btnDoubleStart);
        mBtnEnd = (Button) findViewById(R.id.btnEnd);
        mImgBtnLogo = (ImageButton) findViewById(R.id.imgBtnLogo);
        mImgBtnLogo.setOnClickListener(imgBtnLogoOnClick);
        mBtnSingleStart.setOnClickListener(btnSingleStartOnClick);
        mBtnDoubleStart.setOnClickListener(btnDoubleStartOnClick);
        mBtnEnd.setOnClickListener(btnEndOnClick);
    }
    private View.OnClickListener imgBtnLogoOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            int imgBtnLogoNum=(int)(Math.random()*9);
            switch (imgBtnLogoNum)
            {
                case 0:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx0);
                    break;
                case 1:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx1);
                    break;
                case 2:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx2);
                    break;
                case 3:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx3);
                    break;
                case 4:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx4);
                    break;
                case 5:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx5);
                    break;
                case 6:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx6);
                    break;
                case 7:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx7);
                    break;
                case 8:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx8);
                    break;
                case 9:
                    mImgBtnLogo.setImageResource(R.drawable.ooxx9);
                    break;
            }
        }
    };
    private View.OnClickListener btnSingleStartOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(StartActivity.this, MainActivity.class);
            Bundle b = new Bundle();
            b.putBoolean("isSingle",true);
            intent.putExtras(b);
            startActivity(intent);
            StartActivity.this.finish();
        }
    };
    private View.OnClickListener btnDoubleStartOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(StartActivity.this, MainActivity.class);
            Bundle b = new Bundle();
            b.putBoolean("isSingle",false);
            intent.putExtras(b);
            startActivity(intent);
            StartActivity.this.finish();
        }
    };
    private View.OnClickListener btnEndOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StartActivity.this.finish();
        }
    };

}