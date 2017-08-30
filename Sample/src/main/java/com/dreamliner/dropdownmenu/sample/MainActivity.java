/*
 * Copyright (c) 2016  DreamLiner Studio
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dreamliner.dropdownmenu.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.dreamliner.lib.dropdownmenu.DropdownMenu;
import com.dreamliner.lib.dropdownmenu.adapter.SelectAdapter;

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
