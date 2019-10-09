package br.com.fiap.agendamento_fiap.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.fiap.agendamento_fiap.R;
import br.com.fiap.agendamento_fiap.UpdateRequestRoom;
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
        holder.professor_room.setText(sala.getProfessor());
        holder.sala_room.setText(sala.getSala());
        holder.data_room.setText(sala.getData());
        holder.periodo_room.setText(sala.getPeriodo());
        holder.tipo.setText(sala.getTipo());
        holder.count.setText(sala.getQuantidade());

        holder.cardview.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent it = new Intent(context, UpdateRequestRoom.class);
                it.putExtra("sala",sala);
                context.startActivity(it);
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
        TextView professor_room;
        TextView sala_room;
        TextView data_room;
        TextView periodo_room;
        TextView tipo;
        TextView count;

        public RoomViewHolder(View itemView) {
            super(itemView);

            cardview = (CardView) itemView;
            id = itemView.findViewById(R.id.id);
            professor_room = itemView.findViewById(R.id.professor_room);
            sala_room = itemView.findViewById(R.id.sala_room);
            data_room = itemView.findViewById(R.id.data_room);
            periodo_room = itemView.findViewById(R.id.periodo_room);
            tipo = itemView.findViewById(R.id.tipo);
            count = itemView.findViewById(R.id.count);
        }

    }
}
