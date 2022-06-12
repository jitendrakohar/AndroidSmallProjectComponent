package com.example.messagerecord;



import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class MyAccessibilityService extends AccessibilityService {
private static final String TAG="Jitendra";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e(TAG, "onAccessibilityEvent: " );
        String packageName=event.getPackageName().toString();
        PackageManager packageManager=this.getPackageManager();
        try{
            ApplicationInfo applicationInfo=packageManager.getApplicationInfo(packageName,0);
            CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            Log.e(TAG, "onAccessibilityEvent: "+applicationLabel );
            Toast.makeText(this, "Application running:"+applicationLabel, Toast.LENGTH_SHORT).show();

        }
        catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onInterrupt() {
        Log.e(TAG, "onInterrupt: something went wrong" );
    }


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
       AccessibilityServiceInfo info=new AccessibilityServiceInfo();





        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED |
                AccessibilityEvent.TYPE_VIEW_FOCUSED;

//        info.packageNames = new String[]
//                {"com.example.android.myFirstApp", "com.example.android.mySecondApp"};


        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;


        info.notificationTimeout = 100;

        this.setServiceInfo(info);
        Log.e(TAG, "onServiceConnected: onServiceConnected:" );


    }
}
