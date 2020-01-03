package com.monesz.intentschallange;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCreateContact;
    ImageView ivFace, ivDial, ivOpenBrowser, ivLocation;

    final int CreateContactActivity = 1;

    String name = "", number = "", web = "", address = "", mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateContact = findViewById(R.id.btnCreateContact);
        ivFace = findViewById(R.id.ivFace);
        ivDial = findViewById(R.id.ivDial);
        ivOpenBrowser = findViewById(R.id.ivOpenBrowser);
        ivLocation = findViewById(R.id.ivLocation);

        ivFace.setVisibility(View.GONE);
        ivDial.setVisibility(View.GONE);
        ivOpenBrowser.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);


        btnCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,
                        com.monesz.intentschallange.CreateContactActivity.class);
                startActivityForResult(intent, CreateContactActivity);

            }
        });

        ivDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + number));
                startActivity(intent);
            }
        });

        ivOpenBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?=" + address));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CreateContactActivity) {

            if(resultCode == RESULT_OK){
                ivFace.setVisibility(View.VISIBLE);
                ivDial.setVisibility(View.VISIBLE);
                ivOpenBrowser.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);


                name = data.getStringExtra("name");
                number = data.getStringExtra("number");
                web = data.getStringExtra("web");
                address = data.getStringExtra("address");
                mood = data.getStringExtra("mood");

                if(mood.equals("happy")){
                    ivFace.setImageResource(R.drawable.happy_face);
                }
                else if (mood.equals("sad")) {
                    ivFace.setImageResource(R.drawable.sad_face);
                }else {
                    ivFace.setImageResource(R.drawable.neutral_face);
                }
            }
            else {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
