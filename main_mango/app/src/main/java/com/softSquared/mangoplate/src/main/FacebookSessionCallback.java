package com.softSquared.mangoplate.src.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.softSquared.mangoplate.src.home.HomeAcitivity;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

public class FacebookSessionCallback implements FacebookCallback<LoginResult> {



    Activity act;
    public FacebookSessionCallback(Activity act)
    {
        this.act=act;
    }

    // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.e("Callback :: ", "onSuccess");
        requestMe(loginResult.getAccessToken());
        Intent Homemove=new Intent(act, HomeAcitivity.class);
        act.startActivity(Homemove);
        act.finish();
    }

    // 로그인 창을 닫을 경우, 호출됩니다.
    @Override
    public void onCancel() {
        Log.e("Callback :: ", "onCancel");
    }

    // 로그인 실패 시에 호출됩니다.
    @Override
    public void onError(FacebookException error) {
        Log.e("Callback :: ", "onError : " + error.getMessage());
    }

    // 사용자 정보 요청
    public void requestMe(AccessToken token) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(token,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.e("result",object.toString());
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }
}
/*
LoginCallback이라는 클래스를 새로 생성해주시고 다음 코드를 넣습니다.
LoginCallback클래스는 Facebook에서 제공하는 FacebookCallback<LoginResult>를 상속해서 만들었고,
성공 시 onSuccess()를 호출
로그인 화면을 닫아 로그인을 취소했을 시 onCancel()을 호출
에러상황으로 로그인을 실패했을 경우 onError()을 호출
이렇게 결과를 받을 수 있는 부분을 구성하였습니다.
 */