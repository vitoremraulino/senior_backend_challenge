package vitor.projeto.View.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitor.projeto.Control.Persistence.HospedeRules;
import vitor.projeto.Model.Entities.Hospede;
import vitor.projeto.Model.Entities.HospedeView;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/hospede")
public class HospedeService {
    @Autowired
    private final HospedeRules rules;

    public HospedeService(HospedeRules rules) {
        this.rules = rules;
    }

    @PostMapping("/save")
    public ResponseEntity<Hospede> saveHospede(@RequestBody Hospede hospede){
        return ResponseEntity.ok(rules.saveHospede(hospede));
    }

    @GetMapping("/find/{uuid}")
    public ResponseEntity<HospedeView> findHospede(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.ok(rules.findHospedeByUuid(uuid));
    }


    @GetMapping("/find")
    public List<HospedeView> findHospedes(@RequestParam (value = "uuid", required = false) UUID uuid,
                                      @RequestParam(value = "nome", required = false) String nome,
                                      @RequestParam(value = "telefone", required = false) String telefone,
                                      @RequestParam(value = "documento", required = false) Integer documento){
        return rules.findHospedes(uuid, nome, telefone, documento);
    }

    @GetMapping("/find/naosairam")
    public List<HospedeView> findHospedesNaoSairam(){
        return rules.findHospedesNaoSairam();
    }


    @GetMapping("/find/sairam")
    public List<HospedeView> findHospedesSairam(){
        return rules.findHospedesSairam();
    }
}
