package com.heshi.niuniu.rx.data;



import com.heshi.niuniu.model.Response;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wukewei on 16/5/26.
 */
public class SchedulersCompat {


    private final static Observable.Transformer ioTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object o) {
            return ((Observable)o).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());//.onErrorResumeNext(new HttpResponseFunc<>())
        }
    };

    public static <T> Observable.Transformer<T, Response<T>> applyIoSchedulers() {
        return (Observable.Transformer<T, Response<T>>) ioTransformer;
    }

//    public static class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {
//        @Override
//        public Observable<T> call(Throwable throwable) {
//            //ExceptionEngine为处理异常的驱动器
//            return Observable.error(ExceptionEngine.handleException(throwable));
//        }
//    }
}
