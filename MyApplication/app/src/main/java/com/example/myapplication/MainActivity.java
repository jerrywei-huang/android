package com.example.myapplication;

import android.Manifest;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText telnum;
    private EditText content;
    private Button sendbtn;
    RxPermissions mRxPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        initView();
    }

    private void initView() {
        telnum = (EditText) findViewById(R.id.telnum);
        content = (EditText) findViewById(R.id.content);
        sendbtn = (Button) findViewById(R.id.sendbtn);

        sendbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendbtn:
                PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this, 0, new Intent(), 0);
                SmsManager smsManagfer=SmsManager.getDefault();
                List<String> smsList=smsManagfer.divideMessage(content.getText().toString());
                for(String message:smsList){
                    smsManagfer.sendTextMessage(telnum.getText().toString(), null, message, pi, null);
                }

                break;
        }
    }

    private void submit() {
        // validate
        String telnumString = telnum.getText().toString().trim();
        if (TextUtils.isEmpty(telnumString)) {
            Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
            return;
        }

        String contentString = content.getText().toString().trim();
        if (TextUtils.isEmpty(contentString)) {
            Toast.makeText(this, "???????????????", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    private void requestPermissions() {
        mRxPermissions = new RxPermissions(MainActivity.this);

        mRxPermissions.requestEach(Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // ???????????????????????????
                            Log.d("text", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // ???????????????????????????????????????
                            showPermissions();
                        } else {
                            // ???????????????????????????????????????
                            showPermissions();
                        }
                    }
                });
    }

    public void showPermissions() {
        if (!mRxPermissions.isGranted(Manifest.permission.SEND_SMS)) {
            showPermissionsDialog("?????????????????????????????????,????????????????????????????????????");
        } else if (!mRxPermissions.isGranted(Manifest.permission.READ_SMS)){
            showPermissionsDialog("?????????????????????????????????,????????????????????????????????????");
        }
    }

    public void showPermissionsDialog(String msg) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("?????????")
                .setMessage(msg)
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.fromParts("package", getPackageName(), null));
                        startActivity(intent);
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
            }
        }).show();
    }


}