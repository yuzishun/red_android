package com.heshi.niuniu.fragment.main.dynamic;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.adapter.dynamic.DynamicListAdapter;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.model.DynamicInfoModel;
import com.heshi.niuniu.model.DynamicListModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.util.GlideUtils;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.ToashUtils;
import com.heshi.niuniu.util.UiUtils;
import com.heshi.niuniu.util.popwindow.CommentPopup;
import com.heshi.niuniu.util.recyclerview.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class DynamicPresent extends BasePresenter<DynamicContract.Model>
        implements DynamicContract.Presenter, View.OnClickListener, DynamicListAdapter.setPopListener {

    private DynamicApi api;
    public HttpDialog dialog;
    private List<DynamicListModel> list = new ArrayList<>();
    private DynamicListAdapter adapter;
    private ImageView msgHead;
    private View msgView;
    private ImageView head;
    private ImageView bac;
    private View msgCount;
    private View view;
    private TextView nickName;
    private List<String> zanList = new ArrayList<>();
    private int count;

    //评论一级
    private List<String> commentOneList = new ArrayList<>();
    private int commentCount;
    private CommentPopup mCommentPopup;

    @Inject
    public DynamicPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(DynamicApi.class);
        dialog = new HttpDialog(mActivity);

    }


    @Override
    public void initAdapter(RecyclerView recyclerView) {
        adapter = new DynamicListAdapter(mActivity, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity,
                LinearLayout.VERTICAL, UiUtils.dp2px(10, mActivity)
                , mActivity.getResources().getColor(R.color.background)));
        recyclerView.setAdapter(adapter);
        adapter.setSetPopListener(this);

        view = LayoutInflater.from(mActivity).inflate(R.layout.view_dynamic_head, null);

        setHead(view);
    }

    private void setHead(View view) {
        bac = view.findViewById(R.id.img_dynamic_bac);
        head = view.findViewById(R.id.img_dynamic_head);
        msgView = view.findViewById(R.id.rel_dynamic_msg_head);
        msgHead = view.findViewById(R.id.img_dynamic_msg_head);
        msgCount = view.findViewById(R.id.text_dynamic_msg_count);
        nickName = view.findViewById(R.id.text_dynamic_name);

        msgView.setOnClickListener(this);

    }

    @Override
    public void getDynamicList(String user_name, final int page) {

        addSubscription(api.getDynamicList(user_name, String.valueOf(page))
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<List<DynamicListModel>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("sd", list + "");
                        mModel.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mModel.complete();
                        dialog.dismiss();
//                        adapter.setHeaderView(view);
                    }

                    @Override
                    public void onNext(List<DynamicListModel> dynamicListModels) {
                        if (page == 1) {
                            list.clear();
                        }

                        //设置图片
                        for (DynamicListModel mode : dynamicListModels) {
                            zanList.add(String.valueOf(mode.getCircle_id()));
                            List<String> urlList = setURL(mode.getPic_meta());
                            mode.setUrlList(urlList);
                        }
                        list.addAll(dynamicListModels);

                        adapter.notifyDataSetChanged();
                        adapter.setHeaderView(view);

                        if (dynamicListModels.size() < 10) {
                            mModel.close();
                            mModel.noLoadMode();
                            if (page > 1) {
                                ToashUtils.show(mActivity, "已经是最后一页数据", 2000, Gravity.CENTER);
                            }
                        } else {
                            mModel.defaultMode();
                        }

                        adapter.notifyDataSetChanged();
                        adapter.setHeaderView(view);
                        dialog.dismiss();
//                        for (String s :
//                                zanList) {
//                            getZanData(s);
//                            getOneStepComment(s);
//                        }
                    }
                });
    }

    @Override
    public void getDynamicInfo(String username) {
        addSubscription(api.getDynamicInfo(username)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<DynamicInfoModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DynamicInfoModel dynamicInfoModel) {
                        setDetailInfo(dynamicInfoModel);
                    }
                });

    }

    @Override
    public void setDetailInfo(DynamicInfoModel model) {
        GlideUtils.loadImg(Constants.Icon_url + model.getUser_id(), head);
        nickName.setText(model.getNick_name());

//        msgView

    }

    @Override
    public void getZanData(String circle_id) {
        addSubscription(api.getZanList(Constants.userName, circle_id)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e("sdf", o.toString() + "");
                        count++;

                        if (count < zanList.size()) {
                            getZanData(zanList.get(count));
                        }

                    }
                });
    }

    @Override
    public void getOneStepComment(String circle_id) {
        addSubscription(api.getOneStepComment(Constants.userName, circle_id)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        commentCount++;

                        if (commentCount < commentOneList.size()) {
                            getOneStepComment(commentOneList.get(commentCount));
                        }

                    }
                });

    }

    /**
     * 处理图片Url地址
     *
     * @param data
     * @return
     */
    private List<String> setURL(String data) {
        List<String> list = new ArrayList<>();
        list.clear();
        Pattern r = Pattern.compile("\\d+");

        // 现在创建 matcher
        Matcher m = r.matcher(data);
        while (m.find()) {
            list.add(String.format(Constants.Dynamic_Img_url + "%s", m.group()));
        }

        return list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_dynamic_msg_head:


                break;


        }

    }

    public void showPop() {
        mCommentPopup = new CommentPopup(mActivity);
        mCommentPopup.setOnCommentPopupClickListener(new CommentPopup.OnCommentPopupClickListener() {
            @Override
            public void onLikeClick(View v, TextView likeText) {
                if (v.getTag() == null) {
                    v.setTag(1);
                    likeText.setText("取消");
                } else {
                    switch ((int) v.getTag()) {
                        case 0:
                            v.setTag(1);
                            likeText.setText("取消");
                            break;
                        case 1:
                            v.setTag(0);
                            likeText.setText("赞  ");
                            break;
                    }
                }
            }

            @Override
            public void onCommentClick(View v) {
                ToashUtils.show(mActivity, "评论");
            }
        });
    }

    @Override
    public void setComment(View view, int position) {
        mCommentPopup.showPopupWindow(view);

    }
}
