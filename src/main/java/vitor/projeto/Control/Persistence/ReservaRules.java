package vitor.projeto.Control.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitor.projeto.Control.Controller.ReservaController;
import vitor.projeto.Control.Persistence.Exceptions.DadosInvalidosException;
import vitor.projeto.Control.Persistence.Exceptions.FormatoInvalidoException;
import vitor.projeto.Model.Entities.Reserva;

import java.util.Calendar;
import java.util.Date;
import java.time.Instant;

@Service
public class ReservaRules {
    @Autowired
    private final ReservaController reservaController;

    public ReservaRules(ReservaController reservaController) {
        this.reservaController = reservaController;
    }

    public Reserva saveReserva(Reserva reserva){
        Reserva reservaRecalculada = reserva;
        Date dataEntrada = reserva.getDataEntrada();
        Date dataSaida = reserva.getDataSaida();
        double valorDiariaDiasDeSemana = 0;
        double valorDiariaFimDeSemana = 0;

        double adicionalDiasDeSemana = 0;
        double adicionalFimDeSemana = 0;

        double adicionalExtra = 0;

        double valorTotalReserva = 0;

        int diasSemana = 0;
        int diasFinalDeSemana = 0;
        if(reserva.getDataEntrada() == null){
            throw new DadosInvalidosException("Data de entrada informada é inválida");
        }
        if(reserva.getDataSaida() == null){
            throw new DadosInvalidosException("Data de saída informada é inválida");
        }
        if(reserva.getDataSaida().getTime() < reserva.getDataEntrada().getTime()){
            throw new DadosInvalidosException("Período de data informado é inválido");
        }

        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(reserva.getDataSaida());
        cal2.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DATE), 16, 30);

        diasSemana = getWeekDays(dataEntrada, dataSaida);
        diasFinalDeSemana = getWeekendDays(dataEntrada, dataSaida);

        if(cal.getTime().getTime() >= cal2.getTime().getTime()){
            adicionalExtra = 120;
        }
        valorDiariaDiasDeSemana = diasSemana * 120;
        valorDiariaFimDeSemana = diasFinalDeSemana * 150;

        if(reserva.isAdicionalVeiculo()) {
            adicionalDiasDeSemana = diasSemana * 15;
            adicionalFimDeSemana = diasFinalDeSemana * 20;
        }
        valorTotalReserva = valorDiariaDiasDeSemana+valorDiariaFimDeSemana+adicionalDiasDeSemana+adicionalFimDeSemana+adicionalExtra;

        reservaRecalculada.setValorTotal(valorTotalReserva);
        reservaRecalculada.setDataCadastro(Date.from(Instant.now()));
        try {
            return reservaController.saveReserva(reservaRecalculada);
        } catch (Exception e){
            throw new FormatoInvalidoException("Formato da reserva informada é inválido");
        }
    }
    private int getWeekDays(Date start, Date end){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        int w1 = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DAY_OF_WEEK, -w1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int w2 = c2.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DAY_OF_WEEK, -w2);

        long days = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24);
        long daysWithoutWeekendDays = days-(days*2/7);

        if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
            w1 = Calendar.MONDAY;
        } else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
            w1 = Calendar.FRIDAY;
        }

        if (w2 == Calendar.SUNDAY) {
            w2 = Calendar.MONDAY;
        } else if (w2 == Calendar.SATURDAY) {
            w2 = Calendar.FRIDAY;
        }

        return (int)((daysWithoutWeekendDays-w1+w2) + 1);
    }
    private int getWeekendDays(Date start, Date end){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        int w1 = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DAY_OF_WEEK, -w1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int w2 = c2.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DAY_OF_WEEK, -w2);

        long days = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24);
        long daysWeekend = (days*7/2)/24;

        return (int)(daysWeekend) + 1;
    }
}
