package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private String idtitle;
    static String[] project = new String[35];
    private boolean empty = true;

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        if (hasFocus) {
//            SharedPreferences pref = getSharedPreferences("logininfo", MODE_PRIVATE);
//            username.setText(pref.getString("name", ""));
//            password.setText(pref.getString("pwd", ""));
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.edittext_username);
        password = (EditText) findViewById(R.id.password);


        Button button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(this);


    }

    //点击登陆后，将数据传到下一个活动，同时调用getcookie，使连接。
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login: {


                final String name = username.getText().toString();
                final String pwd = password.getText().toString();

                getCookie(name, pwd);

                Intent intent_1_2 = new Intent(LoginActivity.this, MainActivity.class);
                intent_1_2.putExtra("username", idtitle);
                intent_1_2.putExtra("project", project);
                startActivity(intent_1_2);
                finish();

                break;
//                }

            }

            default:
                break;

        }
    }

    public void getCookie(final String username, final String passwd) {
        if (username.isEmpty() || passwd.isEmpty()) {
            empty = false;
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_LONG).show();
        } else {
            Thread th = new Thread() {
                final String name = username;
                final String pass = passwd;

                public void run() {
                    Connection.Response ress = null;
                    try {

                        //获得cookie
                        ress = Jsoup.connect("http://jwxt.sdu.edu.cn:7890/pls/wwwbks/bks_login2.login")
                                .data("stuid", name, "pwd", pass)
                                .method(Connection.Method.POST)
                                .execute();

                        //爬取网页源代码。cookie从ress中获得。由于ress能够登陆成功，所以能够获取其cookie，此处可以用ress。cookies方法获取之。
                        Connection.Response res2 = Jsoup.connect("http://jwxt.sdu.edu.cn:7890/pls/wwwbks/xk.CourseView")
                                .cookies(ress.cookies()).method(Connection.Method.GET).execute();

                        //把源代码以doc格式存储
                        Document doc = res2.parse();

                        //找到这个table，符合条件的有两个，一个是课表，一个是学分表
                        Elements tablethis = doc.getElementsByAttributeValue("bgcolor", "#F2EDF8");
                        //需要的是第一个table，所以get(0)
                        Element table = tablethis.get(0);


                        //这个注释掉的方法可以用来解析出星期几和第几节的文本，但是可以直接设置在布局中
//            Elements tds=table.select("td[bgcolor=#E2D8EF");
//
//            for(Element project:tds){
//                String name=project.text();
//
//                System.out.println(name);
//            }

                        //这个方法用来解析课程内容，并把35节课的内容存储到一个容量为35的数组project中
                        Elements tds2 = table.select("td[bgcolor=#EAE2F3");

                        for (int i = 0; i < 35; i++) {
                            String name = tds2.get(i).text();
                            project[i] = name;
                        }

                        //这个部分用来解析学生姓名
                        Elements spans = doc.select("span.td1");
                        Element span = spans.get(2);
                        String idname = span.text();
                        String[] idid = idname.split(" ");
                        idtitle = idid[1] + "的" + idid[2];


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            th.start();
            try {
                th.join();
            } catch (java.lang.InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

