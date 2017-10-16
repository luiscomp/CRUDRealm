package com.example.admed.crudrealm.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admed.crudrealm.R;
import com.example.admed.crudrealm.activities.DetalhesCursoActivity;
import com.example.admed.crudrealm.activities.MainActivity;
import com.example.admed.crudrealm.dialogs.DialogCurso;
import com.example.admed.crudrealm.vo.CursoVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by Gabriel Ângelo on 04/10/2017.
 */

public class CursosRecycleViewAdapter extends RecyclerView.Adapter<CursosRecycleViewAdapter.ViewHolder>{

    private FragmentManager fragmentManager;
    private RealmQuery<CursoVO> lista;
    private Context context;

    public CursosRecycleViewAdapter(RealmQuery<CursoVO> lista, FragmentManager fragmentManager, Context context) {
        this.lista = lista;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    public void atualizarLista() {
        Realm realm = Realm.getDefaultInstance();
        lista = realm.where(CursoVO.class);
        notifyItemRangeChanged(0, lista.findAll().size());
    }

    @Override
    public CursosRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cursos, parent, false);
        return new CursosRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CursosRecycleViewAdapter.ViewHolder holder, final int position) {
        holder.tv_nome_curso.setText(lista.findAll().get(position).getNome());

        holder.tv_id_curso.setText(Long.toString(lista.findAll().get(position).getId()));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
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
                Intent i = new Intent(context.getApplicationContext(), DetalhesCursoActivity.class);
                i.putExtra("nome", lista.findAll().get(position).getNome());
                i.putExtra("professor", lista.findAll().get(position).getProfessorVO().getNome());
                //passar também a lista de alunos
                context.startActivity(i);
            }

        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCurso dialog = DialogCurso.newInstance(lista.findAll().get(position), new DialogCurso.OnListener() {
                    @Override
                    public void aoConcluir(CursoVO curso) {
                        notifyItemChanged(position);

                    }
                });
                dialog.show(fragmentManager, "cursoDialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.findAll().size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nome_curso) protected TextView tv_nome_curso;
        @BindView(R.id.tv_id_curso) protected TextView tv_id_curso;
        @BindView(R.id.btnDelete) protected ImageView btnDelete;
        @BindView(R.id.btnEdit) protected  ImageView btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
