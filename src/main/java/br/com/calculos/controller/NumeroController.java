package br.com.calculos.controller;


import br.com.calculos.entity.Numero;
import br.com.calculos.repository.NumeroRepository;
import br.com.calculos.service.NumeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/api/numero")
public class NumeroController {

    @Autowired
    private NumeroRepository numeroRep;
    @Autowired
    private NumeroService numeroServ;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Numero numero = this.numeroRep.findById(id).orElse(null);
        return ResponseEntity.ok(numero);
    }

    @GetMapping("/calcularMedia")
    public ResponseEntity<Double> calcularMedia() {

        return (ResponseEntity<Double>) numeroServ.calculaMedia();
    }

    @GetMapping("/calcularMediana")
    public ResponseEntity<Double> calcularMediana() {

        return (ResponseEntity<Double>) numeroServ.calcularMediana();
    }

    @GetMapping("/calcularDesvio")
    public ResponseEntity<Double> calcularDesvio() {

        return (ResponseEntity<Double>) numeroServ.calcularDesvioPadrao();
    }

    @GetMapping("/quantidade")
    public ResponseEntity<Double> quantidade() {

        return (ResponseEntity<Double>) numeroServ.quantidade();
    }

    @GetMapping("/lista")
    public ResponseEntity <?> ListCompNumero(){
        return ResponseEntity.ok(this.numeroRep.findAll());

    }

    @PostMapping
    public ResponseEntity <?> cadastrarNumero(@RequestBody final Numero numero){
        try {
            this.numeroServ.createNumero(numero);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarNumero(@PathVariable("id") final Long id, @RequestBody final Numero numero){
        try {
            final Numero numero1 = this.numeroRep.findById(id).orElse(null);

            if (numero1 == null || numero1.getId() != (numero1.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.numeroRep.save(numero);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaNumero(@PathVariable Long id) {
        try {

            this.numeroRep.deleteById(id);
            return ResponseEntity.ok("Excluido");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}
