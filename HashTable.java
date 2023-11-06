package com.tabelahash;

import java.util.ArrayList;
import java.util.List;

//tratamento de colisao
//criacao das hashfunction
//criacao das tabelas hash

@FunctionalInterface
interface HashFunction {
    int apply(int key, int tam_tabela);
}

public class HashTable {
    public int tam_tabela;
    public int[] tabela;
    public int colisoes = 0;
    public double loadFactor = 0;
    public List<Integer> chavesColisao;

    public HashTable(int[] tabela) {
        this.tam_tabela = tabela.length;
        this.tabela = tabela;
        chavesColisao = new ArrayList<>();
    };

    public void adicionarChave(int chave, int posicao, int[] tabela) {
        tabela[posicao] = chave;
    }

    public void Expand_Table(HashFunction hashFunction, int novo_tam) {
        int[] expand_tabela = new int[novo_tam];
        this.tam_tabela = novo_tam;
        for (int i = 0; i < this.tabela.length; i++) {
            if (this.tabela[i] != 0) {
                int chave = this.tabela[i];
                int posicao = hashFunction.apply(chave, novo_tam);
                int stepSize = DoubleHashing(chave);

                while (expand_tabela[posicao] != 0) {
                    posicao += stepSize;
                    posicao %= novo_tam;
                }

                expand_tabela[posicao] = chave;
            }
        }

        this.tabela = expand_tabela;
        this.tam_tabela = novo_tam;
    }

    public void aplicarFuncaoDeHashATodosOsValores(HashFunction hashFunction, ConjuntoDeDados conjunto) {

        int elementos = 0;
        int[] dados = conjunto.getDados();

        for (int i = 0; i < dados.length; i++) {
            int chave = dados[i];
            int posicao = hashFunction.apply(chave, this.tam_tabela);
            int stepSize = DoubleHashing(chave);
            // System.out.println(stepSize);
            if (this.tabela[posicao] == 0 || this.tabela[posicao] == chave) {

                adicionarChave(chave, posicao, this.tabela);
                elementos++;
            } else {
                colisoes++;
                chavesColisao.add(chave);
                while (this.tabela[posicao] != 0 && this.tabela[posicao] != chave) {
                    posicao += stepSize;
                    posicao %= this.tam_tabela;
                }
                adicionarChave(chave, posicao, this.tabela);
                elementos++;
            }

            loadFactor = (double) elementos / this.tam_tabela;

            if (loadFactor >= 0.9) {
                int novo_tam = getPrime(tam_tabela);
                //System.out.println("Novo tamanho tabela" + novo_tam);
                Expand_Table(hashFunction, novo_tam);
            }
        }
        //System.out.println("Load Factor: " + loadFactor);
        //System.out.println("Total de colisoes:" + colisoes);
    }


    public static int DoubleHashing(int chave) {
        return 5 - (chave % 5);
    }

    public static int DivisionHashing(int chave, int tam_tabela) {
        return chave % tam_tabela;
    }
    public static int FoldingHashing(int chave, int tam_tabela) {

        int sum = 0;
        while (chave > 0) {
            sum += chave % 1000;
            chave /= 1000;
        }
        return sum % tam_tabela;
    }

    public static int MultiplicationHashing(int chave, int tam_tabela) {
        double constantA = 0.6180339887; // regra de ouro, optimal value according to Knuth
        double result = chave * constantA;
        result -= Math.floor(result); 
        return (int) (tam_tabela * result);

    }

    
    public void BuscaPosicao(HashFunction hashFunction, ConjuntoDeDados conjunto) {
        int[] dados = conjunto.getDados();
        for (int i = 0; i < dados.length; i++) {
            int chave = dados[i];
            int posicao = hashFunction.apply(chave, this.tam_tabela);
            int stepSize = DoubleHashing(chave);

            while (this.tabela[posicao] != 0) {
                if (this.tabela[posicao] == chave) {
                    //System.out.println(chave + " na posição " + posicao);
                    break;
                }
                posicao += stepSize;
                posicao %= this.tam_tabela;
            }
            if (this.tabela[posicao] == 0 || this.tabela[posicao] != chave) {
                //System.out.println("Erro: Não foi possível recuperar a chave " + chave + " na posição " + posicao);
            }
        }
    }


    public void getChavesColisao() {
        for (int chave : chavesColisao) {
            System.out.println(chave);
        }
    }

    public double getLoadFactor() {
        return this.loadFactor;
    }

    public int getColisoes() {
       //System.out.println("Total colisoes:" + colisoes);
        return this.colisoes;
    }
    

    public int getTamanhoTabela() {
        //System.out.println("Tamanho tabela:" + this.tam_tabela);
         return this.tam_tabela;
    } 

    public void visualizarTabela() {
        System.out.println("Visualização dos dados:");
        for (int i = 0; i < this.tam_tabela; i++) {
            System.out.println("Posicao " + i + "Elemento " + this.tabela[i]);
        }
    }

    private int getPrime(int min) {
        for (int j = min + 1; true; j++)
            if (isPrime(j))
                return j;
    }

    private boolean isPrime(int n) {
        for (int j = 2; (j * j <= n); j++)
            if (n % j == 0)
                return false;
        return true;
    }

}
