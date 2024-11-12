package com.rahma.gradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvNIM_2312500735, tvNama_2312500735, tvSemester_2312500735, tvNilaiAkhir_2312500735, tvGrade_2312500735;
    Button btnBack_2312500735;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvNIM_2312500735 = findViewById(R.id.tvNIM_2312500735);
        tvNama_2312500735 = findViewById(R.id.tvNama_2312500735);
        tvSemester_2312500735 = findViewById(R.id.tvSemester_2312500735);
        tvNilaiAkhir_2312500735 = findViewById(R.id.tvNilaiAkhir_2312500735);
        tvGrade_2312500735 = findViewById(R.id.tvGrade_2312500735);
        btnBack_2312500735 = findViewById(R.id.btnBack_2312500735);

        Intent intent = getIntent();
        String nim = intent.getStringExtra("NIM");
        String nama = intent.getStringExtra("Nama");
        String semester = intent.getStringExtra("Semester");
        double nilaiAkhir = intent.getDoubleExtra("NilaiAkhir", 0);
        String grade = intent.getStringExtra("Grade");

        tvNIM_2312500735.setText(nim);
        tvNama_2312500735.setText(nama);
        tvSemester_2312500735.setText(semester);
        tvNilaiAkhir_2312500735.setText(String.format("%.2f", nilaiAkhir));
        tvGrade_2312500735.setText(grade);

        btnBack_2312500735.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
