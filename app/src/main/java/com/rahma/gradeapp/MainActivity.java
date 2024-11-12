package com.rahma.gradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNIM, etNama, etPresensi, etTugas, etUTS, etUAS;
    RadioGroup rgSemester;
    Button btnHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNIM = findViewById(R.id.etNIM);
        etNama = findViewById(R.id.etNama);
        etPresensi = findViewById(R.id.etPresensi);
        etTugas = findViewById(R.id.etTugas);
        etUTS = findViewById(R.id.etUTS);
        etUAS = findViewById(R.id.etUAS);
        rgSemester = findViewById(R.id.rgSemester);
        btnHitung = findViewById(R.id.btnHitung);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(etNIM) || isEmpty(etNama) || isEmpty(etPresensi) ||
                        isEmpty(etTugas) || isEmpty(etUTS) || isEmpty(etUAS) || rgSemester.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Seluruh data wajib diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double presensi = Double.parseDouble(etPresensi.getText().toString());
                    double tugas = Double.parseDouble(etTugas.getText().toString());
                    double uts = Double.parseDouble(etUTS.getText().toString());
                    double uas = Double.parseDouble(etUAS.getText().toString());

                    if (!isValidScore(presensi) || !isValidScore(tugas) || !isValidScore(uts) || !isValidScore(uas)) {
                        Toast.makeText(MainActivity.this, "Nilai tidak boleh lebih kecil dari 10 dan tidak boleh lebih besar dari 100", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double nilaiAkhir = (presensi * 0.1) + (tugas * 0.2) + (uts * 0.3) + (uas * 0.4);
                    String grade = calculateGrade(nilaiAkhir);
                    RadioButton selectedSemester = findViewById(rgSemester.getCheckedRadioButtonId());

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("NIM", etNIM.getText().toString());
                    intent.putExtra("Nama", etNama.getText().toString());
                    intent.putExtra("Semester", selectedSemester.getText().toString());
                    intent.putExtra("NilaiAkhir", nilaiAkhir);
                    intent.putExtra("Grade", grade);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isEmpty(EditText et) {
        return et.getText().toString().trim().isEmpty();
    }

    private boolean isValidScore(double score) {
        return score >= 10 && score <= 100;
    }

    private String calculateGrade(double nilaiAkhir) {
        if (nilaiAkhir >= 85) return "A";
        else if (nilaiAkhir >= 70) return "B";
        else if (nilaiAkhir >= 60) return "C";
        else if (nilaiAkhir >= 50) return "D";
        else return "E";
    }
}
