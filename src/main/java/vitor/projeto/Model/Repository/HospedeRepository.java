package vitor.projeto.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vitor.projeto.Model.Entities.Hospede;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface HospedeRepository extends JpaRepository<Hospede, UUID> {

    @Query(
            value = "select * from hospedes " +
                    "where (:uuid is null or hospedes.uuid= :uuid) " +
                    "and " +
                    "(:nome is null or hospedes.nome= :nome) " +
                    "and " +
                    "(:telefone is null or hospedes.telefone= :telefone) " +
                    "and " +
                    "(:documento is null or hospedes.documento= :documento)",
            nativeQuery = true
    )
    List<Hospede> findHospedes(
            @Param("uuid") UUID uuid,
            @Param("nome") String nome,
            @Param("telefone") String telefone,
            @Param("documento") Integer documento);

    @Query(value = "select hospedes.* from hospedes inner join reservas on reservas.hospede_uuid = hospedes.uuid and reservas.data_saida <= now() group by hospedes.uuid", nativeQuery = true)
    List<Hospede> findHospedesSairam();

    @Query(value = "select hospedes.* from hospedes inner join reservas on reservas.hospede_uuid = hospedes.uuid and reservas.data_entrada >= now() and reservas.data_saida >= now() group by hospedes.uuid", nativeQuery = true)
    List<Hospede> findHospedesNaoSairam();

    @Query(value = "select reservas.valor_total from hospedes inner join reservas on reservas.hospede_uuid = hospedes.uuid where hospedes.uuid = :uuid order by reservas.data_cadastro desc limit 1", nativeQuery = true)
    Double findUltimoValorGasto(@Param("uuid") UUID uuid);

    @Query(value = "select sum(reservas.valor_total) from hospedes inner join reservas on reservas.hospede_uuid = hospedes.uuid where hospedes.uuid = :uuid", nativeQuery = true)
    Double findTotalGasto(@Param("uuid") UUID uuid);
}
