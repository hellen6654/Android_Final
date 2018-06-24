package com.example.ooxx.ooxx;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {
    private MediaPlayer mPlayer;
    private Button mBtnSingleStart;
    private Button mBtnDoubleStart;
    private Button mBtnEnd;
    private ImageButton mImgBtnLogo;
    private ConstraintLayout mLayout;

    private static final int MENU_RECORD= Menu.FIRST,
            MENU_PLAYER=Menu.FIRST+1,
            MENU_PLAY=Menu.FIRST+2,
            MENU_STOP=Menu.FIRST+3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mBtnSingleStart = (Button) findViewById(R.id.btnSingleStart);
        mBtnDoubleStart = (Button) findViewById(R.id.btnDoubleStart);
        mBtnEnd = (Button) findViewById(R.id.btnEnd);
        mImgBtnLogo = (ImageButton) findViewById(R.id.imgBtnLogo);
        mLayout=(ConstraintLayout)findViewById(R.id.startLayout);
        mImgBtnLogo.setOnClickListener(imgBtnLogoOnClick);
        mBtnSingleStart.setOnClickListener(btnSingleStartOnClick);
        mBtnDoubleStart.setOnClickListener(btnDoubleStartOnClick);
        mBtnEnd.setOnClickListener(btnEndOnClick);

        ActionBar actBar=getSupportActionBar();
        actBar.setLogo(R.drawable.icon_round);
        actBar.setDisplayUseLogoEnabled(true);
        actBar.setDisplayShowHomeEnabled(true);
        actBar.setBackgroundDrawable(new ColorDrawable(0xFFF3D95C));
        actBar.show();

        registerForContextMenu(mLayout);

        mPlayer = MediaPlayer.create(this,R.raw.startbgm);
        mPlayer.setLooping(true);
        mPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MENU_RECORD,0,"查看紀錄");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case MENU_RECORD:
                Intent intent = new Intent();
                intent.setClass(StartActivity.this,EndActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        SubMenu subMenu = menu.addSubMenu(0, MENU_PLAYER,0,"背景音樂");
        subMenu.add(0,MENU_PLAY,0,"播放背景音樂");
        subMenu.add(0,MENU_STOP,1,"停止背景音樂");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case MENU_PLAY:
                if(!mPlayer.isPlaying())
                    mPlayer.start();
                break;
            case MENU_STOP:
                if(mPlayer.isPlaying())
                    mPlayer.pause();
                break;
        }
        return super.onContextItemSelected(item);
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
