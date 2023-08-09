package br.com.calculos.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "numeros", schema = "public")
public class Numero {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private long id;

    @Getter @Setter
    @Column(name = "numero")
    private double numeros;


}
