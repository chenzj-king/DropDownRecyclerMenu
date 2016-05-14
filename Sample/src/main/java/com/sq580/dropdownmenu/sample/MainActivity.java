package com.sq580.dropdownmenu.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.sq580.dropdownmenu.DropdownMenu;
import com.sq580.dropdownmenu.adapter.SelectAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String mFuTypes[] = {"高血压", "糖尿病", "老年人", "测试好长好长", "测试更加更加长"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DropdownMenu dropdownMenu1 = (DropdownMenu) findViewById(R.id.dropdown_menu1);
        RecyclerView.Adapter adapter1 = dropdownMenu1.getDropdownAdapter();
        if (null != adapter1 && adapter1 instanceof SelectAdapter) {
            ((SelectAdapter) adapter1).update(mFuTypes);
        }

        DropdownMenu dropdownMenu2 = (DropdownMenu) findViewById(R.id.dropdown_menu2);
        RecyclerView.Adapter adapter2 = dropdownMenu2.getDropdownAdapter();
        if (null != adapter2 && adapter2 instanceof SelectAdapter) {
            ((SelectAdapter) adapter2).update(mFuTypes);
        }

    }
}
