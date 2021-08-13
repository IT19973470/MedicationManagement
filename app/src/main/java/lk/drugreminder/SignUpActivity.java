package lk.drugreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.medi_blue));
        getWindow().setStatusBarColor(getResources().getColor(R.color.medi_blue));

    }
}
