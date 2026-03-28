import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random();
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;
    

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;        
    }

    /**
     * Gerador de vetores de objetos do tipo Integer aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor de Objetos Integer com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static Integer[] gerarVetorObjetos(int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, 10 * tamanho);
        }
        return vetor;
    }


    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcao = -1;
        do{
            System.out.println("\nQual metódo deseja usar?\n1-Bubble sort\n2-Insertion Sort\n3-Selection Sort\n4-Merge Sort\n0-Sair");
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    BubbleSort<Integer> bolha = new BubbleSort<>();
                    teste(bolha, "Bubble Sort");
                    break;
                case 2:
                    InsertionSort<Integer> insertion = new InsertionSort<>();
                    teste(insertion, "Insertion Sort");
                    break;
                case 3:
                    SelectionSort<Integer> selection = new SelectionSort<>();
                    teste(selection, "Selection Sort");
                    break;
                case 4:
                    MergeSort<Integer> merge = new MergeSort<>();
                    teste(merge, "Merge Sort");
                    break;
                
                default:
                    break;
            }
        }while(opcao != 0);
        
        /*for(int tam : tamanhosTestePequeno){
            Integer[] vetor = gerarVetorObjetos(tam);
            System.out.println("\nVetor de tamanho: "+tam);

            BubbleSort<Integer> bolha = new BubbleSort<>();
            Integer[] vetorOrdenadoBolha = bolha.ordenar(vetor);

            System.out.println("\nVetor ordenado método Bolha:");
            System.out.println("Comparações: " + bolha.getComparacoes());
            System.out.println("Movimentações: " + bolha.getMovimentacoes());
            System.out.println("Tempo de ordenação (ms): " + bolha.getTempoOrdenacao());

            InsertionSort<Integer> insertion = new InsertionSort<>();
            insertion.ordenar(vetor);

            System.out.println("\nInsertionSort:");
            System.out.println("Comparações: " + insertion.getComparacoes());
            System.out.println("Movimentações: " + insertion.getMovimentacoes());
            System.out.println("Tempo (ms): " + insertion.getTempoOrdenacao());

            SelectionSort<Integer> selection = new SelectionSort<>();
            selection.ordenar(vetor);

            System.out.println("\nSelectionSort:");
            System.out.println("Comparações: " + selection.getComparacoes());
            System.out.println("Movimentações: " + selection.getMovimentacoes());
            System.out.println("Tempo (ms): " + selection.getTempoOrdenacao());
        }*/
        
    }

    public static void teste(IOrdenador ordenador, String tipo){
        System.out.println("Vetor ordenado pelo metodo "+tipo);
        for(int tam : tamanhosTestePequeno){
            Integer[] vetor = gerarVetorObjetos(tam);
            System.out.println("\nVetor de tamanho: "+tam);
            ordenador.ordenar(vetor);
            System.out.println("Comparações: " + ordenador.getComparacoes());
            System.out.println("Movimentações: " + ordenador.getMovimentacoes());
            System.out.println("Tempo de ordenação (ms): " + ordenador.getTempoOrdenacao());
        
        }
    }
}
