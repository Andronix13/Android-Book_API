package com.enigma.nro9xsilentspear13.whowroteitloader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader {
    private String mQueryString;

    public BookLoader(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Nullable
    @Override
    public Object loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}
