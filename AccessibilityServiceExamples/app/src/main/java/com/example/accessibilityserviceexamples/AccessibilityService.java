package com.example.accessibilityserviceexamples;

import static android.content.ContentValues.TAG;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {
    String name;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    if(event.getEventType()==AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED){
      if(event.getPackageName().toString().equals("com.whatsapp")){
          StringBuilder message=new StringBuilder();
          if(!event.getText().isEmpty()){
              for(CharSequence subText: event.getText()){
                  message.append(subText);
                  Log.e(TAG, "onAccessibilityEvent: "+message );
              }
              if(message.toString().contains("Message from")){
                  name=message.toString().substring(13);
              }
          }
      }
    }
    }

    @Override
    public void onInterrupt() {
        Log.e(TAG, "onInterrupt: interrupt is pressed" );

    }

    @Override
    protected void onServiceConnected() {
        System.out.println("onService Connected");
        AccessibilityServiceInfo info=new AccessibilityServiceInfo();
        info.eventTypes=AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.eventTypes=AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType=AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.notificationTimeout=100;
        info.packageNames=null;
        setServiceInfo(info);

    }
}
