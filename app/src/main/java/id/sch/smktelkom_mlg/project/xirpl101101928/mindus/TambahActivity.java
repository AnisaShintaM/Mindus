package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import id.sch.smktelkom_mlg.project.xirpl101101928.mindus.model.Mind;




public class TambahActivity extends AppCompatActivity
{
    static final int REQUEST_IMAGE_GET = 1;
    EditText etSum;
    EditText etDeskripsi;
    EditText etDetail;
    EditText etDue;
    ImageView ivFoto;
    Uri uriFoto;
    Mind mind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etSum = (EditText) findViewById(R.id.editTextSummary);
        etDeskripsi = (EditText) findViewById(R.id.editTextDeskripsi);
        etDue = (EditText) findViewById(R.id.editTextDue);
        etDetail = (EditText) findViewById(R.id.editTextDetail);
        ivFoto = (ImageView) findViewById(R.id.imageViewFoto);

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhoto();
            }
        });

        findViewById(R.id.buttonAlarm).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(TambahActivity.this, AlarmActivity.class));
                    }
                });

        findViewById(R.id.buttonSimpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });

        mind = (Mind) getIntent().getSerializableExtra(MindMainActivity.MIND);
        if (mind != null) {
            setTitle("Edit " + mind.task);
            fillData();
        } else {
            setTitle("New Task");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillData() {
        etSum.setText(mind.task);
        etDeskripsi.setText(mind.deskripsi);
        etDetail.setText(mind.detail);
        etDue.setText(mind.due);
        uriFoto = Uri.parse(mind.foto);
        ivFoto.setImageURI(uriFoto);
    }

    private void doSave() {
        String sum = etSum.getText().toString();
        String deskripsi = etDeskripsi.getText().toString();
        String detail = etDetail.getText().toString();
        String due = etDue.getText().toString();

        if (isValid(sum, deskripsi, detail, due, uriFoto)) {
            mind = new Mind(sum, deskripsi, detail, due, uriFoto.toString());

            Intent intent = new Intent();
            intent.putExtra(MindMainActivity.MIND, mind);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    private boolean isValid(String judul, String deskripsi, String detail, String lokasi, Uri uriFoto) {
        boolean valid = true;
        if (judul.isEmpty()) {
            setErrorEmpty(etSum);
            valid = false;
        }
        if (deskripsi.isEmpty()) {
            setErrorEmpty(etDeskripsi);
            valid = false;
        }
        if (detail.isEmpty()) {
            setErrorEmpty(etDetail);
            valid = false;
        }
        if (lokasi.isEmpty()) {
            setErrorEmpty(etDue);
            valid = false;
        }
        if (uriFoto == null) {
            Snackbar.make(ivFoto, "Foto Belum Ada", Snackbar.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }

    private void setErrorEmpty(EditText editText) {
        editText.setError(((TextInputLayout) editText.getParent()).getHint() + "Belum Diisi");
    }

    private void pickPhoto() {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, REQUEST_IMAGE_GET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            uriFoto = data.getData();
            ivFoto.setImageURI(uriFoto);
        }
    }
}
