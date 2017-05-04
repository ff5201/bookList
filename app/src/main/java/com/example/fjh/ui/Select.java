package com.example.fjh.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.fjh.booklist.R;
import com.example.fjh.control.Controller;
import com.example.fjh.db.DBconnection;
import com.example.fjh.model.Book;
import com.example.fjh.model.BookList;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        DBconnection.setContext(this.getApplicationContext());
        Controller controller = new Controller();
        BookList bookList = controller.searchBook();
        createTable(bookList);
    }

    private void createTable(BookList bookList) {
        TableLayout tb = (TableLayout) findViewById(R.id.searchTable);
        TableRow header = new TableRow(this);
        TextView hid = new TextView(this);
        hid.setText("编号");
        hid.setHeight(100);
        hid.setGravity(Gravity.CENTER);
        TextView hname = new TextView(this);
        hname.setText("书名");
        hname.setHeight(100);
        hname.setGravity(Gravity.CENTER);
        TextView hprice = new TextView(this);
        hprice.setText("价格");
        hprice.setHeight(100);
        hprice.setGravity(Gravity.CENTER);
        header.addView(hid);
        header.addView(hname);
        header.addView(hprice);
        tb.addView(header);
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            String id = book.getID();
            String name = book.getName();
            String price = book.getPrice();
            TableRow row = new TableRow(this);
            TextView tid = new TextView(this);
            TextView tname = new TextView(this);
            TextView tprice = new TextView(this);
            tid.setText(id);
            tid.setHeight(100);
            tid.setGravity(Gravity.CENTER);
            tname.setText(name);
            tname.setHeight(100);
            tname.setGravity(Gravity.CENTER);
            tprice.setText(price);
            tprice.setHeight(100);
            tprice.setGravity(Gravity.CENTER);
            row.addView(tid);
            row.addView(tname);
            row.addView(tprice);
            tb.addView(row);
        }
    }
}
