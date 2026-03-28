import java.util.Arrays;

public class MergeSort<T extends Comparable> implements IOrdenador<T> {
        private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;
    private double inicio;

    private double nanoToMilli = 1.0/1_000_000;

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoOrdenacao;
    }

    private void iniciar(){
        this.comparacoes = 0;
        this.movimentacoes = 0;
        this.inicio = System.nanoTime();
    }

    private void terminar(){
        this.tempoOrdenacao = (System.nanoTime() - this.inicio) * nanoToMilli;
    }

    private void swap(int x, int y, T[] vetor) {
        T temp = vetor[x];
        vetor[x] = vetor[y];
        vetor[y] = temp;
        movimentacoes+=3;
    }

    @Override
    public T[] ordenar(T[] dados) {
        T[] dadosOrdenados = Arrays.copyOf(dados, dados.length);
        int tamanho = dadosOrdenados.length;
        iniciar();
        mergesort(dadosOrdenados, 0, tamanho);
        terminar();
        return dadosOrdenados;
    }

    private void mergesort(T[] dados, int esq, int dir){
        if (esq < dir){
            int meio = (esq + dir) / 2;
            mergesort(dados, esq, meio);
            mergesort(dados, meio + 1, dir);
            intercalar(dados, esq, meio, dir);
        }
    }

    
    private void intercalar(T[]dados, int esq, int meio, int dir) {
        int n1, n2, i, j, k;

        //Definir tamanho dos dois subarrays
        n1 = meio - esq + 1;
        n2 = dir - meio;

        Integer[] a1 = new Integer[n1];
        Integer[] a2 = new Integer[n2];

        //Inicializar primeiro subarray
      	for (i = 0; i < n1; i++) {
        	a1[i] = dados[esq + i];
      	}

      	//Inicializar segundo subarray
      	for (j = 0; j < n2; j++) {
        	a2[j] = dados[meio + j + 1];
      	}

	    //Intercalação propriamente dita
      	for (i = j = 0, k = esq; (i < n1 && j < n2); k++) {
            if (a1[i] <= a2[j]){
                dados[k] = a1[i++];
            }else{
                dados[k] = a2[j++];
            }
        }
	    if (i == n1){
            for (; k <= dir; k++) {
                dados[k] = a2[j++];
            }
        }else{
            for (; k <= dir; k++) {
                dados[k] = a1[i++];
            }
        }
    }            
}