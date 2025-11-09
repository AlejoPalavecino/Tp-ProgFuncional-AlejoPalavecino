package org.example.Entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private  String titulo;
    private String autor;
    private int paginas;
    private double precio;
}
