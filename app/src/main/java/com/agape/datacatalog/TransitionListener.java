package com.agape.datacatalog;

import android.transition.Transition;

public interface TransitionListener {
    void onTransitionStart(Transition transition);

    void onTransitionEnd(Transition transition);

    void onTransitionCancel(Transition transition);

    void onTransitionPause(Transition transition);

    void onTransitionResume(Transition transition);
}
