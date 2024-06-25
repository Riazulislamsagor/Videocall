package com.example.mycall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {
    EditText useridedttext;
    TextView textView;
    ZegoSendCallInvitationButton videobtn,voicebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        videobtn=findViewById(R.id.videocallbtdId);
        voicebtn=findViewById(R.id.voicecallbtdId);
        useridedttext=findViewById(R.id.userId);
        textView=findViewById(R.id.heyusertextId);

        String userID=getIntent().getStringExtra("userId");
        textView.setText("Hey"+userID);

        useridedttext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String tergetUserID=useridedttext.getText().toString().trim();
                setvoicecall(tergetUserID);
                setvideocall(tergetUserID);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    void setvoicecall(String tergetUserID){
        voicebtn.setIsVideoCall(true);
        voicebtn.setResourceID("Zeco_ukit_call");
        voicebtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(tergetUserID)));
    }

    void setvideocall(String tergetUserID){
        videobtn.setIsVideoCall(true);
        videobtn.setResourceID("Zeco_ukit_call");
        videobtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(tergetUserID)));
    }
}