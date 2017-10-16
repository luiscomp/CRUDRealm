package com.example.admed.crudrealm.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.admed.crudrealm.R;
import com.example.admed.crudrealm.adapters.CursosRecycleViewAdapter;
import com.example.admed.crudrealm.dialogs.DialogCurso;
import com.example.admed.crudrealm.vo.CursoVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class CrudCursosActivity extends AppCompatActivity {

    @BindView(R.id.rvCursos) protected RecyclerView rvCursos;

    @OnClick(R.id.fab_cursos) protected void exibirDialogAdicionarCursos() {
        DialogCurso dialog = DialogCurso.newInstance(new DialogCurso.OnListener() {
            @Override
            public void aoConcluir(CursoVO curso) {
                adicionarCurso(curso);
            }
        });
        dialog.show(getSupportFragmentManager(), "dialogCurso");
    }

    private void adicionarCurso(CursoVO vo) {
        Realm realm = Realm.getDefaultInstance();

        if(!realm.isInTransaction()){
            realm.beginTransaction();
        }
        realm.copyToRealm(vo);
        realm.commitTransaction();

        ((CursosRecycleViewAdapter) rvCursos.getAdapter()).atualizarLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_cursos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cursos);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instanciarLista();
    }

    private void instanciarLista() {
        rvCursos.setHasFixedSize(true);
        rvCursos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvCursos.setAdapter(new CursosRecycleViewAdapter(null, getSupportFragmentManager(), this));
        ((CursosRecycleViewAdapter) rvCursos.getAdapter()).atualizarLista();
    }

}
