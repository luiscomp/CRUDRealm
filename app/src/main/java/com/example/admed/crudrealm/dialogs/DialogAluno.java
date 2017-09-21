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
import com.example.admed.crudrealm.vo.AlunoVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luis Eduardo on 20/09/2017.
 */

public class DialogAluno extends DialogFragment {
    @BindView(R.id.edtNome) protected EditText edtNome;
    private AlunoVO aluno;

    @OnClick(R.id.btnConcluir) protected void concluir() {
        if(dadosValidos()) {
            if(aluno == null) {
                aluno = new AlunoVO();
                aluno.setId(AlunoVO.autoIncrementId());
            }
            aluno.setNome(edtNome.getText().toString());

            listener.aoConcluir(aluno);
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

    public void setAluno(AlunoVO aluno) {
        this.aluno = aluno;
    }

    public interface OnListener {
        void aoConcluir(AlunoVO aluno);
    }

    public static DialogAluno newInstance(OnListener listener) {
        DialogAluno dialog = new DialogAluno();
        dialog.setListener(listener);
        return dialog;
    }

    public static DialogAluno newInstance(AlunoVO aluno, OnListener listener) {
        DialogAluno dialog = new DialogAluno();
        dialog.setListener(listener);
        dialog.setAluno(aluno);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_aluno, null);

        ButterKnife.bind(this, rootView);

        builder.setView(rootView);
        return builder.create();
    }
}
