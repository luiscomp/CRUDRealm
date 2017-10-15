package com.example.admed.crudrealm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.admed.crudrealm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @OnClick(R.id.btnAlunos) protected void abrirCrudAlunos() {
        Intent intent = new Intent(MainActivity.this, CrudAlunosActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btnProfessores) protected void abrirCrudProfessores() {
        Intent intent = new Intent(MainActivity.this, CrudProfessoresActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnCursos) protected void abrirCridCursos() {
        Intent intent = new Intent(MainActivity.this, CrudCursosActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }
}
