package uk.co.onemandan.materialtextviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.co.onemandan.materialtextview.MaterialTextView;

public class Sample extends AppCompatActivity {

    private boolean _error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        final MaterialTextView materialTextView = findViewById(R.id.mtv_sample);

        materialTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _error = !_error;

                if(_error){
                    materialTextView.setError("Error!");
                } else {
                    materialTextView.setError(null);
                }
            }
        });
    }
}
