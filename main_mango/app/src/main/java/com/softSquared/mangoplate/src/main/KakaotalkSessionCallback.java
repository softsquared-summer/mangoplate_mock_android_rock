package com.softSquared.mangoplate.src.main;

import android.content.Intent;
import android.util.Log;

import com.softSquared.mangoplate.src.home.HomeAcitivity;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

public class KakaotalkSessionCallback implements ISessionCallback {

    String mKakaoId;
    String mKaKoImageUrl;
    String mKakaoName;

    String mKakaoJsonString;


//    참고로 profileUrl사진이 null 값이면 profileUrl에 null을 보내지 마시고 id와 name 값만 보내주세요! profileUrl은 무조건 형식을 지켜야 들어올 수 있도록 막아놨거든요

    MainActivity act;
    KakaotalkSessionCallback(MainActivity act)
    {
        this.act=act;
    }
    // 로그인에 성공한 상태
    @Override
    public void onSessionOpened() {
        requestMe();

    }

    // 로그인에 실패한 상태
    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
    }

    // 사용자 정보 요청
    public void requestMe() {
     UserManagement.getInstance()
                .me(new MeV2ResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        Log.i("KAKAO_API", "사용자 아이디: " + result.getId());
                        UserAccount kakaoAccount = result.getKakaoAccount();
                        if (kakaoAccount != null) {

                            // 이메일
                            String email = kakaoAccount.getEmail();

                            if (email != null) {
                                Log.i("KAKAO_API", "email: " + email);

//                                String birthday =kakaoAccount.getBirthday();
//                                String age=kakaoAccount.getAgeRange().getValue();
//                                // 동의 요청 후 이메일 획득 가능
//                                // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.
//                                Log.i("KAKAO_API", "birthday: " + birthday);
//                                Log.i("KAKAO_API", "age: " + age);
                            } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {


                            } else {
                                // 이메일 획득 불가
                            }

                            // 프로필
                            Profile profile = kakaoAccount.getProfile();

                            if (profile != null) {


                                mKakaoId= Integer.toString((int) result.getId());
                                mKakaoName=profile.getNickname();
                                mKaKoImageUrl=profile.getProfileImageUrl();

                                Log.e("어떻게"," "+mKakaoId+""+mKakaoName);
                                MainService mainService= new MainService(act);
                                Log.d("KAKAO_API", "nickname: " + profile.getNickname());
//                                PreferenceManager.setString(act, "nickname", ""+profile.getNickname()); 이건 다시 찾아보기
                                Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
                                Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());
                                Log.d("KAKAO API ", "HI " + kakaoAccount.profileNeedsAgreement());
                                if(mKaKoImageUrl != null)
//                                {
//                                    mKakaoJsonString="{\"id\":"+"\""+mKakaoId+"\","+"\"name\":"+"\","+mKakaoName+"\","+"\"profileUrl\":"+"\""+mKaKoImageUrl+"\""+"}";
//                                    Log.e("KAKAO JSON ", "HI " + mKakaoJsonString);
//                                }else{
//
//                                    mKakaoJsonString="{\"id\":"+"\""+mKakaoId+"\","+"\"name\":"+"\""+mKakaoName+"\""+"}";
//                                    Log.e("KAKAO JSON ", "HI " + mKakaoJsonString);
//                                }
                                mainService.tryPostKakaotalk(mKakaoId,mKakaoName);

                                Thread sleep=new Thread();
                                try {
                                    sleep.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }// 안되면 속도를 늦추면 되지 .

                                Intent intent =new Intent(act, HomeAcitivity.class);
                                act.startActivity(intent);
                                act.finish();
                            } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                // 동의 요청 후 프로필 정보 획득 가능



                            } else {
                                // 프로필 획득 불가
                            }
                        }
                    }
                });
    }



}