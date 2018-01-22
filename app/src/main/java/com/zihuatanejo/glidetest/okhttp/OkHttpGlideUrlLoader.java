package com.zihuatanejo.glidetest.okhttp;

import android.content.Context;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import java.io.InputStream;
import okhttp3.OkHttpClient;

/**
 * Created by Nelson on 2018/1/22.
 */

public class OkHttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {


    private final OkHttpClient mOkHttpClient;

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {

        private OkHttpClient client;

        public Factory() {

        }

        public Factory(OkHttpClient client) {
            this.client = client;
        }

        private OkHttpClient getOkHttpClient() {
            if (client == null) {
                client = new OkHttpClient();
            }
            return client;
        }

        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context,
                GenericLoaderFactory factories) {
            return new OkHttpGlideUrlLoader(getOkHttpClient());
        }

        @Override
        public void teardown() {

        }
    }

    public OkHttpGlideUrlLoader(OkHttpClient client) {
        this.mOkHttpClient = client;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        return new OkHttpFetcher(mOkHttpClient, model);
    }
}
