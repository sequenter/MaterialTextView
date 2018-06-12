package uk.co.onemandan.materialtextviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.co.onemandan.materialtextview.MaterialTextView;

public class Sample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        MaterialTextView materialTextView = findViewById(R.id.mtv_sample);

        materialTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sample.this, "MaterialTextView Clicked",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
