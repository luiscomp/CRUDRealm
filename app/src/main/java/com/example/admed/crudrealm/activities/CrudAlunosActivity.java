package com.example.admed.crudrealm.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.admed.crudrealm.R;
import com.example.admed.crudrealm.adapters.AlunosRecycleViewAdapter;
import com.example.admed.crudrealm.dialogs.DialogAluno;
import com.example.admed.crudrealm.vo.AlunoVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class CrudAlunosActivity extends AppCompatActivity {
    @BindView(R.id.rvAlunos) protected RecyclerView rvAlunos;

    @OnClick(R.id.fab) protected void exibirDialogAdicionarAluno() {
        DialogAluno dialog = DialogAluno.newInstance(new DialogAluno.OnListener() {

            @Override
            public void aoConcluir(AlunoVO aluno) {
                adicionarAluno(aluno);
            }

        });
        dialog.show(getSupportFragmentManager(), "dialogAluno");
    }

    private void adicionarAluno(AlunoVO vo) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(vo);
        realm.commitTransaction();

        ((AlunosRecycleViewAdapter) rvAlunos.getAdapter()).atualizarLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_alunos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instanciarLista();
    }

    private void instanciarLista() {
        rvAlunos.setHasFixedSize(true);
        rvAlunos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvAlunos.setAdapter(new AlunosRecycleViewAdapter(null, getSupportFragmentManager()));
        ((AlunosRecycleViewAdapter) rvAlunos.getAdapter()).atualizarLista();
    }
}
