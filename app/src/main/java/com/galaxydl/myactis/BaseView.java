package com.galaxydl.myactis;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
