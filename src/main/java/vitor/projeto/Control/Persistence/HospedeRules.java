package vitor.projeto.Control.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitor.projeto.Control.Controller.HospedeController;
import vitor.projeto.Control.Persistence.Exceptions.DadosInvalidosException;
import vitor.projeto.Control.Persistence.Exceptions.FormatoInvalidoException;
import vitor.projeto.Model.Entities.Hospede;
import vitor.projeto.Model.Entities.HospedeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class HospedeRules {
    @Autowired
    private final HospedeController hospedeController;

    public HospedeRules(HospedeController hospedeController) {
        this.hospedeController = hospedeController;
    }

    public Hospede saveHospede(Hospede hospede){
        if(hospede.getNome() == null || hospede.getNome().isBlank()){
            throw new DadosInvalidosException("Nome do hóspede não pode ser vazio");
        }
        if(hospede.getDocumento() <= 0){
            throw new DadosInvalidosException("Documento do hóspede não pode ser vazio");
        }
        if(hospede.getTelefone() == null || hospede.getTelefone().isBlank()){
            throw new DadosInvalidosException("Telefone do hóspede não pode ser vazio");
        }
        try {
            return hospedeController.saveHospede(hospede);
        } catch (Exception e){
            throw new FormatoInvalidoException("Formato do hóspede informado é inválido");
        }
    }

    public double findTotalGasto(UUID uuid){
        Double totalGasto = 0.0;
        totalGasto = hospedeController.findValorGasto(uuid);
        if(totalGasto == null){
            totalGasto = 0.0;
        }
        return totalGasto;
    }
    public double findUltimoGasto(UUID uuid){
        Double ultimoGasto;
        ultimoGasto = hospedeController.findUltimoGasto(uuid);
        if(ultimoGasto == null){
            ultimoGasto = 0.0;
        }
        return ultimoGasto;
    }

    public HospedeView findHospedeByUuid(UUID uuid){
        HospedeView hospedeView = new HospedeView(hospedeController.findHospedeByUuid(uuid), findTotalGasto(uuid), findUltimoGasto(uuid));
        return hospedeView;
    }

    public List<HospedeView> findHospedes(UUID uuid, String nome, String telefone, Integer documento) {
        List<Hospede> hospedes = hospedeController.findHospedes(uuid, nome, telefone, documento);
        List<HospedeView> hospedeViews = new ArrayList<>();
        for(Hospede hospede: hospedes){
            HospedeView hospedeView = new HospedeView(hospede, findTotalGasto(hospede.getUuid()), findUltimoGasto(hospede.getUuid()));
            hospedeViews.add(hospedeView);
        }
        return hospedeViews;
    }
    public List<HospedeView> findHospedesSairam() {
        List<Hospede> hospedes = hospedeController.findHospedesSairam();
        List<HospedeView> hospedeViews = new ArrayList<>();
        for(Hospede hospede: hospedes){
            HospedeView hospedeView = new HospedeView(hospede, findTotalGasto(hospede.getUuid()), findUltimoGasto(hospede.getUuid()));
            hospedeViews.add(hospedeView);
        }
        return hospedeViews;
    }
    public List<HospedeView> findHospedesNaoSairam() {

        List<Hospede> hospedes = hospedeController.findHospedesNaoSairam();
        List<HospedeView> hospedeViews = new ArrayList<>();
        for(Hospede hospede: hospedes){
            HospedeView hospedeView = new HospedeView(hospede, findTotalGasto(hospede.getUuid()), findUltimoGasto(hospede.getUuid()));
            hospedeViews.add(hospedeView);
        }
        return hospedeViews;
    }
}
