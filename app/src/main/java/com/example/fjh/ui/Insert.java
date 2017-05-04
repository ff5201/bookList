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

public class Insert extends AppCompatActivity {

    /* private EditText ID;
     private EditText name;
     private EditText price;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        DBconnection.setContext(this.getApplicationContext());
        Button btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ID = (EditText) findViewById(R.id.insert_id);
                EditText name = (EditText) findViewById(R.id.insert_name);
                EditText price = (EditText) findViewById(R.id.insert_price);
                String bookID = ID.getText().toString();
                String bookName = name.getText().toString();
                String bookPrice = price.getText().toString();
                Controller controller = new Controller();
                if (bookID.equals("") || bookName.equals("") || bookPrice.equals("")) {
                    new Builder(Insert.this).setMessage("图书信息不全！").show();
                } else {
                    if (controller.addBook(bookID, bookName, bookPrice)) {
                        ID.setText("");
                        name.setText("");
                        price.setText("");
                        buildDialog();
                    } else {
                        new Builder(Insert.this).setMessage("已有由此图书").show();
                    }
                }
            }
        });
    }

    private void buildDialog() {
        Builder builder = new Builder(Insert.this);
        builder.setTitle("添加成功，是否继续添加图书");
        builder.setNegativeButton("返回首页", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whitcButton) {
                finish();
            }
        });
        builder.setPositiveButton("继续添加", null);
        builder.show();
    }
}
