package model;

import java.time.LocalDateTime;


public class Pagamentos {

    private Reserva reserva;
private double valor;
private LocalDateTime dataPagamento;
private String tipoPagamento;
public enum StatusPagamento {
    PENDENTE,
    APROVADO
}
private StatusPagamento statusPagamento;

    public Pagamentos(Reserva reserva,double valor,LocalDateTime dataPagamento,String tipoPagamento, StatusPagamento statusPagamento ) {
        this.reserva = reserva;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = StatusPagamento.PENDENTE;
    }
    public Reserva getReserva() {
        return reserva;
    }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public String getTipoPagamento() {
        return tipoPagamento;
    }
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }


}

