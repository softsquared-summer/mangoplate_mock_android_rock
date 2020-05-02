package com.example.mangoplate.src.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.mangoplate.src.ApplicationClass;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.interfaces.SearchTapRetrofitInterface;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.models.Result;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.models.ResultList;
import com.example.mangoplate.src.main.models.DefaultResponse;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;
import com.example.mangoplate.src.main.interfaces.MainActivityView;
import com.facebook.login.LoginManager;
import com.kakao.auth.AuthType;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
public class MainActivity extends BaseActivity implements MainActivityView { // 여기서는 뷰만 컨트롤
    private TextView mTvHelloWorld;
    private ImageView mbtn_facebook_login;

    private FacebookSessionCallback mLoginCallback;
    private CallbackManager mCallbackManager;
    private String mMainJsonString;
    private KakaotalkSessionCallback sessionCallback = new KakaotalkSessionCallback(this);
    Session session;
    private ImageView mbtnCustomLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHashKey();//VbKsFknDcqZ3CHUR47Mlsw2cGOU=
// mainApplication.java

        FacebookSdk.sdkInitialize(getApplicationContext());// 페이스북은 이렇게 카카오톡은 Application 수정해야함.
        setContentView(R.layout.activity_main);


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


        mbtnCustomLogin =  findViewById(R.id.main_kakaobutton);
//        btn_custom_login_out = (TextView) findViewById(R.id.btn_custom_login_out);




        mbtnCustomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session = Session.getCurrentSession();
                session.addCallback(sessionCallback);

                session.open(AuthType.KAKAO_LOGIN_ALL, MainActivity.this);


            }
        });





        AccessToken token = AccessToken.getCurrentAccessToken();
        mMainJsonString="{\"at\" : \""+token.getToken()+"\"}";
        Log.e("보내줘",""+mMainJsonString);
        System.out.println("제발");
        mbtn_facebook_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(MainActivity.this,
                        Arrays.asList("public_profile", "email"));
                AccessToken token = AccessToken.getCurrentAccessToken();
                mMainJsonString="{\"at\" : \""+token.getToken()+"\"}";
                Log.e("보낼려는 토큰",""+mMainJsonString);
                main_tryPost("facebook",mMainJsonString);

                if (token != null) {

                    Log.e("토큰", "Token: " + token.getToken());
                    Log.e("유저아이디.", "UserID: " + token.getUserId());
                }
                loginManager.registerCallback(mCallbackManager, mLoginCallback);
            }
        });
        // If using in a fragment
//        facebookLoginButton.setFragment(this);
        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new FacebookSessionCallback(this);

//        mbtn_facebook_login.setReadPermissions(Arrays.asList("public_profile", "email"));
//        mbtn_facebook_login.registerCallback(mCallbackManager, mLoginCallback);
        // Callback registration




    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

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
// 이름이 같아서 나도 헷갈려
    private void main_tryPost(String type, String jsonString) {
        showProgressDialog();

        final MainService mainService = new MainService(this);
//        mainService.getTest();
        mainService.tryPost(type,jsonString);
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


//            case main_facebookbutton:
//                break;
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        com.kakao.auth.Session.getCurrentSession().removeCallback(sessionCallback);
    }
}
