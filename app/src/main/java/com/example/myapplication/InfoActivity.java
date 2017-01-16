package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{


    TextView subject2,teachername,place;
    String data;
    int number;

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        if(hasFocus){
            SharedPreferences pref=getSharedPreferences(data+"info",MODE_PRIVATE);
            teachername.setText(pref.getString("teachername","").toString());
            place.setText(pref.getString("place","").toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button button_editsubject = (Button) findViewById(R.id.button_editsubject);
        Button button_teachername=(Button)findViewById(R.id.edit_teacher);
        Button button_place=(Button)findViewById(R.id.edit_place);
        Button button_finish=(Button)findViewById(R.id.button_finish);

        button_editsubject.setOnClickListener(InfoActivity.this);
        button_teachername.setOnClickListener(InfoActivity.this);
        button_place.setOnClickListener(InfoActivity.this);
        button_finish.setOnClickListener(InfoActivity.this);


        Intent intent=getIntent();
        data=intent.getStringExtra("subject_name");
        number=intent.getIntExtra("position",-1);



        subject2=(TextView)findViewById(R.id.textView_subjectname);
        subject2.setText(data);
        teachername=(TextView)findViewById(R.id.textView_teachername);
        place=(TextView)findViewById(R.id.textView_classplace);



    }

    public void onClick(View view){


//        如果把这个存储器放在onCreate方法里操作，那么每次点击修改后都会执行一次监听加载页面的方法，刚改的内容就会又被存储的信息覆盖掉了
        final SharedPreferences.Editor infomation=getSharedPreferences(data+"info",MODE_PRIVATE).edit();

        final EditText edit1=new EditText(InfoActivity.this);
        switch (view.getId()){
            case R.id.button_editsubject:{
                edit1.setHint(data);
                AlertDialog.Builder aa = new AlertDialog.Builder(InfoActivity.this);
                aa.setTitle("请输入课程名");
                aa.setView(edit1);
                aa.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        subject2.setText(edit1.getText());
                    }
                });
                aa.setNegativeButton("取消", null);
                aa.show();
                break;
            }

            case R.id.edit_teacher:{
                AlertDialog.Builder aa=new AlertDialog.Builder(InfoActivity.this);
                aa.setTitle("请输入教师名");
                aa.setView(edit1);
                aa.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        teachername.setText(edit1.getText().toString());
                        infomation.putString("teachername",teachername.getText().toString());
                        infomation.commit();
                    }
                });
                aa.setNegativeButton("取消", null);
                aa.show();
                break;
            }

            case R.id.edit_place:{
                AlertDialog.Builder aa=new AlertDialog.Builder(InfoActivity.this);
                aa.setTitle("请输入上课地点");
                aa.setView(edit1);
                aa.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        place.setText(edit1.getText().toString());
                        infomation.putString("place",place.getText().toString());
                        infomation.commit();

                    }
                });
                aa.setNegativeButton("取消", null);
                aa.show();
                break;
            }



            case R.id.button_finish:{
                Intent intent=getIntent();
                SharedPreferences.Editor editor=getSharedPreferences(Integer.toString(number),MODE_PRIVATE).edit();
                editor.putString("subjectname",subject2.getText().toString());
                editor.commit();


                Intent ss=new Intent();
                ss.putExtra("data_return",subject2.getText().toString());
                setResult(RESULT_OK,ss);
                finish();
                break;
            }

            default:break;
        }
    }
}
