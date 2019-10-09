package br.com.fiap.agendamento_fiap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.fiap.agendamento_fiap.R;
import br.com.fiap.agendamento_fiap.model.Material;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {

    private Context context;
    private List<Material> materials;

    public MaterialAdapter(Context context, List<Material> materials) {
        this.context = context;
        this.materials = materials;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(this.context)
                .inflate(R.layout.recyclerview_materials, viewGroup, false);
        return new MaterialViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        final Material material = this.materials.get(position);

        holder.id.setText(String.valueOf(material.getId()));
        holder.professor.setText(material.getProfessor());
        holder.material.setText(material.getMaterial());
        holder.sala.setText(material.getSala());
        holder.data.setText(material.getData());
        holder.periodo.setText(material.getPeriodo());

        holder.cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Solicitação para: " + material.getMaterial(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.materials.size();
    }

    public static class MaterialViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView id;
        TextView professor;
        TextView material;
        TextView sala;
        TextView data;
        TextView periodo;

        public MaterialViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
            id = itemView.findViewById(R.id.id);
            professor = itemView.findViewById(R.id.professor);
            material = itemView.findViewById(R.id.material);
            sala = itemView.findViewById(R.id.sala);
            data = itemView.findViewById(R.id.data);
            periodo = itemView.findViewById(R.id.periodo);

        }
    }
}
