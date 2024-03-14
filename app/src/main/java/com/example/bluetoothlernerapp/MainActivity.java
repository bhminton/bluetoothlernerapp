package com.example.bluetoothlernerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BLUETOOTH = 0;
    private static final int REQUEST_DISCOVER_BLUETOOTH = 1;
    private static final int BLUETOOTH_PERMISSION_REQUEST_CODE = 2;// maybe same as above


    TextView mPairedTv;

    Button mOnBtn, mOffBtn, mDiscoverBtn, mPairedBtn, mGattBtn;

    BluetoothAdapter mBlueAdapter;
    BluetoothGattCallback bluetoothGattCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPairedTv = findViewById(R.id.pairedTv);

        mOnBtn = findViewById(R.id.onBtn);
        mOffBtn = findViewById(R.id.offBtn);
        mDiscoverBtn = findViewById(R.id.discoverableBtn);
        mPairedBtn = findViewById(R.id.pairedBtn);

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        mPairedBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                @SuppressLint("MissingPermission") Set<BluetoothDevice> pairedDevices =  mBlueAdapter.getBondedDevices();

                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                    for (BluetoothDevice device : pairedDevices) {
                        @SuppressLint("MissingPermission")
                        String deviceName = device.getName();
                        String deviceHardwareAddress = device.getAddress(); // MAC address
                        mPairedTv.append("\nDevice" + deviceName + deviceHardwareAddress + ","  + device);
                    }
                }

            }
        });

    }
    //==============================================================================================
//    mPairedBtn..setOnCl(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (mBlueAdapter.isEnabled()) {
//                mPairedTv.setText("Paired devices ");
//
//
//                Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
//                for (BluetoothDevice device: devices) {
//                    mPairedTv.append("\nDevice" + device.getName() + ","  + device);
//                    System.out.println(device);
//                    System.out.println(device);
//                }
//
//            }
//            else {
//
//                //bluetooth is not connected and cant get info
//                toastMsg("Turn on bletooth if you want shared devices ");
//            }
//
//        }
//    });
    ///===================================================================


}