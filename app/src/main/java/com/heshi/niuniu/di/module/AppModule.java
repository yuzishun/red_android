package com.heshi.niuniu.di.module;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;


import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.cache.CacheLoader;
import com.heshi.niuniu.di.ContextLife;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

/**
 * 初始化网络请求
 */

@Module
public class AppModule {

    private MyApplication app;
    Request request;
    Request.Builder requestBuilder;

    public AppModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    public MyApplication provideApp() {
        return app;
    }

    @Provides
    @Singleton
    CacheLoader provideCacheLoader() {
        return CacheLoader.getInstance(app.getApplicationContext());
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        //数据拦截器
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.e("message", message);
//            }
//        });

        MoreBaseUrlInterceptor moreBaseUrlInterceptor = new MoreBaseUrlInterceptor();

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                request = chain.request()
                        .newBuilder()
                        .addHeader("appId", "ANDROID-1.0.0")
                        /**
                         * 若存在用户cookie则带入请求头
                         */
//                        .addHeader("cookie", TextUtils.isEmpty(Constants.USER_COOKIE) ? "" : Constants.USER_COOKIE)
                        //  .addHeader("token",MyApplication.appToken)
                        .build();

                Response response = chain.proceed(request);

//                if (response.request().url().toString().indexOf("login") != -1) {
//                    loginSaveCookie(response);
//                }
                return response;
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(moreBaseUrlInterceptor)
                .addInterceptor(interceptor)
                .build();


        return okHttpClient;
    }

    /**
     * 处理login的cookie
     */
    private void loginSaveCookie(Response originalResponse) throws IOException {
        Headers httpHead = originalResponse.headers();
        List<String> cookieHead = originalResponse.headers("Set-Cookie");
        if (cookieHead != null) {
            for (String cache : cookieHead) {
                if (!TextUtils.isEmpty(cache)) {
                    String[] arrays = cache.split(";");
                    if (arrays != null && arrays.length > 0) {
                        /**
                         * 将cookie放到用户信息中
                         */
                        String cookie = arrays[0];
                        String url = originalResponse.request().url().toString();
                        if (!TextUtils.isEmpty(url) && url.indexOf("login") != -1) {
                            Log.e("cookie", cookie);
//                            Constants.saveUserCookie(cookie);
                        }
                    }
                }
            }
        }
        //        originalResponse.close();//这个没有用的就是关闭请求而已
    }

    @Provides
    @Singleton
    Retrofit provideHotApi(OkHttpClient okHttpClient) {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Constants.Base_Url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit1;
    }


    public class MoreBaseUrlInterceptor implements Interceptor {

        private Uri url;
        private String data;

        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取原始的originalRequest
            Request originalRequest = chain.request();
            //获取老的url
            HttpUrl oldUrl = originalRequest.url();
            //获取originalRequest的创建者builder
            Request.Builder builder = originalRequest.newBuilder();
            //获取头信息的集合如：manage,mdffx
            List<String> urlnameList = originalRequest.headers("urlname");

            if (urlnameList.size()==0) {
                //删除原有配置中的值,就是namesAndValues集合里的值
                builder.removeHeader("urlname");
                //获取头信息中配置的value,如：manage或者mdffx
//                String urlname = urlnameList.get(0);
                HttpUrl baseURL = null;
//                //根据头信息中配置的value,来匹配新的base_url地址

                baseURL = HttpUrl.parse(Constants.Im_Url);

//                //重建新的HttpUrl，需要重新设置的url部分
                HttpUrl newHttpUrl = oldUrl.newBuilder()
                        .scheme(baseURL.scheme())//http协议如：http或者https
                        .host(baseURL.host())//主机地址
                        .port(baseURL.port())//端口
                        .build();

                //获取处理后的新newRequest
                Request newRequest = builder.url(newHttpUrl).build();
                return chain.proceed(newRequest);
            } else {
                return chain.proceed(originalRequest);
            }
        }
    }


}
