package com.example.bluetoothlernerapp;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class MyBluetoothService {

    private static final String TAG = "MY_APP_DEBUG_TAG";
    private Handler handler; // handler that gets info from Bluetooth service

    // Defines several constants used when transmitting messages between the
    // service and the UI.
    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;

        // ... (Add other message types here as needed.)
    }

    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream
         private final Handler uiHandler;
        //   private void manageMyConnectedSocket (BluetoothSocket socket){
        public ConnectedThread(BluetoothSocket socket, Handler handler) {
            // public void manageMyConnectedSocket ;
            mmSocket = socket;
          uiHandler = handler;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            //  private void manageMyConnectedSocket (BluetoothSocket socket){
            // Get the input and output streams; using temp objects because
            // member streams are final.

            Log.d(TAG, "Connected1");
            //  public void manageMyConnectedSocket() {
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating input stream", e);
            }
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating output stream", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
            Log.d(TAG, "Connected2");

            //   }
            //}    }
            //  Log.d(TAG,"Connected3");

        }

        public void run () {
        //    Handler handler = new Handler();

            Log.d(TAG,"Connected 2.5");
                Log.d(TAG, "Connected3");
                mmBuffer = new byte[1024];

                int numBytes; // bytes returned from read()
       //       ====================================================






                // Keep listening to the InputStream until an exception occurs.
                while (true) {
                    try {
                        // Read from the InputStream.
                        numBytes = mmInStream.read(mmBuffer);
                        Log.d(TAG, "mmBuffer content: " + Arrays.toString(mmBuffer));
                        // Send the obtained bytes to the UI activity.
                        Message readMsg = uiHandler.obtainMessage(
                                MessageConstants.MESSAGE_READ, numBytes, -1,
                                mmBuffer);
                        Log.d(TAG, "readMsg: " + readMsg);
                        Log.d(TAG, "Connected4");
                       
                       readMsg.sendToTarget();
                    } catch (IOException e) {
                        Log.d(TAG, "Input stream was disconnected", e);
                        break;
                    }
                }
            }
//Going to hold off on write message for now
            // do read only
//        // Call this from the main activity to send data to the remote device.
//        public void write(byte[] bytes) {
//            try {
//                mmOutStream.write(bytes);
//
//                // Share the sent message with the UI activity.
//                Message writtenMsg = handler.obtainMessage(
//                        MessageConstants.MESSAGE_WRITE, -1, -1, bytes);
//                writtenMsg.sendToTarget();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when sending data", e);
//
//                // Send a failure message back to the activity.
//                Message writeErrorMsg =
//                        handler.obtainMessage(MessageConstants.MESSAGE_TOAST);
//                Bundle bundle = new Bundle();
//                bundle.putString("toast",
//                        "Couldn't send data to the other device");
//                writeErrorMsg.setData(bundle);
//                handler.sendMessage(writeErrorMsg);
//            }
//        }

            // Call this method from the main activity to shut down the connection.
            public void cancel () {
                try {
                    mmSocket.close();
                } catch (IOException e) {
                    Log.e(TAG, "Could not close the connect socket", e);
                }
            }
        }
    }



