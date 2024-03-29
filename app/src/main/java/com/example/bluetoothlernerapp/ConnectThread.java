package com.example.bluetoothlernerapp;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;


import android.util.Log;


import java.io.IOException;
import java.util.UUID;

class ConnectThread extends Thread {
    BluetoothAdapter mBlueAdapter;
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private static final String TAG = "MyActivity";

    @SuppressLint("MissingPermission")
    public ConnectThread(BluetoothDevice device) throws IOException {
        // Use a temporary object that is later assigned to mmSocket
        // because mmSocket is final.
        BluetoothSocket tmp = null;
        mmDevice = device;


        try {
            // Get a BluetoothSocket to connect with the given BluetoothDevice.
            // MY_UUID is the app's UUID string, also used in the server code.
            //  tmp = device.createRfcommSocketToServiceRecord(0000111e-0000-1000-8000-00805f9b34fb);

            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                 //       0000111e-0000-1000-8000-00805f9b34fb                 //00001101-0000-1000-8000-00805F9B34FB
// google pixel air buds uuid//
        } catch (IOException e) {
            Log.e(TAG, "Socket's create() method failed", e);
        }
        mmSocket = tmp;
        Log.i(TAG, "Socket's created");
//        if (mmSocket != null) {
//       //     mmSocket.connect();


        }

    @SuppressLint("MissingPermission")


    public void run() {
        // Cancel discovery because it otherwise slows down the connection.
       Log.d(TAG,"EatShit");
     //   mBlueAdapter.cancelDiscovery();
        Log.d(TAG,"EatMoreShit");
        try {
            Log.d(TAG,"Eat3MoreShit");
            // Connect to the remote device through the socket. This call blocks
            // until it succeeds or throws an exception.
            mmSocket.connect();
            MyBluetoothService.ConnectedThread connectedThread = new MyBluetoothService().new ConnectedThread(mmSocket);
        } catch (IOException connectException) {
            // Unable to connect; close the socket and return.
            Log.d(TAG, "Could not connect client socket");
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                Log.e(TAG, "Could not close the client socket", closeException);
            }

            Log.d(TAG,"EAT4 SHit");
          //  return;
        }

        // The connection attempt succeeded. Perform work associated with
        // the connection in a separate thread.
        //  MyBluetoothService.ConnectedThread connectedThread = new MyBluetoothService. new MyBluetoothService.ConnectedThread(mmSocket);


        //MyBluetoothService.ConnectedThread connectedThread = new MyBluetoothService().new ConnectedThread(mmSocket);
       // MyBluetoothService.ConnectedThread.start();

    }


    // Closes the client socket and causes the thread to finish.
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the client socket", e);
        }
    }
}