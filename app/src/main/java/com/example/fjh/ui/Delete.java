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

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        DBconnection.setContext(this.getApplicationContext());
        final EditText delete_name = (EditText) findViewById(R.id.delete_name);
        Button btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname = delete_name.getText().toString();
                Controller controller = new Controller();
                if (bookname.equals("")) {
                    new Builder(Delete.this).setMessage("图书名不能为空！").show();
                } else {
                    if (controller.deleteBook(bookname)) {
                        delete_name.setText("");
                        buildDialog();
                    } else {
                        new Builder(Delete.this).setMessage("没有此图书！").show();
                    }
                }
            }
        });
    }

    private void buildDialog() {
        Builder builder = new Builder(Delete.this);
        builder.setTitle("删除成功，是否继续删除图书");
        builder.setNegativeButton("返回首页", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whitcButton) {
                finish();
            }
        });
        builder.setPositiveButton("继续删除", null);
        builder.show();
    }

}
