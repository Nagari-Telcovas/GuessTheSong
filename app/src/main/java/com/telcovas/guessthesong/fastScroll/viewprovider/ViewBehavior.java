package com.telcovas.guessthesong.fastScroll.viewprovider;


public interface ViewBehavior {
    void onHandleGrabbed();
    void onHandleReleased();
    void onScrollStarted();
    void onScrollFinished();
}
