package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.sch.smktelkom_mlg.project.xirpl101101928.mindus.model.Mind;

public class MindDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mind_activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Mind mind = (Mind) getIntent().getSerializableExtra(MindMainActivity.HOTEL);
        setTitle(mind.task);
        ImageView ivFoto = (ImageView) findViewById(R.id.imageFoto);
        ivFoto.setImageURI(Uri.parse(mind.foto));
        TextView tvTask = (TextView) findViewById(R.id.place_task);
        tvTask.setText(mind.deskripsi + "\n\n" + mind.detail);
        TextView tvDeskripsi = (TextView) findViewById(R.id.place_desc);
        tvDeskripsi.setText(mind.deskripsi);
        TextView tvDue = (TextView) findViewById(R.id.place_due);
        tvDue.setText(mind.due);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}