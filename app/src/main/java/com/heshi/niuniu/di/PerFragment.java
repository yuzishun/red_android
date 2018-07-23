package com.heshi.niuniu.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by min on 2017/3/1.
 */
@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}
