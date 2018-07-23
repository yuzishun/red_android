package com.heshi.niuniu.rx.data;




import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.model.Response;
import com.heshi.niuniu.rx.data.exception.ErrorHanding;
import com.heshi.niuniu.rx.data.exception.ServerException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by wali on 16/5/26.
 */
public class RxResultHelper {

    public static <T> Observable.Transformer<Response<T>, T> handleResult() {
        return new Observable.Transformer<Response<T>, T>() {
            @Override
            public Observable<T> call(Observable<Response<T>> apiResponseObservable) {
                return apiResponseObservable.flatMap(
                        new Func1<Response<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(Response<T> tApiResponse) {
                                if (tApiResponse == null) {
                                    Exception e = new NetworkConnectionException();
                                    ErrorHanding.handleError(e,null, MyApplication.application);
                                    return Observable.error(e);
                                } else if (tApiResponse.isSuccess()) {
                                    return createData(tApiResponse.data);
                                } else {
                                    Exception exception = new ServerException(tApiResponse.code(), tApiResponse.message);
                                    ErrorHanding.handleError(exception,tApiResponse.message, MyApplication.application);
                                    return Observable.error(exception);
                                }
                            }
                        }
                );
            }
        };
    }


    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

//    /**
//     * 对网络接口返回的Response进行分割操作
//     *
//     * @param response
//     * @param <T>
//     * @return
//     */
//    public static <T> Observable<T> flatResponse(final Response<T> response) {
//        return Observable.create(new Observable.OnSubscribe<T>() {
//
//            @Override
//            public void call(Subscriber<? super T> subscriber) {
//                if (response.isSuccess()) {
//                    if (!subscriber.isUnsubscribed()) {
//                        subscriber.onNext(response.data);
//                    }
//                } else {
//                    if (!subscriber.isUnsubscribed()) {
//                        subscriber.onError(new ServerException(response.code, response.message));
//                    }
//                    return;
//                }
//
//                if (!subscriber.isUnsubscribed()) {
//                    subscriber.onCompleted();
//                }
//
//            }
//        });
//    }

//    public static class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {
//        @Override
//        public Observable<T> call(Throwable throwable) {
//            //ExceptionEngine为处理异常的驱动器
//            return Observable.error(ExceptionEngine.handleException(throwable));
//        }
//    }
//    public static <T> Observable.Transformer<Response<T>, T> sTransformer() {
//
//        return responseObservable -> responseObservable.map(tResponse -> {
//            if (!tResponse.isSuccess()) throw new RuntimeException(tResponse.code);
//            return tResponse.data;
//        }).onErrorResumeNext(new HttpResponseFunc<>());
//    }

}

