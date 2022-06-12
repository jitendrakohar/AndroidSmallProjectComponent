package com.example.accessibilityserviceexamples;

import static android.content.ContentValues.TAG;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.GestureDescription;
import android.app.Service;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayDeque;
import java.util.Deque;

public class GlobalActionBarService extends AccessibilityService {
   FrameLayout mLayout;
   String name;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
//Reading up the text for the screen UI from the screen
//        final int eventType=event.getEventType();
//        String eventText=null;
//        switch (eventType){
//            case AccessibilityEvent.TYPE_VIEW_CLICKED:
//                eventText="Focussed: ";
//                break;
//            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
//                eventText="Focussed: ";
//                break;
//        }
//        eventText=eventText+event.getContentDescription();
//        Toast.makeText(this, "message which is reading"+eventText, Toast.LENGTH_SHORT).show();
//

        //extracting the text from the screen using this function
//        if(event.getEventType()==AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED){
//            if(event.getPackageName().toString().equals("com.whatsapp")){
//                StringBuilder message=new StringBuilder();
//                if(!event.getText().isEmpty()){
//                    for(CharSequence subText: event.getText()){
//                        message.append(subText);
//                        Log.e(TAG, "onAccessibilityEvent: "+message );
//                    }
//                    if(message.toString().contains("Message from")){
//                        name=message.toString();
//                    }
//                }
//            }
//        }

        //REading the text using accessibilityNodeInfo mNodeINfo

            AccessibilityNodeInfo mNodeInfo = event.getSource();
            printEverything(mNodeInfo, 0);
//        Toast.makeText(this, "this app package name is "+event.getPackageName().toString(), Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
// set the types of events that this service wants to Listen to others
//        won't be passed to this services
//        AccessibilityServiceInfo info=new AccessibilityServiceInfo();
//        info.eventTypes=AccessibilityEvent.TYPE_VIEW_CLICKED | AccessibilityEvent.TYPE_VIEW_FOCUSED;
//        info.feedbackType=AccessibilityServiceInfo.FEEDBACK_SPOKEN;
//        info.notificationTimeout=100;
//        this.setServiceInfo(info);

String packagename="com.whatsapp";
        //Setting up types of the event tha this service wants to listen to others won't be passed to this services
        System.out.println("onService Connected");
        Toast.makeText(this, "this package name is "+this.getPackageName(), Toast.LENGTH_SHORT).show();
        AccessibilityServiceInfo info=new AccessibilityServiceInfo();
        info.eventTypes=AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.eventTypes=AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType=AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.notificationTimeout=1000;
        info.packageNames=new String[]{"com.whatsapp","com.instagram.android"};
        setServiceInfo(info);




        // Create an overlay and display the action bar
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayout = new FrameLayout(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.action_bar, mLayout);
        wm.addView(mLayout, lp);
        configurePowerButton();
        configureVolumeButton();
        configureScrollButton();
        configureSwipeButton();
    }
    public void printEverything(AccessibilityNodeInfo node, int depth) {
        if (node == null) return;
        String print = "";
        for (int i = 0; i < depth; i++) print += "\t";
        print += "name:" + node.getViewIdResourceName();

        print += " ";
        print += "text:" + node.getText();
        if(print!="name:null text:null") {
            Log.i("TESTREQ", print);
        }
        for (int j = 0; j < node.getChildCount(); j++) {
            printEverything(node.getChild(j), depth + 1);
        }
    }
    private void configurePowerButton() {
        Button powerButton = (Button) mLayout.findViewById(R.id.power);
        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performGlobalAction(GLOBAL_ACTION_POWER_DIALOG);
            }
        });
    }
    private void configureVolumeButton() {
        Button volumeUpButton = (Button) mLayout.findViewById(R.id.volume_up);
        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
            }
        });
    }
    private AccessibilityNodeInfo findScrollableNode(AccessibilityNodeInfo root) {
        Deque<AccessibilityNodeInfo> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            AccessibilityNodeInfo node = deque.removeFirst();
            if (node.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)) {
                return node;
            }
            for (int i = 0; i < node.getChildCount(); i++) {
                deque.addLast(node.getChild(i));
            }
        }
        return null;
    }
    private void configureScrollButton() {
        Button scrollButton = (Button) mLayout.findViewById(R.id.scroll);
        scrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccessibilityNodeInfo scrollable = findScrollableNode(getRootInActiveWindow());
                if (scrollable != null) {
                    scrollable.performAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId());
                }
            }
        });
    }
    private void configureSwipeButton() {
        Button swipeButton = (Button) mLayout.findViewById(R.id.swipe);
        swipeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Path swipePath = new Path();
                swipePath.moveTo(1000, 1000);
                swipePath.lineTo(100, 1000);
                GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
                gestureBuilder.addStroke(new GestureDescription.StrokeDescription(swipePath, 0, 500));
                dispatchGesture(gestureBuilder.build(), null, null);
            }
        });
    }
}
