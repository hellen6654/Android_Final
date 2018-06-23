package com.example.ooxx.ooxx;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private static final String DB_FILE = "records.db", DB_TABLE = "records";
    private SQLiteDatabase mRecordDb;
    private ArrayList records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        mListView=(ListView)findViewById(R.id.listView);
        mBtnOK=(Button)findViewById(R.id.btnOK);

        mBtnOK.setOnClickListener(mBtnOnClick);

        ActionBar actBar=getSupportActionBar();
        actBar.setLogo(R.drawable.icon_round);
        actBar.setDisplayUseLogoEnabled(true);
        actBar.setDisplayShowHomeEnabled(true);
        actBar.setBackgroundDrawable(new ColorDrawable(0xFFF3D95C));
        actBar.show();

        records=new ArrayList();
        RecordDbOpenHelper recordDbOpenHelper = new RecordDbOpenHelper(getApplicationContext(), DB_FILE, null, 1);
        mRecordDb = recordDbOpenHelper.getWritableDatabase();
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
