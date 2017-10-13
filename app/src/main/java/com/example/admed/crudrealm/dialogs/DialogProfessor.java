package com.example.admed.crudrealm.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.admed.crudrealm.R;
import com.example.admed.crudrealm.vo.ProfessorVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by Usuario on 29/09/2017.
 */

public class DialogProfessor extends DialogFragment {
    @BindView(R.id.edtNome) protected EditText edtNome;
    private ProfessorVO professor;

    @OnClick(R.id.btnConcluir) protected void concluir() {
        if(dadosValidos()) {
            Realm realm = null;

            if(professor == null) {
                professor = new ProfessorVO();
                professor.setId(ProfessorVO.autoIncrementId());
            } else {
                realm  = Realm.getDefaultInstance();
                realm.beginTransaction();
            }

            professor.setNome(edtNome.getText().toString());

            if(realm != null) {
                realm.commitTransaction();
            }

            listener.aoConcluir(professor);
            dismiss();
        }
    }

    @OnClick(R.id.btnCancelar) protected void cancelar() {
        dismiss();
    }

    private boolean dadosValidos() {
        boolean dadosInvalidos = false;

        if(edtNome.getText().length() == 0) {
            edtNome.setError("Informe o nome");
            dadosInvalidos = true;
        }

        return !dadosInvalidos;
    }

    private OnListener listener;

    public void setListener(OnListener listener) {
        this.listener = listener;
    }

    public void setProfessor(ProfessorVO professor) {
        this.professor = professor;
    }

    public interface OnListener {
        void aoConcluir(ProfessorVO professor);
    }

    public static DialogProfessor newInstance(OnListener listener) {
        DialogProfessor dialog = new DialogProfessor();
        dialog.setListener(listener);
        return dialog;
    }

    public static DialogProfessor newInstance(ProfessorVO professor, OnListener listener) {
        DialogProfessor dialog = new DialogProfessor();
        dialog.setListener(listener);
        dialog.setProfessor(professor);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_professor, null);

        ButterKnife.bind(this, rootView);

        builder.setView(rootView);

        if(professor != null) {
            edtNome.setText(professor.getNome());
        }

        return builder.create();
    }
}