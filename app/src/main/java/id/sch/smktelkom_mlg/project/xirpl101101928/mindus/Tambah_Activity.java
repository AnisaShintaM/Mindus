package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Tambah_Activity extends AppCompatActivity {

    EditText etSummary;
    EditText etDescription;
    EditText etTanggal;
    ImageButton ibFoto;
    Button bTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.buttonSimpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tambah_Activity.this, AlarmActivity.class));
            }
        });


        etSummary = (EditText) findViewById(R.id.editTextSummary);
        etDescription = (EditText) findViewById(R.id.editTextDeskripsi);
        etTanggal = (EditText) findViewById(R.id.editTextTanggal);
        ibFoto = (ImageButton) findViewById(R.id.imageViewFoto);
        bTambah = (Button) findViewById(R.id.buttonSimpan);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
