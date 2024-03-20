package com.example.bluetoothlernerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import android.content.Intent;
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

    Button mOnBtn, mOffBtn, mDiscoverBtn, mPairedBtn, mConnectBtn, mGattBtn;

    BluetoothAdapter mBlueAdapter;
    BluetoothGattCallback bluetoothGattCallback;
     BluetoothDevice mmDevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPairedTv = findViewById(R.id.pairedTv);

        mOnBtn = findViewById(R.id.onBtn);
        mOffBtn = findViewById(R.id.offBtn);
        mDiscoverBtn = findViewById(R.id.discoverableBtn);
        mPairedBtn = findViewById(R.id.pairedBtn);
        mConnectBtn = findViewById(R.id.connectBtn);

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        mPairedBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                @SuppressLint("MissingPermission") Set<BluetoothDevice> pairedDevices = mBlueAdapter.getBondedDevices();

                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                    for (BluetoothDevice device : pairedDevices) {
                        @SuppressLint("MissingPermission")
                        String deviceName = device.getName();
                        String deviceHardwareAddress = device.getAddress(); // MAC address
                        mPairedTv.append("\nDevice" + deviceName + deviceHardwareAddress + "," + device);
                    }
                }

            }
        });
        mConnectBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                @SuppressLint("MissingPermission")
               // String deviceName = null;




                    BluetoothAdapter mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

                    if (mBlueAdapter == null) {
                        // Device doesn't support Bluetooth
                        return;
                    }

                    // Check if Bluetooth is enabled
                    if (!mBlueAdapter.isEnabled()) {
                        // Bluetooth is not enabled, prompt the user to enable it
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivity(enableBtIntent);
                        return;
                    }

                    // Get a list of paired devices
                    Set<BluetoothDevice> pairedDevices = mBlueAdapter.getBondedDevices();

                    // Iterate over the list of paired devices and choose the device you want to connect to
                    for (BluetoothDevice pairedDevice : pairedDevices) {
                        // Choose your device based on its MAC address
                        if (pairedDevice.getAddress().equals("74:74:46:F1:95:53")) {
                            // Create an instance of ConnectThread
                          mmDevice = pairedDevice;
                          //  String deviceMACAddress = "74:74:46:F1:95:53";
                         //   ConnectThread connectThread = new ConnectThread(mmDevice);

                            // Start the thread
                           // connectThread.start();
                            return; // Exit the method after starting the thread
                        }
                    }
                ConnectThread connectThread = new ConnectThread(mmDevice);
                connectThread.start();
                    // If the device is not found in the list of paired devices, you might want to initiate a discovery process
                    // to find nearby devices and connect to the desired one.
                    // Refer to the Android Bluetooth documentation for implementing Bluetooth device discovery.
                }
















//                if (deviceName == "bryan's Pixel Buds A-Series)"){
//                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                 //   @SuppressLint("MissingPermission")
//                    String deviceName;
//                    for (BluetoothDevice device : pairedDevices) {
//                        deviceName = device.getName();
//                        String deviceHardwareAddress = device.getAddress();
//
//                        // if (deviceName="bryan's Pixel Buds A-Series)"){


                });
            }
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


