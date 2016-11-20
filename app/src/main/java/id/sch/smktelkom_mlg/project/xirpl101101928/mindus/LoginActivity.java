package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @InjectView(R.id.et_email)
    EditText et_email;
    @InjectView(R.id.et_password)
    EditText et_password;
    @InjectView(R.id.btn_login)
    Button btn_login;
    @InjectView(R.id.cv)
    CardView cv;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.inject(this);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn_login, R.id.fab})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.btn_login:
                Explode explode = new Explode();
                explode.setDuration(500);

                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2, oc2.toBundle());
                break;
        }
    }

}
