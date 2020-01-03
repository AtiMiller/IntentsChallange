package com.monesz.intentschallange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContactActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etNumber, etWeb, etAddress;
    ImageView ivPicHappy, ivPicNeutral, ivPicSad;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etWeb = findViewById(R.id.etWeb);
        etAddress = findViewById(R.id.etAddress);

        ivPicHappy = findViewById(R.id.ivPicHappy);
        ivPicNeutral = findViewById(R.id.ivPicNeutral);
        ivPicSad = findViewById(R.id.ivPicSad);

        ivPicHappy.setOnClickListener(this);
        ivPicNeutral.setOnClickListener(this);
        ivPicSad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(etName.getText().toString().isEmpty() ||etNumber.getText().toString().isEmpty() ||
                etWeb.getText().toString().isEmpty() || etAddress.getText().toString().isEmpty())
        {
            Toast.makeText(CreateContactActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
        }
        else
            {
            Intent intent = new Intent();
            intent.putExtra("name",etName.getText().toString().trim());
            intent.putExtra("number",etNumber.getText().toString().trim());
            intent.putExtra("web",etWeb.getText().toString().trim());
            intent.putExtra("address",etAddress.getText().toString().trim());

            if (view.getId() == R.id.ivPicHappy)
            {
                intent.putExtra("mood", "happy");
            }
            else if (view.getId() == R.id.ivPicNeutral)
            {
                intent.putExtra("mood", "ok");
            }
            else
            {
                intent.putExtra("mood", "sad");
            }

            setResult(RESULT_OK, intent);
            CreateContactActivity.this.finish();

        }

    }
}
