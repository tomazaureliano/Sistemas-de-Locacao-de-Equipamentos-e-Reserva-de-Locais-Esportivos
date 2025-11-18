package service;
import exceptions.CapacidadeExcedida;
import exceptions.FuncionarioNaoAutorizado;
import exceptions.LocalJaReservado;
import model.Equipamento;
import model.Funcionario;
import model.LocalEsportivo;
import model.Reserva;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservaService {
    private List<Reserva> reservas = new ArrayList<>();
    private static final long HORAS_CANCELAMENTO_LIMITE = 2;

    public Reserva criarReserva(Reserva novaReserva) throws CapacidadeExcedida, LocalJaReservado, FuncionarioNaoAutorizado {
       if(novaReserva.getConvidados() > novaReserva.getLocal().getCapacidade()){
         throw new CapacidadeExcedida("capacidade de convidados excedida");

       }
       for(Reserva reservaExistente : reservas){
           if(reservaExistente.getLocal().equals(novaReserva.getLocal()) && reservaExistente.estaAtiva() && horariosConflitam(reservaExistente.getInicio()
                   , reservaExistente.getFim(), novaReserva.getInicio(), novaReserva.getFim())){
              throw new LocalJaReservado("Local já reservado nesse horário");

           }
       }

       if(novaReserva.getFuncionario().getAutorizacao() == Funcionario.Autorizacao.NAOAUTORIZADO){
           throw new FuncionarioNaoAutorizado("funcionario nao autorizado");

       }
        reservas.add(novaReserva);
        System.out.println("Reserva Criada");
        return novaReserva;

    }

    public void cancelarReserva(Reserva reserva) {
        LocalDateTime agora = LocalDateTime.now();
        long horasFaltando = Duration.between(agora, reserva.getInicio()).toHours();

        if (horasFaltando < HORAS_CANCELAMENTO_LIMITE) {
            System.out.println("Cancelamento tarde");
            System.out.println("Aplicando multa...");
            reserva.setValor((reserva.getValor() + horasFaltando));

        }

        reserva.cancelar();
        System.out.println("Reserva cancelada com sucesso");
    }
    private boolean horariosConflitam(LocalDateTime inicio1, LocalDateTime fim1,
                                      LocalDateTime inicio2, LocalDateTime fim2) {
        return (inicio1.isBefore(fim2) && fim1.isAfter(inicio2));
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}
