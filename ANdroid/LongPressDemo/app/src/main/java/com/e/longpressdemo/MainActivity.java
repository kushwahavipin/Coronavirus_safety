package com.e.longpressdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView cm;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case Menu.FIRST:
                cm.setText(item.getTitle());
                break;
            case Menu.FIRST+1:
                cm.setText(item.getTitle());
                break;
                case Menu.FIRST+2:
            cm.setText(item.getTitle());
            break;
            case Menu.FIRST+3:
            cm.setText(item.getTitle());
            break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, Menu.FIRST,Menu.NONE,"Samsung");
        menu.add(0,Menu.FIRST+1,Menu.NONE,"HTC");
        menu.add(0,Menu.FIRST+2,Menu.NONE,"Sony");
        menu.add(0,Menu.FIRST+3,Menu.NONE,"One Plus");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cm=findViewById(R.id.cm);
        cm.setOnCreateContextMenuListener(this);

    }
}