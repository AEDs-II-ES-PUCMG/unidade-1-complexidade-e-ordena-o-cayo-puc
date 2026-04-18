
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * MIT License
 *
 * Copyright(c) 2022-25 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class AppOficina {

    static final int MAX_PEDIDOS = 100;
    static Produto[] produtos;
    static Produto[] produtosOrdenadosId;
    static Produto[] produtosOrdenadosDescricao;
    static int quantProdutos = 0;
    static String nomeArquivoDados = "produtos.txt";
    static IOrdenador<Produto> ordenador;

  
    static Scanner teclado;

    

    static <T extends Number> T lerNumero(String mensagem, Class<T> classe) {
        System.out.print(mensagem + ": ");
        T valor;
        try {
            valor = classe.getConstructor(String.class).newInstance(teclado.nextLine());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            return null;
        }
        return valor;
    }

    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Tecle Enter para continuar.");
        teclado.nextLine();
    }

    static void cabecalho() {
        limparTela();
        System.out.println("XULAMBS COMÉRCIO DE COISINHAS v0.2\n================");
    }
    

    static int exibirMenuPrincipal() {
        cabecalho();
        System.out.println("1 - Procurar produto");
        System.out.println("2 - Filtrar produtos por preço máximo");
        System.out.println("3 - Ordenar produtos");
        System.out.println("4 - Embaralhar produtos");
        System.out.println("5 - Listar produtos");
        System.out.println("0 - Finalizar");
       
        return lerNumero("Digite sua opção", Integer.class);
    }

    static int exibirMenuOrdenadores() {
        cabecalho();
        System.out.println("1 - Bolha");
        System.out.println("2 - Inserção");
        System.out.println("3 - Seleção");
        System.out.println("4 - Mergesort");
        System.out.println("0 - Finalizar");
       
        return lerNumero("Digite sua opção", Integer.class);
    }

    static int exibirMenuComparadores() {
        cabecalho();
        System.out.println("1 - Padrão");
        System.out.println("2 - Por código");
        
        return lerNumero("Digite sua opção", Integer.class);
    }

    // #endregion
    static Produto[] carregarProdutos(String nomeArquivo){
        Scanner dados;
        Produto[] dadosCarregados;
        try{
            dados = new Scanner(new File(nomeArquivo));
            int tamanho = Integer.parseInt(dados.nextLine());
            
            dadosCarregados = new Produto[tamanho];
            while (dados.hasNextLine()) {
                Produto novoProduto = Produto.criarDoTexto(dados.nextLine());
                dadosCarregados[quantProdutos] = novoProduto;
                quantProdutos++;
            }
            dados.close();
        }catch (FileNotFoundException fex){
            System.out.println("Arquivo não encontrado. Produtos não carregados");
            dadosCarregados = null;
        }
        return dadosCarregados;
    }


    static Produto localizarProduto() {
        cabecalho();
        System.out.println("Localizando um produto");

        System.out.println("1 - Buscar por código");
        System.out.println("2 - Buscar por descrição");

        int opcao = lerNumero("Escolha", Integer.class);

        int inicio = 0;
        int fim = quantProdutos - 1;

        if (opcao == 1) {
            int codigo = lerNumero("Digite o código", Integer.class);

            while (inicio <= fim) {
                int meio = (inicio + fim) / 2;

                int atual = produtosOrdenadosId[meio].hashCode();

                if (atual == codigo) {
                    return produtosOrdenadosId[meio];
                } else if (atual < codigo) {
                    inicio = meio + 1;
                } else {
                    fim = meio - 1;
                }
            }

        } else if (opcao == 2) {
            System.out.print("Digite a descrição: ");
            String desc = teclado.nextLine();

            while (inicio <= fim) {
                int meio = (inicio + fim) / 2;

                String atual = produtosOrdenadosDescricao[meio]
                    .toString()
                    .split(" - ")[1]
                    .split(":")[0];

                int comparacao = atual.compareToIgnoreCase(desc);

                if (comparacao == 0) {
                    return produtosOrdenadosDescricao[meio];
                } else if (comparacao < 0) {
                    inicio = meio + 1;
                } else {
                    fim = meio - 1;
                }
            }
        }

        return null;
    }

    private static void mostrarProduto(Produto produto) {
        cabecalho();
        String mensagem = "Dados inválidos";
        
        if(produto!=null){
            mensagem = String.format("Dados do produto:\n%s", produto);            
        }
        
        System.out.println(mensagem);
    }

    private static void filtrarPorPrecoMaximo(){
        cabecalho();
        System.out.println("Filtrando por valor máximo:");
        double valor = lerNumero("valor", Double.class);
        StringBuilder relatorio = new StringBuilder();
        for (int i = 0; i < quantProdutos; i++) {
            if(produtos[i].valorDeVenda() < valor)
            relatorio.append(produtos[i]+"\n");
        }
        System.out.println(relatorio.toString());
    }

    static void ordenarProdutos(){
        cabecalho();
        Produto[] ordenado = null;
        Comparator<Produto> comparador = escolherComparador();
        int opcao = exibirMenuOrdenadores();
        switch (opcao) {
            case 1:
                Bubblesort<Produto> bolha = new Bubblesort<>();
                ordenado = bolha.ordenar(produtos, comparador);
                break;
            case 2:
                InsertSort<Produto> insertion = new InsertSort<>();
                ordenado = insertion.ordenar(produtos, comparador);
                break;
            case 3:
                SelectionSort<Produto> selection = new SelectionSort<>();
                ordenado = selection.ordenar(produtos, comparador);
                break;
            case 4:
                Mergesort<Produto> merge = new Mergesort<>();
                ordenado = merge.ordenar(produtos, comparador);
                break;
            default:
                    break;
        }
        verificarSubstituicao(produtos, ordenado);
    }

    static Comparator<Produto> escolherComparador(){
        Comparator comparador = null;
        int opcao = exibirMenuComparadores();
        switch(opcao){
            case 1:
                comparador = new ComparadorPorDescricao();
                break;
            case 2:
                comparador = new ComparadorPorCodigo();
                break;
        }
        return comparador;
    }

    static void embaralharProdutos(){
        Collections.shuffle(Arrays.asList(produtos));
    }

    static void verificarSubstituicao(Produto[] dadosOriginais, Produto[] copiaDados){
        cabecalho();
        System.out.print("Deseja sobrescrever os dados originais pelos ordenados (S/N)?");
        String resposta = teclado.nextLine().toUpperCase();
        if(resposta.equals("S"))
            dadosOriginais = Arrays.copyOf(copiaDados, copiaDados.length);
    }

    /*static void verificarSubstituicao(Produto[] dadosOriginais, Produto[] copiaDados){
        cabecalho();
        System.out.print("Deseja sobrescrever os dados originais pelos ordenados (S/N)?");
        String resposta = teclado.nextLine().toUpperCase();

        if(resposta.equals("S")) {
            for (int i = 0; i < copiaDados.length; i++) {
                dadosOriginais[i] = copiaDados[i];
            }
    }
    }*/

    static void listarProdutos(){
        cabecalho();
        for (int i = 0; i < quantProdutos; i++) {
            System.out.println(produtos[i]);
        }
    }

    public static void inicio(){
        Mergesort<Produto> merge = new Mergesort<>();
        Produto[] copia1 = Arrays.copyOf(produtos, quantProdutos);
        Produto[] copia2 = Arrays.copyOf(produtos, quantProdutos);
        produtosOrdenadosId = merge.ordenar(copia1, new ComparadorPorCodigo());
        produtosOrdenadosDescricao = merge.ordenar(copia2, new ComparadorPorDescricao());
    }
   
    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        produtos = carregarProdutos(nomeArquivoDados);
        inicio();
        embaralharProdutos();
        int opcao = -1;
        
        do {
            opcao = exibirMenuPrincipal();
            switch (opcao) {
                case 1 -> mostrarProduto(localizarProduto());
                case 2 -> filtrarPorPrecoMaximo();
                case 3 -> ordenarProdutos();
                case 4 -> embaralharProdutos();
                case 5 -> listarProdutos();
                case 0 -> System.out.println("FLW VLW OBG VLT SMP.");
            }
            pausa();
        }while (opcao != 0);
        teclado.close();
    }
}