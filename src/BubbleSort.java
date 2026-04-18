<<<<<<< HEAD
import java.util.Comparator;

public class Bubblesort<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dadosOrdenados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Bubblesort() {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(T::compareTo);
	}
	
	public Bubblesort(Comparator<T> comparador) {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(comparador);
	}
	
	@Override
	public void setComparador(Comparator<T> comparador) {
		this.comparador = comparador;
	}
	
	@Override
	public T[] ordenar(T[] dados) {

		dadosOrdenados = dados;
		
		comparacoes = 0;
		movimentacoes = 0;
		iniciar();
		
		for (int i = dadosOrdenados.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				comparacoes++;
				if (comparador.compare(dadosOrdenados[j], dadosOrdenados[j + 1]) > 0)
					swap (j, j + 1);
			}
		}
		
		terminar();
		
		return  dadosOrdenados;
	}
	
	private void swap(int i, int j) {
	      
		movimentacoes++;
		
		T temp = dadosOrdenados[i];
	    dadosOrdenados[i] = dadosOrdenados[j];
	    dadosOrdenados[j] = temp;
	}
	
	@Override
=======
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;


public class Bubblesort<T extends Comparable<T>> implements IOrdenador<T>{

	private long comparacoes;
	private long movimentacoes;
	private LocalDateTime inicio;
	private LocalDateTime termino;	
	
	public Bubblesort() {
		comparacoes = 0;
		movimentacoes = 0;
	}
	
	@Override
	public T[] ordenar(T[] dados) {
		return ordenar(dados, T::compareTo);
	}

	@Override
	public T[] ordenar(T[] dados, Comparator<T> comparador) {
		T[] dadosOrdenados = Arrays.copyOf(dados, dados.length);
		int tamanho = dadosOrdenados.length;
		
		inicio = LocalDateTime.now();
		
		for (int posReferencia = tamanho - 1; posReferencia > 0; posReferencia--) {
			int trocas = 0;
			for (int posicao = 0; posicao < posReferencia; posicao++) {
				comparacoes++;
				if (comparador.compare(dadosOrdenados[posicao], dadosOrdenados[posicao+1]) > 0){
					swap (posicao, posicao + 1, dadosOrdenados);
					trocas++;
				}
			}
			if(trocas == 0 )
				posReferencia = 0;
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
=======
	public double getTempoOrdenacao() {
	    return  0;
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
	}
}