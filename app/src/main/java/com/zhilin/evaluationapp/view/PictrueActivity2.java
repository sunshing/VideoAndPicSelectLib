package com.zhilin.evaluationapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.zhilin.evaluationapp.R;
import com.zhilin.evaluationapp.util.XSYTool;

import java.util.ArrayList;

/**
 * Created by SongCaiBain on 2018/1/11.
 */

public class PictrueActivity2 extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();
    private int MaxPicNum = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictrue_activity2);
        initView();
        initData();
    }

    private void initView() {
        ImageView iv_add = (ImageView) findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
    }

    private void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d(TAG, "数量：" + list.size());
                    XSYTool.showToastmsg(this,"数量:"+list.size());
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    XSYTool.showToastmsg(this,"数量:"+ListExtra.size());
                    break;
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                defaultSelectPic();
                break;
        }
    }

    private void defaultSelectPic() {
        PhotoPickerIntent intent = new PhotoPickerIntent(PictrueActivity2.this);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCarema(true); // 是否显示拍照
        if (imagePaths.size() == 0) {
            intent.setMaxTotal(MaxPicNum - imagePaths.size()); // 最多选择照片数量，默认为6
        } else {
            intent.setMaxTotal(MaxPicNum + 1 - imagePaths.size()); // 最多选择照片数量，默认为6
        }
        intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
        startActivityForResult(intent, REQUEST_CAMERA_CODE);

    }

    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            inflater = LayoutInflater.from(PictrueActivity2.this);
        }

        public int getCount() {
            return listUrls.size();
        }

        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.pic_grid_item, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final String path = listUrls.get(position);
            if (path.equals("paizhao")) {
                holder.image.setImageResource(R.mipmap.find_add_img);
            } else {
                Glide.with(PictrueActivity2.this)
                        .load(path)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
            }
            return convertView;
        }

        class ViewHolder {
            ImageView image;
        }
    }
}
