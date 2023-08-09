package br.com.calculos.service;

import br.com.calculos.entity.Numero;
import br.com.calculos.repository.NumeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NumeroService {
    @Autowired
    private NumeroRepository numeroRep;

    private List<Double> numeros = new ArrayList<>();


    @Transactional(rollbackFor = Exception.class)
    public void createNumero(final Numero numero){

        numeros.add(numero.getNumeros());

    }
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> calculaMedia(){

        double soma = numeros.stream().mapToDouble(Double::doubleValue).sum();
        double media = soma / numeros.size();

        return ResponseEntity.ok(media);



    }

    public ResponseEntity<?> calcularDesvioPadrao(){

        double media = (double) calculaMedia().getBody();
        double somaDiferencasQuadrado = numeros.stream()
                .mapToDouble(num -> Math.pow(num - media, 2))
                .sum();

        double desvioPadrao = Math.sqrt(somaDiferencasQuadrado / (numeros.size() - 1));

        return ResponseEntity.ok(desvioPadrao);
    }

    public ResponseEntity<?> calcularMediana(){
        List<Double> numerosOrdenados = new ArrayList<>(numeros);
        Collections.sort(numerosOrdenados);

        int tamanho = numerosOrdenados.size();
        double mediana;

        if (tamanho % 2 == 0) {
            int meio = tamanho / 2;
            mediana = (numerosOrdenados.get(meio - 1) + numerosOrdenados.get(meio)) / 2;
        } else {
            mediana = numerosOrdenados.get(tamanho / 2);
        }

        return ResponseEntity.ok(mediana);
    }

    public ResponseEntity<?> quantidade(){
        return ResponseEntity.ok(numeros.size());
    }

}
