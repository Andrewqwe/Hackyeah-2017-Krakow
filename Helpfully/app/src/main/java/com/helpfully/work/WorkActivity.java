package com.helpfully.work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.helpfully.Database;
import com.helpfully.R;

public class WorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        final EditText titleInput = findViewById(R.id.titleInput);
        final EditText descriptonInput = findViewById(R.id.descriptonInput);
        final EditText paymentInput = findViewById(R.id.paymentInput);
        Button sendBtn = findViewById(R.id.SendButton);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleInput.length() != 0 && descriptonInput.length() != 0 && paymentInput.length() != 0) {
                    System.out.println(titleInput.getText().toString());
                    System.out.println(descriptonInput.getText().toString());
                    System.out.println(Integer.parseInt(paymentInput.getText().toString()));
                    Work work = new Work(titleInput.getText().toString(), descriptonInput.getText().toString(), Integer.parseInt(paymentInput.getText().toString()));
                    System.out.println(work);
                    Database.sendWorkToDatabase(work);
                }
            }
        });
    }
}
