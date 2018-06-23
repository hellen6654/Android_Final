package com.example.ooxx.ooxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mTxtSingleOrDouble;
    private ImageButton mImgBtn0;
    private ImageButton mImgBtn1;
    private ImageButton mImgBtn2;
    private ImageButton mImgBtn3;
    private ImageButton mImgBtn4;
    private ImageButton mImgBtn5;
    private ImageButton mImgBtn6;
    private ImageButton mImgBtn7;
    private ImageButton mImgBtn8;
    private ImageView mImgRightOrLelt;
    private ImageView mImgPlayer1;
    private ImageView mImgPlayer2;
    private int state=1;
    /* single
        1==玩家  -1==電腦
       double
        1==玩家1 -1==玩家2
    */
    boolean isSingle;
    int whoWin=-1;
    /*
        -1 == Init
        1  == player1
        2  == player2
        3  == draw
    */
    int playTable[]=new int[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtSingleOrDouble=(TextView)findViewById(R.id.txtSingleOrDouble);
        mImgBtn0=(ImageButton)findViewById(R.id.imgBtn0);
        mImgBtn1=(ImageButton)findViewById(R.id.imgBtn1);
        mImgBtn2=(ImageButton)findViewById(R.id.imgBtn2);
        mImgBtn3=(ImageButton)findViewById(R.id.imgBtn3);
        mImgBtn4=(ImageButton)findViewById(R.id.imgBtn4);
        mImgBtn5=(ImageButton)findViewById(R.id.imgBtn5);
        mImgBtn6=(ImageButton)findViewById(R.id.imgBtn6);
        mImgBtn7=(ImageButton)findViewById(R.id.imgBtn7);
        mImgBtn8=(ImageButton)findViewById(R.id.imgBtn8);
        mImgRightOrLelt=(ImageView) findViewById(R.id.imgRightOrLeft);
        mImgPlayer1=(ImageView) findViewById(R.id.imgPlayer1);
        mImgPlayer2=(ImageView) findViewById(R.id.imgPlayer2);
        Bundle b = this.getIntent().getExtras();
        isSingle= b.getBoolean("isSingle");
        if (isSingle)
            mTxtSingleOrDouble.setText(getResources().getString(R.string.NowMode)+getResources().getString(R.string.SingleGame));
        else
            mTxtSingleOrDouble.setText(getResources().getString(R.string.NowMode)+getResources().getString(R.string.DoubleGame));

        for (int i=0;i<9;i++)
            playTable[i]=0;

        mImgBtn0.setOnClickListener(mImgBtn0OnClicked);
        mImgBtn1.setOnClickListener(mImgBtn1OnClicked);
        mImgBtn2.setOnClickListener(mImgBtn2OnClicked);
        mImgBtn3.setOnClickListener(mImgBtn3OnClicked);
        mImgBtn4.setOnClickListener(mImgBtn4OnClicked);
        mImgBtn5.setOnClickListener(mImgBtn5OnClicked);
        mImgBtn6.setOnClickListener(mImgBtn6OnClicked);
        mImgBtn7.setOnClickListener(mImgBtn7OnClicked);
        mImgBtn8.setOnClickListener(mImgBtn8OnClicked);



        mImgPlayer1.setImageResource(R.drawable.circle);
        mImgPlayer2.setImageResource(R.drawable.xx);

        changeDirect();

    }
    private View.OnClickListener mImgBtn0OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                if (playTable[0]!=0)
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
                else
                {
                    playTable[0]=state;
                    if (state==1) //玩家 OR 玩家1
                        mImgBtn0.setImageDrawable(getDrawable(R.drawable.circle));
                    else
                        mImgBtn0.setImageDrawable(getDrawable(R.drawable.xx));
                    state*=-1;
                    Iswin();
                    changeDirect();
                    if (isSingle)
                    {
                        SinglePlayerAI();
                        Iswin();
                        changeDirect();

                    }
                }
        }
    };
    private View.OnClickListener mImgBtn1OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (playTable[1]!=0)
                Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
            else
            {
                playTable[1]=state;
                if (state==1) //玩家 OR 玩家1
                    mImgBtn1.setImageDrawable(getDrawable(R.drawable.circle));
                else
                    mImgBtn1.setImageDrawable(getDrawable(R.drawable.xx));
                state*=-1;
                changeDirect();
                if (isSingle)
                {
                    SinglePlayerAI();
                    Iswin();
                    changeDirect();

                }
            }
        }
    };
    private View.OnClickListener mImgBtn2OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (playTable[2]!=0)
                Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
            else
            {
                playTable[2]=state;
                if (state==1) //玩家 OR 玩家1
                    mImgBtn2.setImageDrawable(getDrawable(R.drawable.circle));
                else
                    mImgBtn2.setImageDrawable(getDrawable(R.drawable.xx));
                state*=-1;
                Iswin();
                changeDirect();
                if (isSingle)
                {
                    SinglePlayerAI();
                    Iswin();
                    changeDirect();
                }
            }

        }
    };
    private View.OnClickListener mImgBtn3OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (playTable[3]!=0)
                Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
            else
            {
                playTable[3]=state;
                if (state==1) //玩家 OR 玩家1
                    mImgBtn3.setImageDrawable(getDrawable(R.drawable.circle));
                else
                    mImgBtn3.setImageDrawable(getDrawable(R.drawable.xx));
                state*=-1;
                Iswin();
                changeDirect();
                if (isSingle)
                {
                    SinglePlayerAI();
                    Iswin();
                    changeDirect();

                }
            }
        }
    };
    private View.OnClickListener mImgBtn4OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (playTable[4]!=0)
                Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
            else
            {
                playTable[4]=state;
                if (state==1) //玩家 OR 玩家1
                    mImgBtn4.setImageDrawable(getDrawable(R.drawable.circle));
                else
                    mImgBtn4.setImageDrawable(getDrawable(R.drawable.xx));
                state*=-1;
                Iswin();
                changeDirect();
                if (isSingle)
                {
                    SinglePlayerAI();
                    Iswin();
                    changeDirect();

                }
            }
        }
    };
    private View.OnClickListener mImgBtn5OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (playTable[5]!=0)
                Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
            else
            {
                playTable[5]=state;
                if (state==1) //玩家 OR 玩家1
                    mImgBtn5.setImageDrawable(getDrawable(R.drawable.circle));
                else
                    mImgBtn5.setImageDrawable(getDrawable(R.drawable.xx));
                state*=-1;
                Iswin();
                changeDirect();
                if (isSingle)
                {
                    SinglePlayerAI();
                    Iswin();
                    changeDirect();
                }
            }
        }
    };
    private View.OnClickListener mImgBtn6OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (playTable[6]!=0)
                Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
            else
            {
                playTable[6]=state;
                if (state==1) //玩家 OR 玩家1
                    mImgBtn6.setImageDrawable(getDrawable(R.drawable.circle));
                else
                    mImgBtn6.setImageDrawable(getDrawable(R.drawable.xx));
                state*=-1;
                Iswin();
                changeDirect();
                if (isSingle)
                {
                    SinglePlayerAI();
                    Iswin();
                    changeDirect();

                }
            }
        }
    };
    private View.OnClickListener mImgBtn7OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (playTable[7]!=0)
                Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
            else
            {
                playTable[7]=state;
                if (state==1) //玩家 OR 玩家1
                    mImgBtn7.setImageDrawable(getDrawable(R.drawable.circle));
                else
                    mImgBtn7.setImageDrawable(getDrawable(R.drawable.xx));
                state*=-1;
                Iswin();
                changeDirect();
                if (isSingle)
                {
                    SinglePlayerAI();
                    Iswin();
                    changeDirect();

                }
            }
        }
    };
    private View.OnClickListener mImgBtn8OnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (playTable[8]!=0)
                Toast.makeText(MainActivity.this,getResources().getString(R.string.NotEmpty),Toast.LENGTH_LONG).show();
            else
            {
                playTable[8]=state;
                if (state==1) //玩家 OR 玩家1
                    mImgBtn8.setImageDrawable(getDrawable(R.drawable.circle));
                else
                    mImgBtn8.setImageDrawable(getDrawable(R.drawable.xx));
                state=state*(-1);
                Iswin();
                changeDirect();
                if (isSingle)
                {
                    SinglePlayerAI();
                    Iswin();
                    changeDirect();
                }
            }
        }
    };
    private void changeDirect()
    {
        if (state==1) mImgRightOrLelt.setImageResource(R.drawable.left);
        else mImgRightOrLelt.setImageResource(R.drawable.right);
    }
    private void Iswin()
    {
        /*
           012
           345
           678
         */
        Intent intent = new Intent();
        Bundle b = new Bundle();
        if (playTable[0]==playTable[1]&&playTable[1]==playTable[2]&&playTable[0]!=0)
        {
            if (playTable[0]==1&&playTable[1]==1&&playTable[2]==1)
            {
                whoWin=1;

            }
            else
            {
                whoWin=2;

            }
            intent.setClass(MainActivity.this,EndActivity.class);
            b.putInt("whoWin",whoWin);
            b.putBoolean("IsSingle",isSingle);
            intent.putExtras(b);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[3]==playTable[4]&&playTable[4]==playTable[5]&&playTable[3]!=0)
        {
            if (playTable[3]==1&&playTable[4]==1&&playTable[5]==1)
            {
                whoWin=1;
            }
            else
            {
                whoWin=2;
            }
            intent.setClass(MainActivity.this,EndActivity.class);
            b.putInt("whoWin",whoWin);
            b.putBoolean("IsSingle",isSingle);
            intent.putExtras(b);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[6]==playTable[7]&&playTable[7]==playTable[8]&&playTable[6]!=0)
        {
            if (playTable[6]==1&&playTable[7]==1&&playTable[8]==1)
            {
                whoWin=1;
            }
            else
            {
                whoWin = 2;
            }
            intent.setClass(MainActivity.this,EndActivity.class);
            b.putInt("whoWin",whoWin);
            b.putBoolean("IsSingle",isSingle);
            intent.putExtras(b);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[0]==playTable[3]&&playTable[3]==playTable[6]&&playTable[0]!=0)
        {
            if (playTable[0]==1&&playTable[3]==1&&playTable[6]==1)
            {
                whoWin=1;
            }
            else
            {
                whoWin=2;
            }
            intent.setClass(MainActivity.this,EndActivity.class);
            b.putInt("whoWin",whoWin);
            b.putBoolean("IsSingle",isSingle);
            intent.putExtras(b);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[1]==playTable[4]&&playTable[4]==playTable[7]&&playTable[1]!=0)
        {
            if (playTable[1]==1&&playTable[4]==1&&playTable[7]==1)
            {
                whoWin=1;
            }
            else
            {
                whoWin=2;
            }
            intent.setClass(MainActivity.this,EndActivity.class);
            b.putInt("whoWin",whoWin);
            b.putBoolean("IsSingle",isSingle);
            intent.putExtras(b);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[2]==playTable[5]&&playTable[5]==playTable[8]&&playTable[2]!=0)
        {
            if (playTable[2]==1&&playTable[5]==1&&playTable[8]==1)
            {
                whoWin=1;
            }
            else
            {
                whoWin=2;
            }
            intent.setClass(MainActivity.this,EndActivity.class);
            b.putInt("whoWin",whoWin);
            b.putBoolean("IsSingle",isSingle);
            intent.putExtras(b);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[0]==playTable[4]&&playTable[4]==playTable[8]&&playTable[0]!=0)
        {
            if (playTable[0]==1&&playTable[4]==1&&playTable[8]==1)
            {
                whoWin=1;
            }
            else
            {
                whoWin=2;
            }
            intent.setClass(MainActivity.this,EndActivity.class);
            b.putInt("whoWin",whoWin);
            b.putBoolean("IsSingle",isSingle);
            intent.putExtras(b);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[2]==playTable[4]&&playTable[4]==playTable[6]&&playTable[2]!=0)
        {
            if (playTable[2]==1&&playTable[4]==1&&playTable[6]==1)
            {
                whoWin=1;
            }
            else
            {
                whoWin=2;
            }
            intent.setClass(MainActivity.this,EndActivity.class);
            b.putInt("whoWin",whoWin);
            b.putBoolean("IsSingle",isSingle);
            intent.putExtras(b);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else
        {
            int input=0;
            for (int i=0;i<9;i++)
                if (playTable[i]!=0) input++;
            if (input==9)
            {
                whoWin=3;
                intent.setClass(MainActivity.this,EndActivity.class);
                b.putInt("whoWin",whoWin);
                b.putBoolean("IsSingle",isSingle);
                intent.putExtras(b);
                startActivity(intent);
                MainActivity.this.finish();
            }
        }
        return;
    }
    private void SinglePlayerAI()
    {
        if (playTable[4]==0)
        {
            playTable[4]=state;
            mImgBtn4.setImageDrawable(getDrawable(R.drawable.xx));
            state*=-1;
            return;
        }
        else
        {
            /*
                012
                345
                678
            */
            if ((playTable[0]==playTable[1]&&playTable[2]==0&&playTable[0]!=0)||
                    (playTable[1]==playTable[2]&&playTable[0]==0&&playTable[1]!=0)||
                    (playTable[0]==playTable[2]&&playTable[1]==0&&playTable[2]!=0))
            {
                if (playTable[2]==0)playTable[2]=state;
                else if (playTable[0]==0) playTable[0]=state;
                else if (playTable[1]==0) playTable[1]=state;
            }
            else if ((playTable[3]==playTable[4]&&playTable[5]==0&&playTable[3]!=0)||
                    (playTable[4]==playTable[5]&&playTable[6]==0&&playTable[4]!=0)||
                    (playTable[3]==playTable[5]&&playTable[4]==0&&playTable[5]!=0))
            {
                if (playTable[5]==0) playTable[5]=state;
                else if (playTable[6]==0) playTable[6]=state;
                else if (playTable[4]==0) playTable[4]=state;
            }
            else if ((playTable[6]==playTable[7]&&playTable[8]==0&&playTable[6]!=0)||
                    (playTable[7]==playTable[8]&&playTable[6]==0&&playTable[7]!=0)||
                    (playTable[6]==playTable[8]&&playTable[7]==0&&playTable[8]!=0))
            {
                if (playTable[8]==0) playTable[8]=state;
                else if (playTable[6]==0) playTable[6]=state;
                else if (playTable[7]==0) playTable[7]=state;
            }
            else if ((playTable[0]==playTable[3]&&playTable[6]==0&&playTable[0]!=0)||
                    (playTable[3]==playTable[6]&&playTable[0]==0&&playTable[3]!=0)||
                    (playTable[0]==playTable[6]&&playTable[3]==0&&playTable[6]!=0))
            {
                if (playTable[6]==0) playTable[6]=state;
                else if (playTable[0]==0) playTable[0]=state;
                else if (playTable[3]==0) playTable[3]=state;
            }
            else if ((playTable[1]==playTable[4]&&playTable[7]==0&&playTable[1]!=0)||
                    (playTable[4]==playTable[7]&&playTable[1]==0&&playTable[4]!=0)||
                    (playTable[1]==playTable[7]&&playTable[4]==0&&playTable[7]!=0))
            {
                if (playTable[7]==0) playTable[7]=state;
                else if (playTable[1]==0) playTable[1]=state;
                else if (playTable[4]==0) playTable[4]=state;
            }
            else if ((playTable[2]==playTable[5]&&playTable[8]==0&&playTable[2]!=0)||
                    (playTable[5]==playTable[8]&&playTable[2]==0&&playTable[5]!=0)||
                    (playTable[2]==playTable[8]&&playTable[5]==0&&playTable[8]!=0))
            {
                if (playTable[2]==0) playTable[2]=state;
                else if (playTable[5]==0) playTable[5]=state;
                else if (playTable[8]==0) playTable[8]=state;
            }
            else  if ((playTable[0]==playTable[4]&&playTable[8]==0&&playTable[0]!=0)||
                    (playTable[4]==playTable[8]&&playTable[0]==0&&playTable[4]!=0)||
                    (playTable[0]==playTable[8]&&playTable[4]==0&&playTable[8]!=0))
            {
                if (playTable[0]==0) playTable[0]=state;
                else if (playTable[4]==0) playTable[4]=state;
                else if (playTable[8]==0) playTable[8]=state;
            }
            else if ((playTable[2]==playTable[4]&&playTable[6]==0&&playTable[2]!=0)||
                    (playTable[4]==playTable[6]&&playTable[2]==0&&playTable[4]!=0)||
                    (playTable[2]==playTable[6]&&playTable[4]==0&&playTable[6]!=0))
            {
                if (playTable[2]==0) playTable[2]=state;
                else if (playTable[4]==0) playTable[4]=state;
                else if (playTable[6]==0) playTable[6]=state;
            }
            else
            {
                int i=0;
                for (i=0;i<9;i++)
                    if (playTable[i]==0)
                        break;
               playTable[i]=state;
            }
            if (playTable[0]==state) mImgBtn0.setImageDrawable(getDrawable(R.drawable.xx));
            if (playTable[1]==state) mImgBtn1.setImageDrawable(getDrawable(R.drawable.xx));
            if (playTable[2]==state) mImgBtn2.setImageDrawable(getDrawable(R.drawable.xx));
            if (playTable[3]==state) mImgBtn3.setImageDrawable(getDrawable(R.drawable.xx));
            if (playTable[4]==state) mImgBtn4.setImageDrawable(getDrawable(R.drawable.xx));
            if (playTable[5]==state) mImgBtn5.setImageDrawable(getDrawable(R.drawable.xx));
            if (playTable[6]==state) mImgBtn6.setImageDrawable(getDrawable(R.drawable.xx));
            if (playTable[7]==state) mImgBtn7.setImageDrawable(getDrawable(R.drawable.xx));
            if (playTable[8]==state) mImgBtn8.setImageDrawable(getDrawable(R.drawable.xx));
            state*=-1;
            return;
        }
    }
}
