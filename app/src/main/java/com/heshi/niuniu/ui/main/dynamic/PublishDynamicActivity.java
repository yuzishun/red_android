package com.heshi.niuniu.ui.main.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.adapter.ImagePickerAdapter;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.util.ImagePickerUtil;
import com.heshi.niuniu.util.LuBanCompress;
import com.heshi.niuniu.util.SelectDialog;
import com.heshi.niuniu.util.http.HttpParams;
import com.heshi.niuniu.util.recyclerview.DividerGridItemDecoration;
import com.heshi.niuniu.util.recyclerview.manager.ScrollGridLayoutManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;

import static com.heshi.niuniu.util.UiUtils.dp2px;

/**
 * Created by Administrator on 2018/7/19 0019.
 */

public class PublishDynamicActivity extends BaseActivity<PublishDynamicPresent>
        implements PublishDynamicContract.Model, ImagePickerAdapter.OnRecyclerViewItemClickListener, TextWatcher {

    @BindView(R.id.text_add_left)
    TextView textAddLeft;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_add_right)
    TextView textAddRight;
    @BindView(R.id.rec_dynamic_photo)
    RecyclerView recDynamicPhoto;
    @BindView(R.id.text_dynamic_publish)
    EditText textDynamicPublish;


    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    private ImagePickerAdapter adapter;
    public static final int REQUEST_CODE_TAKE_SELECT = 107;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int IMAGE_ITEM_ADD = -1;
    private ArrayList<ImageItem> images;


    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(activityModule)
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_dynamic;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        textTitle.setText("");
        textAddLeft.setVisibility(View.VISIBLE);
        textAddRight.setVisibility(View.VISIBLE);
        textAddRight.setText("发表");
        textDynamicPublish.addTextChangedListener(this);
        textAddRight.setEnabled(false);

        initImagePacker();

    }

    /**
     * 初始化单据选择控件
     */
    private void initImagePacker() {
        ImagePickerUtil.initImageMorePicker();
        // 添加图片
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, 9);
        adapter.setOnItemClickListener(this);
        recDynamicPhoto.addItemDecoration(new DividerGridItemDecoration(mContext, dp2px(5, mContext), mContext.getResources().getColor(R.color.colorWhite)));
        ScrollGridLayoutManager gridLayoutManager = new ScrollGridLayoutManager(this, 3);
        gridLayoutManager.setScrollEnabled(false);
        recDynamicPhoto.setLayoutManager(gridLayoutManager);
        recDynamicPhoto.setHasFixedSize(true);
        recDynamicPhoto.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                //打开选择,本次允许选择的数量
                                //                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(mContext, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, selImageList);
                                startActivityForResult(intent, REQUEST_CODE_TAKE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(9);
                                Intent intent1 = new Intent(mContext, ImageGridActivity.class);
                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, selImageList);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:

                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);

        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                //添加图片返回
                if (requestCode == REQUEST_CODE_SELECT) {

                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (images != null) {
                        selImageList.clear();
                        selImageList.addAll(images);
                        adapter.setImages(selImageList);
                    }

                } else if (requestCode == REQUEST_CODE_TAKE_SELECT) {
                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

                    selImageList.addAll(images);
                    adapter.setImages(selImageList);

                }

            } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
                //预览图片返回
                if (requestCode == REQUEST_CODE_PREVIEW) {
                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                    if (images != null) {
                        selImageList.clear();
                        selImageList.addAll(images);
                        adapter.setImages(selImageList);
                    }
                }

            }

        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (charSequence.length() > 0) {
            textAddRight.setTextColor(getResources().getColor(R.color.color_56D23B));
            textAddRight.setEnabled(true);

        } else {
            textAddRight.setTextColor(getResources().getColor(R.color.color_999999));
            textAddRight.setEnabled(false);

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @OnClick({R.id.text_add_left, R.id.text_add_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_add_left:
                appManager.finishActivity();
                break;
            case R.id.text_add_right:
                String data = textDynamicPublish.getText().toString().trim();

                HttpParams params = new HttpParams();
                for (ImageItem item : selImageList) {
//                    params.put("mFile", LuBanCompress.compressWithLs(new File(item.path), mContext));
                    params.put("mFile", item.path);

                }
                params.put("user_name", Constants.userName);
                params.put("txt_meta", data);
                List<MultipartBody.Part> list = params.setMoreImgType();

                mPresenter.publishDynamic(list);


                break;
        }
    }

}
