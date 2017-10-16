package com.example.admed.crudrealm.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admed.crudrealm.R;

public class DetalhesCursoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_curso);

        final TextView tv_detalhes_nome_curso = (TextView) findViewById(R.id.tv_detalhes_nome_curso);
        final TextView tv_detalhes_nome_professor = (TextView)findViewById(R.id.tv_detalhes_nome_professor);

        Intent i = getIntent();

        String nome_curso = i.getStringExtra("nome");
        String nome_professor = i.getStringExtra("professor");

        tv_detalhes_nome_curso.setText(nome_curso);
        tv_detalhes_nome_professor.setText(nome_professor);
    }
}
