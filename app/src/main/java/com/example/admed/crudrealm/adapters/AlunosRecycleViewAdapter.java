package com.example.admed.crudrealm.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admed.crudrealm.R;
import com.example.admed.crudrealm.dialogs.DialogAluno;
import com.example.admed.crudrealm.vo.AlunoVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by Luis Eduardo on 20/09/2017.
 */

public class AlunosRecycleViewAdapter extends RecyclerView.Adapter<AlunosRecycleViewAdapter.ViewHolder> {
    private FragmentManager fragmentManager;
    private RealmQuery<AlunoVO> lista;

    public AlunosRecycleViewAdapter(RealmQuery<AlunoVO> lista, FragmentManager fragmentManager) {
        this.lista = lista;
        this.fragmentManager = fragmentManager;
    }

    public void atualizarLista() {
        Realm realm = Realm.getDefaultInstance();
        lista = realm.where(AlunoVO.class);
        notifyItemRangeChanged(0, lista.findAll().size());
    }

    @Override
    public AlunosRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_alunos, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlunosRecycleViewAdapter.ViewHolder holder, final int position) {
        holder.txtNome.setText(lista.findAll().get(position).getNome());

        holder.rlRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        lista.findAll().deleteFromRealm(position);

                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, lista.findAll().size());
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogAluno dialog = DialogAluno.newInstance(lista.findAll().get(position), new DialogAluno.OnListener() {
                    @Override
                    public void aoConcluir(AlunoVO aluno) {
                        notifyItemChanged(position);
                    }
                });
                dialog.show(fragmentManager, "alunoDialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.findAll().size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNome) protected TextView txtNome;
        @BindView(R.id.rlRemover) protected RelativeLayout rlRemover;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
