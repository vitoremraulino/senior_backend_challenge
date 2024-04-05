package vitor.projeto.Control.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vitor.projeto.Model.Entities.Hospede;
import vitor.projeto.Model.Repository.HospedeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class HospedeController {
    @Autowired
    private final HospedeRepository hospedeRepository;

    public HospedeController(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    @Transactional(readOnly = true)
    public Hospede findHospedeByUuid(UUID uuid){
        return hospedeRepository.getReferenceById(uuid);
    }

    @Transactional
    public Hospede saveHospede(Hospede hospede){
        return hospedeRepository.save(hospede);
    }

    @Transactional(readOnly = true)
    public List<Hospede> findHospedes(UUID uuid, String nome, String telefone, Integer documento) {
        return hospedeRepository.findHospedes(uuid, nome, telefone, documento);
    }

    @Transactional(readOnly = true)
    public List<Hospede> findHospedesNaoSairam() {
        return hospedeRepository.findHospedesNaoSairam();
    }

    @Transactional(readOnly = true)
    public List<Hospede> findHospedesSairam() {
        return hospedeRepository.findHospedesSairam();
    }

    @Transactional(readOnly = true)
    public Double findValorGasto(UUID uuid) {
        return hospedeRepository.findTotalGasto(uuid);
    }

    @Transactional(readOnly = true)
    public Double findUltimoGasto(UUID uuid) {
        return hospedeRepository.findUltimoValorGasto(uuid);
    }
}
