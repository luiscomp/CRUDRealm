package com.example.admed.crudrealm.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admed.crudrealm.R;
import com.example.admed.crudrealm.vo.CursoVO;
import com.example.admed.crudrealm.vo.ProfessorVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.widget.Toast.*;

/**
 * Created by Gabriel Ângelo on 07/10/2017.
 */

public class DialogCurso extends DialogFragment {

    @BindView(R.id.edtNomeCurso) protected EditText edtNomeCurso;

    private CursoVO curso;

    @BindView(R.id.spn_professor) protected Spinner spn_professor;

    @OnItemSelected(R.id.spn_professor) protected void selecionarProfessor(){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ProfessorVO> query = realm.where(ProfessorVO.class).findAll();

        List<String> labels = new ArrayList<>();
        for (ProfessorVO professor : query){
            labels.add(professor.getNome());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spn_professor.setAdapter(dataAdapter);
    }

    @OnClick(R.id.btnConcluir_dialogCurso) protected void concluir() {
        if(dadosValidos()) {
            Realm realm = null;

            if(curso == null) {
                curso = new CursoVO();
                curso.setId(CursoVO.autoIncrementId());
            } else{
                realm  = Realm.getDefaultInstance();
                realm.beginTransaction();
            }
            curso.setNome(edtNomeCurso.getText().toString());

            //curso.setId_professor(Long.parseLong(edtNomeProfessor.getText().toString()));

            if(realm != null) {
                realm.commitTransaction();
            }

            listener.aoConcluir(curso);
            dismiss();
        }
    }

    @OnClick(R.id.btnCancelar_dialogCurso) protected void cancelar() {
        dismiss();
    }

    private boolean dadosValidos() {
        boolean dadosInvalidos = false;

        if(edtNomeCurso.getText().length() == 0){ // || edtNomeProfessor.getText().length() == 0) {
            edtNomeCurso.setError("Preencha todos os campos");
            dadosInvalidos = true;
        }
        return !dadosInvalidos;
    }

    private DialogCurso.OnListener listener;

    public void setListener(DialogCurso.OnListener listener) {
        this.listener = listener;
    }

    public void setCurso(CursoVO curso) {
        this.curso = curso;
    }

    public interface OnListener {
        void aoConcluir(CursoVO curso);
    }

    public static DialogCurso newInstance(DialogCurso.OnListener listener) {
        DialogCurso dialog = new DialogCurso();
        dialog.setListener(listener);
        return dialog;
    }

    public static DialogCurso newInstance(CursoVO curso, DialogCurso.OnListener listener) {
        DialogCurso dialog = new DialogCurso();
        dialog.setListener(listener);
        dialog.setCurso(curso);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_curso, null);

        ButterKnife.bind(this, rootView);

        builder.setView(rootView);

        if(curso != null) {
            edtNomeCurso.setText(curso.getNome());
        }

        return builder.create();
    }
}
