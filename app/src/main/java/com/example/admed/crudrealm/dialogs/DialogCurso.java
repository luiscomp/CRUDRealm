package com.example.admed.crudrealm.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.admed.crudrealm.R;
import com.example.admed.crudrealm.vo.CursoVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by Gabriel Ângelo on 07/10/2017.
 */

public class DialogCurso extends DialogFragment {

    @BindView(R.id.edtNomeCurso) protected EditText edtNomeCurso;
    @BindView(R.id.edtNomeProfessor) protected EditText edtNomeProfessor;

    private CursoVO curso;

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

        if(edtNomeCurso.getText().length() == 0 || edtNomeProfessor.getText().length() == 0) {
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
