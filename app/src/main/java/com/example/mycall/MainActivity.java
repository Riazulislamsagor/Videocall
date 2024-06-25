package com.example.mycall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {
    EditText userIdedit;
    Button startbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIdedit=findViewById(R.id.userId);
        startbtn=findViewById(R.id.startId);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=userIdedit.getText().toString().trim();

                if (userID.isEmpty())
                    return;

                Intent intent=new Intent(MainActivity.this, CallActivity.class);
                intent.putExtra("userId",userID);
                startActivity(intent);
                startservice(userID);
            }


        });

    }

    void startservice(String userID){
        Application application =getApplication() ; // Android's application context
        long appID = 292669903;   // yourAppID
        String appSign ="46e48d96fb82bd2d9001e17177c5ce97c286e88908b3170b076a706bf872e307";  // yourAppSign
        String userName =userID;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        ZegoNotificationConfig notificationConfig=new ZegoNotificationConfig();
        notificationConfig.sound="zeco_uikit_call";
        notificationConfig.channelID="callinvitation";
        notificationConfig.channelName="CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(),appID,appSign,userID,userName,callInvitationConfig);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}