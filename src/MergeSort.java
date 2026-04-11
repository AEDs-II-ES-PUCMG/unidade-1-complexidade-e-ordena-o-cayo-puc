import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements IOrdenador<T> {

    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;
    private double inicio;

    private double nanoToMilli = 1.0 / 1_000_000;

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

    private void iniciar() {
        this.comparacoes = 0;
        this.movimentacoes = 0;
        this.inicio = System.nanoTime();
    }

    private void terminar() {
        this.tempoOrdenacao = (System.nanoTime() - this.inicio) * nanoToMilli;
    }

    @Override
    public T[] ordenar(T[] dados) {
        T[] dadosOrdenados = Arrays.copyOf(dados, dados.length);
        iniciar();
        mergesort(dadosOrdenados, 0, dadosOrdenados.length - 1);
        terminar();
        return dadosOrdenados;
    }

    private void mergesort(T[] dados, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(dados, esq, meio);
            mergesort(dados, meio + 1, dir);
            intercalar(dados, esq, meio, dir);
        }
    }

    private void intercalar(T[] dados, int esq, int meio, int dir) {

        T[] a1 = Arrays.copyOfRange(dados, esq, meio + 1);
        T[] a2 = Arrays.copyOfRange(dados, meio + 1, dir + 1);

        int i = 0, j = 0, k = esq;

        while (i < a1.length && j < a2.length) {
            comparacoes++;
            if (a1[i].compareTo(a2[j]) <= 0) {
                dados[k++] = a1[i++];
            } else {
                dados[k++] = a2[j++];
            }
            movimentacoes++;
        }

        while (i < a1.length) {
            dados[k++] = a1[i++];
            movimentacoes++;
        }

        while (j < a2.length) {
            dados[k++] = a2[j++];
            movimentacoes++;
        }
    }
}