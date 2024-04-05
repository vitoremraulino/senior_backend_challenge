package vitor.projeto.Model.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HospedeView {
    private Hospede hospede;
    private double totalGasto;
    private double valorUltimaReserva;

    public HospedeView(Hospede hospede, double totalGasto, double valorUltimaReserva) {
        this.hospede = hospede;
        this.totalGasto = totalGasto;
        this.valorUltimaReserva = valorUltimaReserva;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public double getValorUltimaReserva() {
        return valorUltimaReserva;
    }

    public void setValorUltimaReserva(double valorUltimaReserva) {
        this.valorUltimaReserva = valorUltimaReserva;
    }

    @Override
    public String toString() {
        return "HospedeView{" +
                "hospede=" + hospede +
                ", totalGasto=" + totalGasto +
                ", valorUltimaReserva=" + valorUltimaReserva +
                '}';
    }
}
