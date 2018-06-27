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

        final MaterialTextView materialTextView = findViewById(R.id.mtv_sample);
        final MaterialTextView materialTextView2 = findViewById(R.id.mtv_sample2);

        materialTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!materialTextView.getError()){
                    materialTextView.setError("Error!");
                } else {
                    materialTextView.setError(null);
                }
            }
        });

        materialTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!materialTextView2.getError()){
                    materialTextView2.setError("");
                } else {
                    materialTextView2.setError(null);
                }
            }
        });
    }
}
