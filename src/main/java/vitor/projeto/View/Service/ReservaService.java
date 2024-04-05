package vitor.projeto.View.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitor.projeto.Control.Persistence.ReservaRules;
import vitor.projeto.Model.Entities.Reserva;

@RestController
@CrossOrigin("*")
@RequestMapping("/reserva")
public class ReservaService {
    @Autowired
    private final ReservaRules reservaRules;

    public ReservaService(ReservaRules reservaRules) {
        this.reservaRules = reservaRules;
    }

    @PostMapping("/save")
    public ResponseEntity<Reserva> saveReserva(@RequestBody Reserva reserva){
        return ResponseEntity.ok(reservaRules.saveReserva(reserva));
    }
}
