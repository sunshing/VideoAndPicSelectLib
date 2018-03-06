package com.zhilin.evaluationapp.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.privatee.mylibrary.Base.BaseActivity;
import com.zhilin.evaluationapp.R;

/**
 * Created by SongCaiBain on 2018/3/5.
 */

public class IndexActivity extends BaseActivity {
    @Override
    public String setNowActivityName() {
        return "IndexActivity";
    }

    @Override
    public int setLayout() {
        return R.layout.index_layout;
    }

    @Override
    public void inintView() {
        TextView tv1 = fvbi(R.id.tv1);
        TextView tv2 = fvbi(R.id.tv2);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
    }

    @Override
    public void inintData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                Intent intent = new Intent(this, PictrueActivity.class);
                startActivity(intent);
                break;
            case R.id.tv2:
                Intent intent2 = new Intent(this, VideoActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
