package vitor.projeto.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitor.projeto.Model.Entities.Reserva;

import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
}
