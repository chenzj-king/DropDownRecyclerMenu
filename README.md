# DropDownRecyclerMenu
-----

这个项目是在项目中刚好需要做一個筛选.类似大部分团购等等的筛选过滤器.搜索了一下现有成的轮子.都不太符合需求.只好自己撸一個.主要是pop+RecyclerView来进行定制弹出的筛选框.   

## 例子
![](http://i.imgur.com/q3Yjaaj.gif)

## Usage

## JitPack.io

我把项目放到了[jitpack.io](https://jitpack.io).如果要使用请按照如下对项目进行配置.

    repositories {
    	//...
    	maven { url "https://jitpack.io" }
	}

	dependencies {
		//...
    	compile 'com.github.chenzj-king:DropDownRecyclerMenu:1.0'
	}


##使用方式

###  在xml中下面sample代码一样配置相关属性:  

    <com.dreamliner.dropdownmenu.DropdownMenu
        android:id="@+id/dropdown_menu2"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:gravity="center_vertical"
        android:minHeight="35dp"
        app:iconColor="#222"
        app:listBgColor="#fff"
        app:titleBgColor="@android:color/white"
        app:titleColor="#222"
        app:titleHighLight="#eb5310"
        app:titleText="状态状态状态状态状态状态"
        app:titleTextSize="14sp"
        />

### 在代码中这样设置

	String mFuTypes[] = {"高血压", "糖尿病", "老年人", "测试好长好长", "测试更加更加长"};
    DropdownMenu dropdownMenu1 = (DropdownMenu) findViewById(R.id.dropdown_menu1);
    RecyclerView.Adapter adapter1 = dropdownMenu1.getDropdownAdapter();
    if (null != adapter1 && adapter1 instanceof SelectAdapter) {
        ((SelectAdapter) adapter1).update(mFuTypes);
    }


## License ##

    Copyright (c) 2016 chenzj-king

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    Come on, don't tell me you read that.

## About me ##

- **QQ:** 364972027
- **Weibo:** [http://weibo.com/u/1829515680](http://weibo.com/u/1829515680)
- **Email:** admin@chenzhongjin.cn
- **Github:** [https://github.com/chenzj-king](https://github.com/chenzj-king)
- **Blog:** [http://www.chenzhongjin.cn](http://www.chenzhongjin.cn)