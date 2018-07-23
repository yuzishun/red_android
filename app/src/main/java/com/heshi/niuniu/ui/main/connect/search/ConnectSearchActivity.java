package com.heshi.niuniu.ui.main.connect.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.custom.edittext.ClearEditText;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.model.ConnectFriendModel;
import com.heshi.niuniu.ui.main.connect.friend_info.FriendInfoActivity;
import com.heshi.niuniu.util.UIHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class ConnectSearchActivity extends BaseActivity<ConnectSearchPresent>
        implements ConnectSearchContract.Model, TextWatcher {


    @BindView(R.id.edit_title_search)
    ClearEditText editTitleSearch;
    @BindView(R.id.img_add_right)
    TextView imgAddRight;
    @BindView(R.id.text_end_content)
    TextView textEndContent;
    @BindView(R.id.text_connect_empty)
    TextView textConnectEmpty;
    @BindView(R.id.view_search_one)
    LinearLayout viewSearchOne;
    @BindView(R.id.view_search_two)
    LinearLayout viewSearchTwo;
    private ConnectFriendModel contactModels;

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
        return R.layout.activity_connect_search;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        editTitleSearch.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        viewSearchOne.setVisibility(charSequence.length() == 0 ? View.GONE : View.VISIBLE);
        textEndContent.setText(charSequence + "");

        viewSearchTwo.setVisibility(View.GONE);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onSuccess(ConnectFriendModel contactModels) {
        this.contactModels = contactModels;

        if (contactModels == null) {
            viewSearchTwo.setVisibility(View.VISIBLE);
            viewSearchOne.setVisibility(View.GONE);

        } else {
            String str = textEndContent.getText().toString().trim();
            viewSearchTwo.setVisibility(View.GONE);
            Bundle data = new Bundle();
            data.putString("name", str);
            UIHelper.startActivity(mContext, FriendInfoActivity.class, data);
        }

    }


    @OnClick(R.id.view_search_one)
    public void onClick() {
        String str = textEndContent.getText().toString().trim();
        mPresenter.searchFriend(str + "");

    }

    @OnClick(R.id.img_add_right)
    public void onViewClicked() {
        appManager.finishActivity();
    }

}
