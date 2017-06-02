package com.example.wilder.myapplicationgraphapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ShareDialog shareDialog;
    private String name, surname, imageUrl;
    private String TAG = "MainActivity";
    private TextView nameView;
    private ImageView profileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);


        Bundle inBundle = getIntent().getExtras();

        new GraphRequest(
                AccessToken.getCurrentAccessToken(), "/me/live_videos", null, HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.e(TAG,response.getJSONObject().toString());
                    }
                }
        ).executeAsync();


        if (inBundle == null){
            Intent login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(login);
            finish();
        } else {
            name = inBundle.getString("name");
            surname = inBundle.getString("surname");
            imageUrl = inBundle.getString("imageUrl");

            nameView = (TextView) findViewById(R.id.nameAndSurname);
            nameView.setText("" + name + " " + surname);

            profileView = (ImageView) findViewById(R.id.profileImage);
            DownLoadImage photo = new DownLoadImage(profileView);
            photo.execute(imageUrl);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share:
                share();
                break;

            case R.id.getPosts:
                postLive();
                break;

            case R.id.logout:
                logout();
                break;
        }
    }

    public void share(){
        shareDialog = new ShareDialog(this);
        List<String> taggedUserIds= new ArrayList<String>();
        taggedUserIds.add("{USER_ID}");
        taggedUserIds.add("{USER_ID}");
        taggedUserIds.add("{USER_ID}");

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("http://www.sitepoint.com"))
                .setContentTitle("This is a content title")
                .setContentDescription("This is a description")
                .setShareHashtag(new ShareHashtag.Builder().setHashtag("#sitepoint").build())
                .setPeopleIds(taggedUserIds)
                .setPlaceId("{PLACE_ID}")
                .build();

        shareDialog.show(content);
    }


    public void postLive(){

        Intent camera =new Intent(MainActivity.this, StreamingActivity.class);
        camera.putExtra("name", name);
        camera.putExtra("surname", surname);
        camera.putExtra("imageUrl", imageUrl.toString());
        startActivity(camera);


    }




    /**
     * private void getPosts(){

     new GraphRequest(
     AccessToken.getCurrentAccessToken(), "/me/posts", null, HttpMethod.GET,
     new GraphRequest.Callback() {
     public void onCompleted(GraphResponse response) {
     Log.e(TAG,response.toString());
     }
     }
     ).executeAsync();
     }*/

    public void logout(){
        LoginManager.getInstance().logOut();
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }

}
