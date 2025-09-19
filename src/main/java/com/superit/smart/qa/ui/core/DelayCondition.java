package com.superit.smart.qa.ui.core;


public interface DelayCondition<T> {

    boolean check(T element);
}