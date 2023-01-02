package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private ListView ContactsLv;
    private ContactsAdapter adapter;
    public static List<ContactsInfo> list = new ArrayList<ContactsInfo>();

    private Button btn;

    /*Author：HuangWei1824231127
      Date：2020.6.13
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        btn=findViewById(R.id.btnGetContacts);
        ContactsLv = (ListView) findViewById(R.id.ContactsLv);
        getContacts(ContactActivity.this);
        adapter = new ContactsAdapter(list, ContactActivity.this);
        ContactsLv.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContacts(ContactActivity.this);
                adapter = new ContactsAdapter(list, ContactActivity.this);
                ContactsLv.setAdapter(adapter);
            }
        });
        ContactsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView myphone=view.findViewById(R.id.contactNumber);
                String telphone=myphone.getText().toString();
                Intent intent=new Intent(ContactActivity.this,MainActivity.class);
                intent.putExtra("telphone",telphone);
                startActivity(intent);
            }
        });
    }

    public String getContacts(Context context){

        String contactstr=null;
        if (ContextCompat.checkSelfPermission(context,android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    1);
        }
        else {
            try {
                Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Cursor cursor = context.getContentResolver().query(contactUri,
                        new String[]{"display_name", "sort_key", "contact_id", "data1"},
                        null, null, "sort_key");

                String contactName;
                String contactNumber;
                String contactSortKey;
                int contactId;
                while (cursor.moveToNext()) {
                    contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    contactSortKey = getSortkey(cursor.getString(1));
                    ContactsInfo contactsInfo = new ContactsInfo(contactName, contactNumber, contactSortKey, contactId);
                    if (contactName != null)
                        list.add(contactsInfo);
                }
                cursor.close();//使用完后一定要将cursor关闭，不然会造成内存泄露等问题

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                context = null;
            }

        }
        return contactstr;
    }
    private static String getSortkey(String sortKeyString){
        String key =sortKeyString.substring(0,1).toUpperCase();
        if (key.matches("[A-Z]")){
            return key;
        }else
            return "#";   //获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
    }
    @Override
    public void onBackPressed() {   //清除缓存
        list.clear();
        super.onBackPressed();
    }
}
