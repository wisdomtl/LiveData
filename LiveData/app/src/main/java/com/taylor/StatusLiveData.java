package com.taylor;

import android.arch.lifecycle.MutableLiveData;

/**
 * the LiveData which stores {@link Status}.
 * it is easier to be accessed by different activity if it is an singleton
 */
public class StatusLiveData extends MutableLiveData<Status> {
    private StatusLiveData() {
    }

    private static class Holder {
        public static final StatusLiveData INSTANCE = new StatusLiveData();
    }

    public static StatusLiveData getInstance() {
        return Holder.INSTANCE;
    }
}
