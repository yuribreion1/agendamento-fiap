package br.com.fiap.agendamento_fiap.ws;

import br.com.fiap.agendamento_fiap.model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("consultar/usuario")
    Call<User> getUser();
}
