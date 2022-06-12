package com.example.recordingscreenmessage;

import static android.content.ContentValues.TAG;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

public class GlobalActionBarService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        AccessibilityNodeInfo mNodeInfo=event.getSource();
        printEveryThing(mNodeInfo,0);


    }

    private void printEveryThing(AccessibilityNodeInfo mNodeInfo, int depth) {
    if(mNodeInfo==null) return;
    String print="";
    for(int i=0;i<depth;i++) print +="\t";
    print+="name:"+mNodeInfo.getViewIdResourceName();
    print+=" ";
    print+="text:"+ mNodeInfo.getText();
        Log.i("screenText",print+"");
        for(int j=0;j< mNodeInfo.getChildCount();j++){
            printEveryThing(mNodeInfo.getChild(j),depth+1);
        }


    }


    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        Toast.makeText(this, "onServiceConnected", Toast.LENGTH_SHORT).show();

    }
}
