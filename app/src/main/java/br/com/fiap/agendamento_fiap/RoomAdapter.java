package br.com.fiap.agendamento_fiap;

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

import br.com.fiap.agendamento_fiap.model.Sala;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context context;
    private List<Sala> salas;

    public RoomAdapter(Context context, List<Sala> salas) {
        this.context = context;
        this.salas = salas;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(this.context)
                .inflate(R.layout.recyclerview_rooms, viewGroup, false);
        return new RoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        final Sala sala = this.salas.get(position);

        holder.id.setText(String.valueOf(sala.getId()));
        holder.professor.setText(sala.getProfessor());
        holder.sala.setText(sala.getSala());
        holder.data.setText(sala.getData());
        holder.periodo.setText(sala.getPeriodo());
        holder.tipo.setText(sala.getTipo());
        holder.count.setText(sala.getQuantidade());

        holder.cardview.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Solicitação para : " + sala.getProfessor(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.salas.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView id;
        TextView professor;
        TextView sala;
        TextView data;
        TextView periodo;
        TextView tipo;
        TextView count;

        public RoomViewHolder(View itemView) {
            super(itemView);

            cardview = (CardView) itemView;
            id = itemView.findViewById(R.id.id);
            professor = itemView.findViewById(R.id.professor);
            sala = itemView.findViewById(R.id.sala);
            data = itemView.findViewById(R.id.data);
            periodo = itemView.findViewById(R.id.periodo);
            tipo = itemView.findViewById(R.id.tipo);
            count = itemView.findViewById(R.id.count);

        }

    }
}
