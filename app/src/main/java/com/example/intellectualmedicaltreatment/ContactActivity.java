package com.example.intellectualmedicaltreatment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.contact);
        initButton();
    }

    private void initButton() {
        button = (Button) this.findViewById(R.id.button4);
        button.setText("发送我的位置");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
