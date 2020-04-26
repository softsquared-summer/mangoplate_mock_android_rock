package com.example.mangoplate.src.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.example.mangoplate.src.home.HomeAcitivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;
import com.example.mangoplate.src.main.interfaces.MainActivityView;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends BaseActivity implements MainActivityView { // 여기서는 뷰만 컨트롤
    private TextView mTvHelloWorld;
    private ImageView mbtn_facebook_login;

    private FacebookCallback mLoginCallback;
    private CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHashKey();//VbKsFknDcqZ3CHUR47Mlsw2cGOU=
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // 이 두줄을 쓰면 타이틀 바를 없앨 수가 있습니다.
        FacebookSdk.sdkInitialize(getApplicationContext());// 여기 순서를 잘 지켜야지.
        setContentView(R.layout.mainactivity);


        mbtn_facebook_login = (ImageView) findViewById(R.id.main_facebookbutton);

//        mbtn_facebook_login = (LoginButton) findViewById(R.id.real_facebook_button);
//        mbtn_facebook_login.setReadPermissions("email"); // 페이스 북 로그인 처리


        ImageView main_menu = (ImageView) findViewById(R.id.main_menu);


        AppEventsLogger.activateApp(this);
        CallbackManager callbackManager;
        callbackManager = CallbackManager.Factory.create();
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(main_menu);
        Glide.with(this).load(R.drawable.mangoplate).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.mangoplate)
               .into(gifImage);
//        출처: https://gogorchg.tistory.com/entry/Android-Glide-에서-Gif-로드가-너무-느려요 [항상 초심으로]


        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(MainActivity.this,
                        Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(mCallbackManager, mLoginCallback);
            }
        });
        // If using in a fragment
//        facebookLoginButton.setFragment(this);
        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new FacebookCallback() {
            @Override
            public void onSuccess(Object o) {


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };

//        mbtn_facebook_login.setReadPermissions(Arrays.asList("public_profile", "email"));
//        mbtn_facebook_login.registerCallback(mCallbackManager, mLoginCallback);
        // Callback registration




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Intent Homemove=new Intent(MainActivity.this, HomeAcitivity.class);
        startActivity(Homemove);
    }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    private void tryGetTest() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
            default:
                break;
        }
    }
}
