package com.example.fjh.ui;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;

import com.example.fjh.booklist.R;
import com.example.fjh.control.Controller;
import com.example.fjh.db.DBconnection;

public class Sett extends AppCompatActivity {

    private EditText name;
    private EditText id;
    private EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sett);
        DBconnection.setContext(this.getApplicationContext());
        id = (EditText) findViewById(R.id.set_id);
        name = (EditText) findViewById(R.id.set_name);
        price = (EditText) findViewById(R.id.set_price);
        Button btn_set = (Button) findViewById(R.id.btn_set);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookid = id.getText().toString();
                String bookName = name.getText().toString();
                String bookPrice = price.getText().toString();
                Controller controller = new Controller();
                if (bookid.equals("") || bookName.equals("") || bookPrice.equals("")) {
                    new Builder(Sett.this).setMessage("图书信息不能为空").show();
                } else {
                    if (controller.setBook(bookid, bookName, bookPrice)) {
                        id.setText("");
                        name.setText("");
                        price.setText("");
                        buildDialog();
                    } else {
                        new Builder(Sett.this).setMessage("没有此编号图书，请重新输入！").show();
                    }
                }
            }
        });
    }

    private void buildDialog() {
        Builder builder = new Builder(Sett.this);
        builder.setTitle("修改成功，是否继续修改图书");
        builder.setNegativeButton("返回首页", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whitcButton) {
                finish();
            }
        });
        builder.setPositiveButton("继续继续", null);
        builder.show();
    }
}
