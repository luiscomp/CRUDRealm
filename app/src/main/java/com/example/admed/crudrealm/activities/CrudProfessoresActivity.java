package com.example.admed.crudrealm.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.admed.crudrealm.R;
import com.example.admed.crudrealm.adapters.ProfessoresRecycleViewAdapter;
import com.example.admed.crudrealm.dialogs.DialogProfessor;
import com.example.admed.crudrealm.vo.ProfessorVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class CrudProfessoresActivity extends AppCompatActivity {
    @BindView(R.id.rvProfessores) protected RecyclerView rvProfessores;

    @OnClick(R.id.fab) protected void exibirDialogAdicionarProfessor() {
        DialogProfessor dialog = DialogProfessor.newInstance(new DialogProfessor.OnListener() {

            @Override
            public void aoConcluir(ProfessorVO professor) {
                adicionarProfessor(professor);
            }

        });
        dialog.show(getSupportFragmentManager(), "dialogProfessor");
    }

    private void adicionarProfessor(ProfessorVO vo) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(vo);
        realm.commitTransaction();

        ((ProfessoresRecycleViewAdapter) rvProfessores.getAdapter()).atualizarLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_professores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instanciarLista();
    }

    private void instanciarLista() {
        rvProfessores.setHasFixedSize(true);
        rvProfessores.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvProfessores.setAdapter(new ProfessoresRecycleViewAdapter(null, getSupportFragmentManager()));
        ((ProfessoresRecycleViewAdapter) rvProfessores.getAdapter()).atualizarLista();
    }
}