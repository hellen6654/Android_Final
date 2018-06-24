package com.example.ooxx.ooxx;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EndActivity extends AppCompatActivity {
    private ListView mListView;
    private Button mBtnOK;

    private MediaPlayer mPlayer;

    private static final int MENU_RECORD= Menu.FIRST,
            MENU_PLAYER=Menu.FIRST+1,
            MENU_PLAY=Menu.FIRST+2,
            MENU_STOP=Menu.FIRST+3;

    private ConstraintLayout mLayout;
    private static final String DB_FILE = "records.db", DB_TABLE = "records";
    private SQLiteDatabase mRecordDb;
    private ArrayList records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        mListView=(ListView)findViewById(R.id.listView);
        mBtnOK=(Button)findViewById(R.id.btnOK);
        mLayout=(ConstraintLayout)findViewById(R.id.endLayout);

        mBtnOK.setOnClickListener(mBtnOnClick);

        ActionBar actBar=getSupportActionBar();
        actBar.setLogo(R.drawable.icon_round);
        actBar.setDisplayUseLogoEnabled(true);
        actBar.setDisplayShowHomeEnabled(true);
        actBar.setBackgroundDrawable(new ColorDrawable(0xFFF3D95C));
        actBar.show();

        registerForContextMenu(mLayout);

        mPlayer = MediaPlayer.create(this,R.raw.recordbgm);
        mPlayer.setLooping(true);
        mPlayer.start();

        records=new ArrayList();
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
        Cursor c = mRecordDb.query(true, DB_TABLE, new String[]{"name", "win", "draw", "lose"}, null, null, null, null, null, null);
        if (c.getCount()==0)
            Toast.makeText(this, "We Don't have any records.", Toast.LENGTH_LONG).show();
        else
        {
            c.moveToFirst();
            records.add(c.getString(0) + "     " + c.getString(1) + " Wins     "  + c.getString(2) + " Draws     " + c.getString(3) + " Losses");
            while (c.moveToNext())
                records.add(c.getString(0) + "     " + c.getString(1) + " Wins     "  + c.getString(2) + " Draws     " + c.getString(3) + " Losses");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, records);
            mListView.setAdapter(arrayAdapter);
        }
        Animation alpha_in = new AlphaAnimation(0, 1);
        alpha_in.setDuration(2500);
        mListView.setAnimation(alpha_in);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
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

    private View.OnClickListener mBtnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(EndActivity.this, StartActivity.class);
            startActivity(intent);
            EndActivity.this.finish();
        }
    };
}
