package com.example.locnavigator;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class ReceiveSMS extends BroadcastReceiver {


    private static MessageListner mListener;
    int count =0 ;
    final SmsManager sms = SmsManager.getDefault();


    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){


            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String from;
            if(bundle!=null){
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0;i<msgs.length;i++){
                        String body="";
                        for (int j = 0; j < pdus.length; j++) {
                            body += SmsMessage.createFromPdu((byte[])pdus[j]).getDisplayMessageBody();
                        }
                        body = body.substring(37);
                        Log.d("amessage", body);
//                        Toast.makeText(context,body,Toast.LENGTH_LONG).show();
                        JSONObject json = (JSONObject) new JSONTokener(body).nextValue();
                        String key = (String) json.get("Key");
                        Log.d("amessage", body);
                        if(key.equals("mai_hacker_hu")){
                            String durationn= (String)json.get("Duration");
                            String distance= (String)json.get("Distance");
                            String turn= (String)json.get("Turn");
                            String placeName= (String)json.get("Place_name");
                            String comp="";
                            float duration = Float.parseFloat(durationn);
                            duration = duration/60;
                            if(duration<1F)duration=1;

                            if(turn.equals("right"))
                            {
                                comp="Turn right"+" and then drive "+distance +" metres "+"for "+ duration +" minutes."+" You might be near"+ placeName;
                            }
                            if(turn.equals("left"))
                            {
                                comp="Turn left"+" and then drive "+distance +" metres "+"for "+duration+" minutes."+" You might be near"+ placeName;
                            }
                            if(turn.equals("straight"))
                            {
                                comp="Go straight"+" and drive "+distance +" metres "+"for "+duration+" minutes."+" You might be near"+ placeName;
                            }
                            if(turn.equals("slight left"))
                            {
                                comp="Take a slight  right"+" and then drive "+distance +" metres "+"for "+duration+" minutes."+" You might be near"+ placeName;
                            }
                            if(turn.equals("slight right"))
                            {
                                comp="Take a slight left"+" and then drive "+distance +" metres "+"for "+duration+" minutes."+" You might be near"+ placeName;
                            }
                            Log.d("sdsa", comp);
                            location l1 = new location(durationn,distance,turn,placeName,comp);
                            count++;
//                            Toast.makeText(context, "received : "+count, Toast.LENGTH_SHORT).show();
                            mListener.messageReceived(l1);
                        }else{

                            Log.d("Shivam","Key failed");
                        }

                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void bindListener(MessageListner listener){
        mListener = listener;
    }

}
