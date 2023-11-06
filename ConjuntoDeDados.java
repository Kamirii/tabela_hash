package com.tabelahash;

import java.util.Random;

public class ConjuntoDeDados {

    public int tam_conjunto;
    public Registro[] conjuntoDeDados;

    private class Registro {
        public int codigo;

        public Registro(long seed) {
            Random random = new Random(seed);
            codigo = 100000000 + random.nextInt(900000000);
        }
    }

    public ConjuntoDeDados(int tam_conjunto, long seed) {
        this.tam_conjunto = tam_conjunto;

        conjuntoDeDados = new Registro[tam_conjunto];
        
        for (int i = 0; i < tam_conjunto; i++) {
            conjuntoDeDados[i] = new Registro(seed + i);
        }
    }

    public void visualizarDados() {
        System.out.println("Visualização dos dados:");
        for (int i = 0; i < tam_conjunto; i++) {
            System.out.println("Posição " + i + ": Código - " + conjuntoDeDados[i].codigo);
        }
    }

    public int[] getDados() {
        int[] dados = new int[tam_conjunto];
        for (int i = 0; i < tam_conjunto; i++) {
            dados[i] = conjuntoDeDados[i].codigo;
        }
        return dados;
    }

}