package com.heshi.niuniu.ui.main.connect.new_friend;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.heshi.niuniu.R;
import com.heshi.niuniu.adapter.contact.NewFriendAdapter;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.model.NewFriendModel;
import com.heshi.niuniu.model.Response;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.UiUtils;
import com.heshi.niuniu.util.Utils;
import com.heshi.niuniu.util.recyclerview.DividerItemDecoration;
import com.heshi.niuniu.util.recyclerview.manager.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class NewFriendPresent extends BasePresenter<NewFriendContract.Model>
        implements NewFriendContract.Presenter, NewFriendAdapter.operationLister {

    private List<NewFriendModel> list = new ArrayList<>();
    private NewFriendApi api;
    private HttpDialog dialog;
    private NewFriendAdapter adapter;

    @Inject
    public NewFriendPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(NewFriendApi.class);
        dialog = new HttpDialog(mActivity);

    }


    @Override
    public void initAdapter(RecyclerView recyclerView) {
        adapter = new NewFriendAdapter(mActivity, list);
        recyclerView.setLayoutManager(new ScrollLinearLayoutManager(mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity,
                LinearLayout.VERTICAL, UiUtils.dp2px(1, mActivity)
                , mActivity.getResources().getColor(R.color.background)));

        recyclerView.setAdapter(adapter);
        adapter.setLister(this);
    }

    @Override
    public void getFriendList(String user_id, final int type) {
        dialog.show();

        Observable<Response<List<NewFriendModel>>> observable = null;

        switch (type) {
            case 0:
                observable = api.getNewFriend(user_id);
                break;
            case 1:
                observable = api.getOldFiend(user_id);
                break;
        }

        addSubscription(observable.compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<List<NewFriendModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(List<NewFriendModel> newFriendModels) {
                        for (NewFriendModel mode :
                                newFriendModels) {
                            mode.setType(type);
                        }

                        list.addAll(newFriendModels);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void agreeOperation(String user_id, String friend_id, final int position) {
        dialog.show();

        addSubscription(api.agreeOperation(user_id, friend_id)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(Object o) {
                        list.get(position).setType(2);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });

    }


    @Override
    public void agree(int position) {
        NewFriendModel model = list.get(position);
        agreeOperation(Constants.USER_ID, String.valueOf(model.getUser_id()), position);
    }
}
