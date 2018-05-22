package com.jones.ojaswikumar.homesec;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class PinCheck extends AppCompatActivity {

    Button btnOpen, btnDis;
    SeekBar brightness;
    TextView lumn;
    String address = null;
    EditText p1, p2, p3, p4;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    private String pin;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    int counter;
    volatile boolean stopWorker;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_check);

        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS); //receive the address of the bluetooth device

        //view of the activity
        setContentView(R.layout.activity_pin_check);

        //call the widgtes
        btnDis = (Button) findViewById(R.id.button2);
        btnOpen = (Button) findViewById(R.id.button1);
        p1 = (EditText) findViewById(R.id.pin1);
        p2 = (EditText) findViewById(R.id.pin2);
        p3 = (EditText) findViewById(R.id.pin3);
        p4 = (EditText) findViewById(R.id.pin4);

        p1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                try {
                    if (i != KeyEvent.KEYCODE_DEL) {
                        Selection.setSelection((Editable) p2.getText(), p1.getSelectionStart());
                        p2.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        p2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                try {
                    if (i != KeyEvent.KEYCODE_DEL) {
                        Selection.setSelection((Editable) p3.getText(), p2.getSelectionStart());
                        p3.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        p3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                try {
                    if (i != KeyEvent.KEYCODE_DEL) {
                        Selection.setSelection((Editable) p4.getText(), p3.getSelectionStart());
                        p4.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        p4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                try {
                    if (i == KeyEvent.ACTION_DOWN) {
                        Selection.setSelection((Editable) p4.getText(), p4.getSelectionStart());
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        p4.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });



    }

}
