package com.nkhuarongdao.lyx.hua1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    Button people[] = new Button[10];
    int HRDmap[][] = new int[5][4];
    TextView step;
    FrameLayout body;
    float screenWidth;
    float x1, x2, y1, y2;
    int Step=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        people[0] = (Button) findViewById(R.id.people11);
        people[1] = (Button) findViewById(R.id.people12);
        people[2] = (Button) findViewById(R.id.people13);
        people[3] = (Button) findViewById(R.id.people14);
        people[4] = (Button) findViewById(R.id.people15);
        people[5] = (Button) findViewById(R.id.people16);
        people[6] = (Button) findViewById(R.id.people17);
        people[7] = (Button) findViewById(R.id.people18);
        people[8] = (Button) findViewById(R.id.people19);
        people[9] = (Button) findViewById(R.id.people20);
        body = (FrameLayout)findViewById(R.id.HRD2);
        step = (TextView) findViewById(R.id.Text2);
        for (int i = 0; i < 10; i++)
            people[i].setOnTouchListener(new mTouch());
        //背景数组对应填充
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++)
                HRDmap[i][j] = 1;
        HRDmap[4][0] = 0;
        HRDmap[4][1] = 0;
        step.post(new Runnable() {
            @Override
            public void run() {
                screenWidth = body.getWidth();
                init();
            }
        });

    }

    public class mTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int type;
            int x, y;
            if(v.getWidth()==v.getHeight())
            {
                if(v.getHeight()>300)
                    type=4;
                else
                    type =1;
            }
            else
            {
                if(v.getHeight()>v.getWidth())
                    type =2;
                else
                    type =3;
            }
            x=(int)(v.getX()/270f);
            y=(int)(v.getY()/270f);

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                x1 = event.getX();
                y1 = event.getY();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                x2 = event.getX();
                y2 = event.getY();
                if (y1 - y2 > 50) //上
                {
                    switch (type) {
                        case 1:
                            if(y>0 && HRDmap[y-1][x]==0) {
                                drawPeople(v, y - 1, x);
                                HRDmap[y - 1][x] = 1;
                                HRDmap[y][x] = 0;
                                Step++;
                                step.setText("步数："+Step+"步");
                            }
                            break;
                        case 2:
                            if(y>0 && HRDmap[y-1][x]==0){
                                drawPeople(v,y-1,x);
                                HRDmap[y-1][x]=1;
                                HRDmap[y+1][x]=0;
                                Step++;
                                step.setText("步数："+Step+"步");
                            }
                            break;
                        case 3:
                            if(y>0 && HRDmap[y-1][x]==0 && HRDmap[y-1][x+1]==0){
                                drawPeople(v,y-1,x);
                                HRDmap[y-1][x]=HRDmap[y-1][x+1]=1;
                                HRDmap[y][x]=HRDmap[y][x+1]=0;
                                Step++;
                                step.setText("步数："+Step+"步");
                            }
                            break;
                        case 4:
                            if(y>0 && HRDmap[y-1][x]==0 && HRDmap[y-1][x+1]==0){
                                drawPeople(v,y-1,x);
                                HRDmap[y-1][x]=HRDmap[y-1][x+1]=1;
                                HRDmap[y+1][x]=HRDmap[y+1][x+1]=0;
                                Step++;
                                step.setText("步数："+Step+"步");
                            }
                            break;

                    }

                } else if (y2 - y1 > 50){ //下
                    switch (type) {
                        case 1:
                            if(y<4 && HRDmap[y+1][x]==0) {
                                drawPeople(v, y + 1, x);
                                HRDmap[y + 1][x] = 1;
                                HRDmap[y][x] = 0;
                                Step++;
                                step.setText("步数："+Step+"步");
                            }
                            break;
                        case 2:
                            if(y<3 && HRDmap[y+2][x]==0){
                                drawPeople(v,y+1,x);
                                HRDmap[y+2][x]=1;
                                HRDmap[y][x]=0;
                                Step++;
                                step.setText("步数："+Step+"步");
                            }
                            break;
                        case 3:
                            if(y<4 && HRDmap[y+1][x]==0 &&HRDmap[y+1][x+1]==0)
                            {
                                drawPeople(v,y+1,x);
                                HRDmap[y+1][x]=HRDmap[y+1][x+1]=1;
                                HRDmap[y][x]=HRDmap[y][x+1]=0;
                                Step++;
                                step.setText("步数："+Step+"步");
                            }
                            break;
                        case 4:
                            if(y<3 && HRDmap[y+2][x]==0 && HRDmap[y+2][x+1]==0){
                                drawPeople(v,y+1,x);
                                HRDmap[y+2][x]= 1;
                                HRDmap[y+2][x+1]=1;
                                HRDmap[y][x]=0;
                                HRDmap[y][x+1]=0;
                                Step++;

                                if(y==2 && x==1){
                                    step.setText("成功脱逃共"+Step+"步");
                                }
                                else
                                    step.setText("步数："+Step+"步");
                            }
                            break;
                    }
                } else if (x1 - x2 > 50) //左
                {
                    switch (type) {
                        case 1:
                            if(x>0 && HRDmap[y][x-1]==0) {
                                drawPeople(v, y, x- 1);
                                HRDmap[y][x- 1] = 1;
                                HRDmap[y][x] = 0;
                                Step++; step.setText("步数："+Step+"步");
                            }
                            break;
                        case 2:
                            if(x>0 && HRDmap[y][x-1]==0 && HRDmap[y+1][x-1]==0){
                                drawPeople(v,y,x-1);
                                HRDmap[y][x-1]=1;
                                HRDmap[y+1][x-1]=1;
                                HRDmap[y][x]=0;
                                HRDmap[y+1][x]=0;
                                Step++; step.setText("步数："+Step+"步");
                            }
                            break;
                        case 3:
                            if(x>0 & HRDmap[y][x-1]==0)
                            {
                                drawPeople(v,y,x-1);
                                HRDmap[y][x-1]=1;
                                HRDmap[y][x+1]=0;
                                Step++; step.setText("步数："+Step+"步");
                            }
                            break;
                        case 4:
                            if(x>0 && HRDmap[y][x-1]==0 && HRDmap[y+1][x-1]==0){
                                drawPeople(v,y,x-1);
                                HRDmap[y][x-1]= 1;
                                HRDmap[y+1][x-1]=1;
                                HRDmap[y][x+1]=0;
                                HRDmap[y+1][x+1]=0;
                                Step++;
                                if(y==3 && x==2){
                                    step.setText("成功脱逃共"+Step+"步");
                                }
                                else
                                    step.setText("步数："+Step+"步");
                            }
                            break;
                    }
                } else if (x2 - x1 > 50) //右
                {
                    switch (type) {
                        case 1:
                            if(x<3 && HRDmap[y][x+1]==0) {
                                drawPeople(v, y , x+1);
                                HRDmap[y][x+1] = 1;
                                HRDmap[y][x] = 0;
                                Step++; step.setText("步数："+Step+"步");
                            }
                            break;
                        case 2:
                            if(x<3 & HRDmap[y][x+1]==0 && HRDmap[y+1][x+1]==0)
                            {
                                drawPeople(v,y,x+1);
                                HRDmap[y][x+1]=1;
                                HRDmap[y+1][x+1]=1;
                                HRDmap[y][x]=0;
                                HRDmap[y+1][x]=0;
                                Step++;
                                step.setText("步数："+Step+"步！");
                            }
                            break;
                        case 3:
                            if(x<2 && HRDmap[y][x+2]==0)
                            {
                                drawPeople(v,y,x+1);
                                HRDmap[y][x+2]=1;
                                HRDmap[y][x]=0;
                                Step++;
                                step.setText("步数："+Step+"步");
                            }
                            break;
                        case 4:
                            if(x<2 && HRDmap[y][x+2]==0 && HRDmap[y+1][x+2]==0){
                                drawPeople(v,y,x+1);
                                HRDmap[y][x+2]=1;
                                HRDmap[y+1][x+2]=1;
                                HRDmap[y][x]=0;
                                HRDmap[y+1][x]=0;
                                Step++;
                                if(y==3 && x==0){
                                    step.setText("成功脱逃共"+Step+"步");
                                }
                                else
                                    step.setText("步数："+Step+"步");
                            }
                            break;
                    }
                }
            }
            return true;
        }
    }
    public static int convert(Context context, float dp){
        float scale =context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    void regular(Button v, int w, int h) {
        v.setWidth(w * convert(getApplicationContext(),screenWidth/4));
        v.setHeight(h * convert(getApplicationContext(), screenWidth / 4));
        //v.setText(txt);
    }
    void drawPeople(View v, int y ,int x) {
        v.setX(x*screenWidth/4f);
        v.setY(y*screenWidth/4f);
    }
    void init() {
        regular(people[0],1,1);
        drawPeople(people[0],0,0);

        regular(people[1],1,1);
        drawPeople(people[1], 1, 0);

        regular(people[2], 1, 1);
        drawPeople(people[2], 2, 2);

        regular(people[3], 1, 1);
        drawPeople(people[3], 2, 3);

        regular(people[4], 1, 2);
        drawPeople(people[4], 0, 1);

        regular(people[5],1,2);
        drawPeople(people[5],0,2);

        regular(people[6],1,2);
        drawPeople(people[6],0,3);

        regular(people[7],2,1);
        drawPeople(people[7],2,0);

        regular(people[8],2,1);
        drawPeople(people[8],3,0);

        regular(people[9],2,2);
        drawPeople(people[9], 3, 2);
    }
}

