package com.nkhuarongdao.lyx.hua1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    mydialog mydialog;
    Button rule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rule = (Button)findViewById(R.id.rule);

    }

    public void rule(View view) {
        mydialog = new mydialog(MainActivity.this);
        mydialog.setTitle("游戏规则");
        mydialog.setMessage(getString(R.string.rules));
        mydialog.setYesOnclickListener("确定", new mydialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                mydialog.dismiss();
            }
        });
        mydialog.show();
    }

    public void story(View view) {
        mydialog = new mydialog(MainActivity.this);
        mydialog.setTitle("故事背景");
        mydialog.setMessage(getString(R.string.story));
        mydialog.setYesOnclickListener("确定", new mydialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                mydialog.dismiss();
            }
        });
        mydialog.show();
    }

    public void choose(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void rank(View view) {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }

    public void run(View view) {
        Intent intent = new Intent(this, Main4Activity.class);
        startActivity(intent);
    }
    public void success(View view) {
        Intent intent = new Intent(this, Main5Activity.class);
        startActivity(intent);
    }
}
