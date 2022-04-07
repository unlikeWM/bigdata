package com.example.broadcast_charge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private PowerConnectionReceiver powerConnectionReceiver;
    private BatteryLevelReceiver batteryLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        powerConnectionReceiver = new PowerConnectionReceiver();
        // 创建意图对象
        IntentFilter iFilter = new IntentFilter();
        // 添加电池改变的活动
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(powerConnectionReceiver, iFilter);

        batteryLevelReceiver = new BatteryLevelReceiver();

        registerReceiver(batteryLevelReceiver, iFilter);

    }


    class PowerConnectionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
            if (isCharging == true) {
                Log.d("正在充电", "正在充电");
                if (usbCharge == true)
                    Log.d("usb", "usb充电");
                else if(acCharge == true) Log.d("ac", "ac充电");
            }
        }
    }
    class BatteryLevelReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = level / (float)scale;
            if ( batteryPct >= 0.2&&batteryPct < 0.201){
                Log.d("离开低电量", "离开低电量："+batteryPct*100);
            }
            if (batteryPct<=0.2){
                Log.d("低电量", "低电量："+batteryPct*100);
            }
        }
    }
}