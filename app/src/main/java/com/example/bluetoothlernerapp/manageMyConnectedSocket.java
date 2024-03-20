package com.example.bluetoothlernerapp;

import android.bluetooth.BluetoothSocket;

public class manageMyConnectedSocket {

    private void manageMyConnectedSocket(BluetoothSocket socket) {
        connectedThread = new ConnectedThread(socket);
        connectedThread.start();

    }

}