package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {

    //字符串数组，用来存储从上一个活动中传递过来的课程信息
    static String[] project;

    //定义按钮数组，把每节课对应的按钮一一存储到数组中
    final Button[] buttons=new Button[35];

    //定义按钮数组，这个用来存储节次对应的按钮，从而加上时间
    final Button[] buttonstime=new Button[5];

    static int noticeNumber=0;

    private Button bu;


    //标题栏的菜单选项
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);

        return true;
    }

//    菜单响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_summer:
                itemSummer();
                break;

            case R.id.item_winter:
                itemWinter();
                break;

            case R.id.item_notice:
                itemNotice();
                break;

            case R.id.item_logout:
                itemLogout();
                break;

            case R.id.item_finish:
                itemFinish();
                break;

            default:break;

        }
        return true;
    }

    private void itemSummer(){
        noticeNumber=1;
        buttonstime[0].setText("第一节8:00");
        buttonstime[1].setText("第二节10:10");
        buttonstime[2].setText("第三节14:00");
        buttonstime[3].setText("第四节16:00");
        buttonstime[4].setText("第五节19:00");
    }

    private void itemWinter(){
        noticeNumber=2;
        buttonstime[0].setText("第一节8:00");
        buttonstime[1].setText("第二节10:10");
        buttonstime[2].setText("第三节13:30");
        buttonstime[3].setText("第四节15:30");
        buttonstime[4].setText("第五节18:30");
    }

    private void itemNotice(){
        if(noticeNumber==0){
            Toast.makeText(MainActivity.this,"请选择作息时间！",Toast.LENGTH_LONG).show();
        }
        else if(noticeNumber==1) {
            //夏季作息
            Toast.makeText(MainActivity.this, "将在上课前十分钟提醒您",Toast.LENGTH_LONG).show();
            setClock(7,50);
            setClock(10,00);
            setClock(13,50);
            setClock(15,50);
            setClock(18,50);
        }
        else {
            Toast.makeText(MainActivity.this, "将在上课前十分钟提醒您",Toast.LENGTH_LONG).show();
            setClock(7,50);
            setClock(10,00);
            setClock(13,20);
            setClock(15,20);
            setClock(18,20);

        }
    }

    private void itemLogout(){
        Intent intent_2_1 =new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent_2_1);
    }

    private void itemFinish(){
        finish();
    }



    //标题栏的小彩蛋
    public String getCurrentTime() {
        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR_OF_DAY);
        if(hour<5||hour>19)
            return "晚上好,";
        else
            if(hour>=5&&hour<11)
                return "早上好,";
        else
                if(hour>=11&&hour<=14)
                    return "中午好,";
        else
                    return "下午好,";
    }

//    监听页面加载，加载出来时读取原本存储的课程信息
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus){
//        if(hasFocus){
//            for(int i=0;i<45;i++)
//            {
//                SharedPreferences pref=getSharedPreferences(Integer.toString(i),MODE_PRIVATE);
//                String subjectname=pref.getString("subjectname","");
//                buttons[i].setText(subjectname);
//
//            }
//        }
//    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode,keyEvent);
    }

//    public boolean moveTaskToBack(boolean nonRoot) {
//        try {
//            return ActivityManagerNative.getDefault().moveActivityTaskToBack(
//                    mToken, nonRoot);
//        } catch (RemoteException e) {
//            // Empty
//        }
//        return false;
//    }




//    @Override
//    public void OnClick(View view){
//        switch (view.getId()){
//            case R.id.button:
//                Intent intent=new Intent(this,LongRunningService.class);
//                startService(intent);
//                break;
//
//            default:break;
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        来自上一个活动传递的信息，将用户输入的用户名定为标题栏内容
//        setTitle方法用来修改标题栏内容
        Intent intent_username =getIntent();
        String name=intent_username.getStringExtra("username");
        project=intent_username.getStringArrayExtra("project");
        setTitle(getCurrentTime()+name);

        buttons[0]=(Button)findViewById(R.id.button1);
        buttons[1]=(Button)findViewById(R.id.button2);
        buttons[2]=(Button)findViewById(R.id.button3);
        buttons[3]=(Button)findViewById(R.id.button4);
        buttons[4]=(Button)findViewById(R.id.button5);
        buttons[5]=(Button)findViewById(R.id.button6);
        buttons[6]=(Button)findViewById(R.id.button7);
        buttons[7]=(Button)findViewById(R.id.button8);
        buttons[8]=(Button)findViewById(R.id.button9);
        buttons[9]=(Button)findViewById(R.id.button10);
        buttons[10]=(Button)findViewById(R.id.button11);
        buttons[11]=(Button)findViewById(R.id.button12);
        buttons[12]=(Button)findViewById(R.id.button13);
        buttons[13]=(Button)findViewById(R.id.button14);
        buttons[14]=(Button)findViewById(R.id.button15);
        buttons[15]=(Button)findViewById(R.id.button16);
        buttons[16]=(Button)findViewById(R.id.button17);
        buttons[17]=(Button)findViewById(R.id.button18);
        buttons[18]=(Button)findViewById(R.id.button19);
        buttons[19]=(Button)findViewById(R.id.button20);
        buttons[20]=(Button)findViewById(R.id.button21);
        buttons[21]=(Button)findViewById(R.id.button22);
        buttons[22]=(Button)findViewById(R.id.button23);
        buttons[23]=(Button)findViewById(R.id.button24);
        buttons[24]=(Button)findViewById(R.id.button25);
        buttons[25]=(Button)findViewById(R.id.button26);
        buttons[26]=(Button)findViewById(R.id.button27);
        buttons[27]=(Button)findViewById(R.id.button28);
        buttons[28]=(Button)findViewById(R.id.button29);
        buttons[29]=(Button)findViewById(R.id.button30);
        buttons[30]=(Button)findViewById(R.id.button31);
        buttons[31]=(Button)findViewById(R.id.button32);
        buttons[32]=(Button)findViewById(R.id.button33);
        buttons[33]=(Button)findViewById(R.id.button34);
        buttons[34]=(Button)findViewById(R.id.button35);

        buttonstime[0]=(Button)findViewById(R.id.button43);
        buttonstime[1]=(Button)findViewById(R.id.button44);
        buttonstime[2]=(Button)findViewById(R.id.button45);
        buttonstime[3]=(Button)findViewById(R.id.button46);
        buttonstime[4]=(Button)findViewById(R.id.button47);



        for(int i=0;i<35;i++){
            buttons[i].setText(project[i]);
        }





//        用for循环对各个按钮依次添加监听点击事件
        for(int i=0;i<35;i++) {
            final Button button = buttons[i];
            final int j=i;

                buttons[i].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        final EditText edi=new EditText(MainActivity.this);
                        edi.setSingleLine(true);
                        edi.setHint(button.getText());

                        AlertDialog.Builder aa = new AlertDialog.Builder(MainActivity.this);
                        aa.setTitle("请输入课程名");
                        aa.setView(edi);

//                        点击确定，一方面把输入框信息传递给按钮，作为按钮的名称；另一方面把按钮的名称上传到j文件中，j是按钮的编号
                        aa.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                button.setText(edi.getText());
                                SharedPreferences.Editor editor=getSharedPreferences(Integer.toString(j),MODE_PRIVATE).edit();
                                editor.putString("subjectname",button.getText().toString());
                                editor.commit();
                            }
                        });

                        aa.setNegativeButton("取消", null);

//                        点击更多，一方面把按钮的编号用position记录下来；另一方面把课程名称（即按钮名称）和按钮编号作为信息传递给下一个活动
                        aa.setNeutralButton("更多…", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                position=j;
                                String data=button.getText().toString();
                                Intent intent_2_3 =new Intent(MainActivity.this,InfoActivity.class);
                                intent_2_3.putExtra("subject_name",data);
                                intent_2_3.putExtra("position",j);
                                startActivityForResult(intent_2_3,1);

                            }

                        });
                        aa.show();

                    }
                });

        }
    }

//    全局变量position，用来记录按钮名称
    private int position;

//    改写该方法，用于接收下一个活动返回的信息
    @Override
    public void onActivityResult(int request,int resultCode,Intent intentback){
        switch (request){
            case 1:
                if(resultCode==RESULT_OK){
                    buttons[position].setText(intentback.getStringExtra("data_return").toString());
                    break;
                }
            default:break;

        }

    }


//    下面是闹钟的内容
    private void setClock(int hour,int min){

        Intent i=new Intent(this,AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.set(Calendar.MINUTE,min);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),60*24*60*1000,pi);

    }




}
