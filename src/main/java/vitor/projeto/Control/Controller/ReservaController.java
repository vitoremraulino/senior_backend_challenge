package vitor.projeto.Control.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vitor.projeto.Model.Entities.Reserva;
import vitor.projeto.Model.Repository.ReservaRepository;

@Service
public class ReservaController {

    @Autowired
    private final ReservaRepository reservaRepository;

    public ReservaController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    public Reserva saveReserva(Reserva reserva){
        return reservaRepository.save(reserva);
    }
}
