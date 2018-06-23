package com.example.ooxx.ooxx;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String DB_FILE = "records.db", DB_TABLE = "records";
    private SQLiteDatabase mRecordDb;

    private TextView mTxtSingleOrDouble;
    private TextView mTxtPlayer1;
    private TextView mTxtPlayer2;
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
    int playTable[]=new int[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtSingleOrDouble=(TextView)findViewById(R.id.txtSingleOrDouble);
        mTxtPlayer1=(TextView)findViewById(R.id.txtPlayer1);
        mTxtPlayer2=(TextView)findViewById(R.id.txtPlayer2);
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
        {
            mTxtSingleOrDouble.setText(getResources().getString(R.string.NowMode) + getResources().getString(R.string.SingleGame));

            final AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater1 = this.getLayoutInflater();
            final View dialog1View = inflater1.inflate(R.layout.dialog_single, null);
            final EditText username = (EditText) dialog1View.findViewById(R.id.username);
            dialog1.setView(dialog1View)
                    .setCancelable(false)
                    .setPositiveButton("輸入", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mTxtPlayer1.setText(username.getText().toString());
                            mTxtPlayer2.setText("Computer");
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
        else
        {
            mTxtSingleOrDouble.setText(getResources().getString(R.string.NowMode) + getResources().getString(R.string.DoubleGame));

            final AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater2 = this.getLayoutInflater();
            final View dialog2View = inflater2.inflate(R.layout.dialog_twoplayer, null);
            final EditText player1name = (EditText) dialog2View.findViewById(R.id.player1name);
            final EditText player2name = (EditText) dialog2View.findViewById(R.id.player2name);
            dialog2.setView(dialog2View)
                    .setCancelable(false)
                    .setPositiveButton("輸入", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mTxtPlayer1.setText(player1name.getText().toString());
                            mTxtPlayer2.setText(player2name.getText().toString());
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }

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

        RecordDbOpenHelper recordDbOpenHelper = new RecordDbOpenHelper(getApplicationContext(), DB_FILE, null, 1);
        mRecordDb = recordDbOpenHelper.getWritableDatabase();
        Cursor cursor = mRecordDb.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + DB_TABLE + "'", null);
        if(cursor != null) {
            if(cursor.getCount() == 0)	// 沒有資料表，要建立一個資料表。
                mRecordDb.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                        "_id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "win INTEGER," +
                        "draw INTEGER," +
                        "lose INTEGER);");
            cursor.close();
        }

        changeDirect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecordDb.close();
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
        if (playTable[0]==playTable[1]&&playTable[1]==playTable[2]&&playTable[0]!=0)
        {
            if (playTable[0]==1&&playTable[1]==1&&playTable[2]==1)
                RecordUpdate(1,0,0);
            else
                RecordUpdate(0,1,0);

            intent.setClass(MainActivity.this,EndActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[3]==playTable[4]&&playTable[4]==playTable[5]&&playTable[3]!=0)
        {
            if (playTable[3]==1&&playTable[4]==1&&playTable[5]==1)
                RecordUpdate(1,0,0);
            else
                RecordUpdate(0,1,0);

            intent.setClass(MainActivity.this,EndActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[6]==playTable[7]&&playTable[7]==playTable[8]&&playTable[6]!=0)
        {
            if (playTable[6]==1&&playTable[7]==1&&playTable[8]==1)
                RecordUpdate(1,0,0);
            else
                RecordUpdate(0,1,0);

            intent.setClass(MainActivity.this,EndActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[0]==playTable[3]&&playTable[3]==playTable[6]&&playTable[0]!=0)
        {
            if (playTable[0]==1&&playTable[3]==1&&playTable[6]==1)
                RecordUpdate(1,0,0);
            else
                RecordUpdate(0,1,0);

            intent.setClass(MainActivity.this,EndActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[1]==playTable[4]&&playTable[4]==playTable[7]&&playTable[1]!=0)
        {
            if (playTable[1]==1&&playTable[4]==1&&playTable[7]==1)
                RecordUpdate(1,0,0);
            else
                RecordUpdate(0,1,0);

            intent.setClass(MainActivity.this,EndActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[2]==playTable[5]&&playTable[5]==playTable[8]&&playTable[2]!=0)
        {
            if (playTable[2]==1&&playTable[5]==1&&playTable[8]==1)
                RecordUpdate(1,0,0);
            else
                RecordUpdate(0,1,0);

            intent.setClass(MainActivity.this,EndActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[0]==playTable[4]&&playTable[4]==playTable[8]&&playTable[0]!=0)
        {
            if (playTable[0]==1&&playTable[4]==1&&playTable[8]==1)
                RecordUpdate(1,0,0);
            else
                RecordUpdate(0,1,0);

            intent.setClass(MainActivity.this,EndActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        else if (playTable[2]==playTable[4]&&playTable[4]==playTable[6]&&playTable[2]!=0)
        {
            if (playTable[2]==1&&playTable[4]==1&&playTable[6]==1)
                RecordUpdate(1,0,0);
            else
                RecordUpdate(0,1,0);

            intent.setClass(MainActivity.this,EndActivity.class);
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
                RecordUpdate(0,0,1);
                intent.setClass(MainActivity.this,EndActivity.class);
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

    private void RecordUpdate(int player1win, int player1lose, int draw)
    {
        if(player1win==1)
            Toast.makeText(MainActivity.this, mTxtPlayer1.getText().toString() + " Win !", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, mTxtPlayer2.getText().toString() + " Win !", Toast.LENGTH_LONG).show();

        Cursor cp1 = mRecordDb.query(true, DB_TABLE, new String[]{"name", "win", "draw", "lose"}, "name=" + "\"" + mTxtPlayer1.getText().toString() + "\"", null, null, null, null, null);
        if (cp1.getCount()==0)
        {
            ContentValues newRow = new ContentValues();
            newRow.put("name", mTxtPlayer1.getText().toString());
            newRow.put("win", player1win);
            newRow.put("draw", draw);
            newRow.put("lose", player1lose);
            mRecordDb.insert(DB_TABLE, null, newRow);
        }
        else
        {
            cp1.moveToFirst();
            ContentValues newRow = new ContentValues();
            newRow.put("name", mTxtPlayer1.getText().toString());
            newRow.put("win", player1win + cp1.getInt(1));
            newRow.put("draw", draw + cp1.getInt(2));
            newRow.put("lose", player1lose + cp1.getInt(3));
            mRecordDb.update(DB_TABLE, newRow, "name=" + "\"" + mTxtPlayer1.getText().toString() + "\"", null);
        }
        if(!isSingle)
        {
            Cursor cp2 = mRecordDb.query(true, DB_TABLE, new String[]{"name", "win", "draw", "lose"}, "name=" + "\"" + mTxtPlayer2.getText().toString() + "\"", null, null, null, null, null);
            if (cp2.getCount()==0)
            {
                ContentValues newRow = new ContentValues();
                newRow.put("name", mTxtPlayer2.getText().toString());
                newRow.put("win", player1lose);
                newRow.put("draw", draw);
                newRow.put("lose", player1win);
                mRecordDb.insert(DB_TABLE, null, newRow);
            }
            else
            {
                cp2.moveToFirst();
                ContentValues newRow = new ContentValues();
                newRow.put("name", mTxtPlayer2.getText().toString());
                newRow.put("win", player1lose + cp2.getInt(1));
                newRow.put("draw", draw + cp2.getInt(2));
                newRow.put("lose", player1win + cp2.getInt(3));
                mRecordDb.update(DB_TABLE, newRow, "name=" + "\"" + mTxtPlayer2.getText().toString() + "\"", null);
            }
        }
    }
}
