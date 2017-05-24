package com.example.wilder.myapplicationgraphapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wilder on 08/05/17.
 */
public class DownLoadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownLoadImage(ImageView bmImage){
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String urldisplay = params[0];
        Bitmap mIcon11 = null;

        try {
            InputStream inputStream = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error",e.getMessage());
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }
}
