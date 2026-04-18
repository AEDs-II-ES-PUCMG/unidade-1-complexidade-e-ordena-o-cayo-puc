<<<<<<< HEAD
import java.util.Comparator;

public class SelectionSort<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dadosOrdenados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public SelectionSort() {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(T::compareTo);
	}
	
	public SelectionSort(Comparator<T> comparador) {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(comparador);
	}
	
	@Override
	public void setComparador(Comparator<T> comparador) {
		this.comparador = comparador;
=======
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

public class SelectionSort<T extends Comparable<T>> implements IOrdenador<T>{
    private long comparacoes;
	private long movimentacoes;
	private LocalDateTime inicio;
	private LocalDateTime termino;	
	
	public SelectionSort() {
		comparacoes = 0;
		movimentacoes = 0;
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
	}
	
	@Override
	public T[] ordenar(T[] dados) {
<<<<<<< HEAD
	
		dadosOrdenados = dados;
		
		comparacoes = 0;
		movimentacoes = 0;
		iniciar();
		
		for (int i = 0; i < (dadosOrdenados.length - 1); i++) {
			 int menor = i;
			 for (int j = (i + 1); j < dadosOrdenados.length; j++) {
				 comparacoes++;
	        	 if (comparador.compare(dadosOrdenados[menor], dadosOrdenados[j]) > 0)
	        		 menor = j;
			 }
	         if (menor != i)
	        	 swap(menor, i);
	    }
		
		terminar();
		
		return dadosOrdenados;
	}
	
	private void swap(int i, int j) {
	      
		movimentacoes++;
		
		T temp = dadosOrdenados[i];
	    dadosOrdenados[i] = dadosOrdenados[j];
	    dadosOrdenados[j] = temp;
	}
	
	@Override
=======
		return ordenar(dados, T::compareTo);
	}
	
	@Override
	public T[] ordenar(T[] dados, Comparator<T> comparador) {
		T[] dadosOrdenados = Arrays.copyOf(dados, dados.length);
		int tamanho = dadosOrdenados.length;
		
		inicio = LocalDateTime.now();
		
		for (int posReferencia = 0; posReferencia < tamanho ; posReferencia++) {
            int posMenor = posReferencia;
			for (int posicao = posReferencia+1; posicao < tamanho; posicao++) {
				comparacoes++;
				if (comparador.compare(dadosOrdenados[posMenor],dadosOrdenados[posicao]) > 0){
					posMenor = posicao;
				}
			}
			swap(posReferencia, posMenor, dadosOrdenados);
		}	
		termino = LocalDateTime.now();

		return dadosOrdenados;
	}

	private void swap(int i, int j, T[] vet) {
		movimentacoes++;
		
		T temp = vet[i];
	    vet[i] = vet[j];
	    vet[j] = temp;
	}
	
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
	public long getComparacoes() {
		return comparacoes;
	}
	
<<<<<<< HEAD
	@Override
=======
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
	public long getMovimentacoes() {
		return movimentacoes;
	}
	
<<<<<<< HEAD
	private void iniciar() {
		inicio = System.nanoTime();
	}
	
	private void terminar() {
		termino = System.nanoTime();
	}
	
	@Override
	public double getTempoOrdenacao() {
		
		double tempoTotal;
		
	    tempoTotal = (termino - inicio) / 1_000_000;
	    return tempoTotal;
	}
}
=======
	public double getTempoOrdenacao() {
	    return  0;
	}
}
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
