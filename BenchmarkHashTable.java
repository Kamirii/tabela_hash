package com.tabelahash;

// 11,101,1033,10007,104729

public class BenchmarkHashTable {

    public static void main(String[] args) {
        int seed = 123456789;

        int[] tabela = new int[98737];

        ConjuntoDeDados conjunto = new ConjuntoDeDados(100000, seed);
        HashTable hash_tabela = new HashTable(tabela);

        Medicao medidor = new Medicao();

        //FoldingHashing
        //DivisionHashing
        //MultiplicationHashing
        medidor.iniciarMedicao();
        hash_tabela.aplicarFuncaoDeHashATodosOsValores(HashTable::FoldingHashing, conjunto);
        medidor.pararMedicao();
        double tempoSeg = medidor.obterTempoSegundos();

        medidor.iniciarMedicao();
        hash_tabela.BuscaPosicao(HashTable::FoldingHashing, conjunto);
        medidor.pararMedicao();
         double buscaSeg = medidor.obterTempoSegundos();

        //int tam = hash_tabela.getTamanhoTabela();
        int colisoes = hash_tabela.getColisoes();
        //double loadFactor = hash_tabela.getLoadFactor();
        System.out.println("========================");
        System.out.println("Tempo de execução de Insercao: " + tempoSeg + " segundos");
        System.out.println("========================");
        System.out.println("Tempo de execução de Busca: " + buscaSeg + " segundos");
        System.out.println("------------------------");
        System.out.println("Tamanho tabela: " + tam);
        System.out.println("Total colisao: " + colisoes);
        //System.out.println("LoadFactor: " + loadFactor);
        
        //hash_tabela.getChavesColisao();

    }

}

// A programação funcional é especialmente útil quando você precisa lidar
// com operações de alto nível,
// como mapeamento, filtragem e redução de dados
