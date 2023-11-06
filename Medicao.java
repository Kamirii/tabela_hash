package com.tabelahash;

public class Medicao {
    private long startTime;
    private long endTime;

    public void iniciarMedicao() {
        startTime = System.nanoTime();
    }

    public void pararMedicao() {
        endTime = System.nanoTime();
    }

    public double obterTempoSegundos() {
        long tempoNanos = endTime - startTime;
        return (double) tempoNanos / 1e9; // Converter para segundos
    }



    
}
