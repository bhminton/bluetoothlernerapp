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
    private final BluetoothDevice mmDevice ;
    private static final String TAG = "MyActivity";

    @SuppressLint("MissingPermission")
    public ConnectThread(BluetoothDevice device) {
        // Use a temporary object that is later assigned to mmSocket
        // because mmSocket is final.
        BluetoothSocket tmp = null;
        mmDevice = device;



        try {
            // Get a BluetoothSocket to connect with the given BluetoothDevice.
            // MY_UUID is the app's UUID string, also used in the server code.
          //  tmp = device.createRfcommSocketToServiceRecord(0000111e-0000-1000-8000-00805f9b34fb);

            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("0000111e-0000-1000-8000-00805f9b34fb"));
// google pixel air buds uuid//
        } catch (IOException e) {
            Log.e(TAG, "Socket's create() method failed", e);
        }
       mmSocket = tmp;
        Log.e(TAG, "Socket's created");
    }

    @SuppressLint("MissingPermission")
    public void run() {
        // Cancel discovery because it otherwise slows down the connection.
    mBlueAdapter.cancelDiscovery();

        try {
            // Connect to the remote device through the socket. This call blocks
            // until it succeeds or throws an exception.
            mmSocket.connect();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and return.
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                Log.e(TAG, "Could not close the client socket", closeException);
            }
            return;
        }

        // The connection attempt succeeded. Perform work associated with
        // the connection in a separate thread.
        ConnectedThread(mmSocket)= newConnectedThread
        BluetoothSocket mmSocket = new BluetoothSocket;
         manageMyConnectedSocket(mmSocket)= new manageMyConnectedSocket();
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