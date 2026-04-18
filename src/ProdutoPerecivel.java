<<<<<<< HEAD
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto{

	/** Desconto para proximidade de validade: 25% */
	private static final double DESCONTO = 0.25;
	
	/** Prazo, em dias, para conceder o desconto por proximidade da validade */
	private static final int PRAZO_DESCONTO = 7;
	
	/** Data de validade do produto. Não pode ser anterior à data da criação ou venda do produto. */
	private LocalDate dataDeValidade;
	
	/**
     * Construtor completo. 
     * Causa exceção em caso de valores inválidos para os dados do produto.
     * @param desc Descrição do produto (mínimo de 3 caracteres)
=======

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/** 
 * MIT License
 *
 * Copyright(c) 2025 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
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

public class ProdutoPerecivel extends Produto{
    /** Desconto para proximidade de validade: 25% */
    private static final double DESCONTO = 0.25;
    
    /** Prazo, em dias, para conceder o desconto por proximidade da validade */
    private static final int PRAZO_DESCONTO = 7;
    
    /** Data de validade do produto. Não pode ser anterior à data da criação ou venda */
    private LocalDate dataDeValidade;

    /**
     * Construtor completo. 
     * Causa exceção em caso de valores inválidos
     * @param desc Descrição do produto (mínimo 3 caracteres)
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
     * @param precoCusto Preço de compra do produto (mínimo 0.01)
     * @param margemLucro Margem de lucro para a venda (mínimo 0.01)
     * @param validade Data de validade do produto, que deve ser posterior à data atual.
     * @throws IllegalArgumentException em caso dos limites acima serem desrespeitados.
     */
<<<<<<< HEAD
	public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade) {
		
		super(desc, precoCusto, margemLucro);
		
		if (validade.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Data de validade do produto é anterior ao dia de hoje!");
		}
		dataDeValidade = validade;
	}
	
	/**
     * Construtor do produto com margem de lucro padrão (20%). Causa exceção em caso de valores inválidos para os dados do produto.
     * @param desc Descrição do produto (mínimo de 3 caracteres)
     * @param precoCusto Preço de compra do produto (mínimo 0.01)
     * @param validade Data de validade do produto, que deve ser posterior à data atual.
     * @throws IllegalArgumentException em caso dos limites acima serem desrespeitados.
     */
	public ProdutoPerecivel(String desc, double precoCusto, LocalDate validade) {
		
		super(desc, precoCusto);
		
		if (validade.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Data de validade do produto é anterior ao dia de hoje!");
		}
		dataDeValidade = validade;
	}

	/**
=======
    public ProdutoPerecivel(String descricao, double precoCusto, double margemLucro, LocalDate validade){
        super(descricao, precoCusto, margemLucro);
        if(validade.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Validade anterior ao dia de hoje!");
        dataDeValidade = validade;
    }

    /**
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
     * Retorna o valor de venda do produto, considerando seu preço de custo, margem de lucro e
     * dias de validade. Se o prazo de validade estiver a menos de 7 dias, será concedido desconto de 25%.
     * @return Valor de venda do produto (double, positivo)
     */
<<<<<<< HEAD
	@Override
	public double valorDeVenda() {
		
		double precoVenda;
		
		if (dataDeValidade.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Data de validade do produto é anterior ao dia de hoje!");
		}
		
		precoVenda = (precoCusto * (1.0 + margemLucro));
				
		if (LocalDate.now().until(dataDeValidade).getDays() <= PRAZO_DESCONTO) {
			precoVenda = precoVenda * (1.0 - DESCONTO);
		}
		
		return precoVenda;
	}
	
	/**
     * Descrição, em string, do produto, contendo sua descrição, o valor de venda e sua data de validade.
=======
    @Override
    public double valorDeVenda() {
        double desconto = 0d;
        long diasValidade = LocalDate.now().until(dataDeValidade,ChronoUnit.DAYS);
        if(diasValidade<=PRAZO_DESCONTO)
            desconto = DESCONTO;
        return (precoCusto * (1+margemLucro)) * (1-desconto);
    }

    /**
     * Descrição em string do produto, contendo sua descrição, o valor de venda e data de validade.
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
     *  @return String com o formato:
     * [NOME]: R$ [VALOR DE VENDA]
     * Válido até [DD/MM/YYYY]
     */
    @Override
    public String toString(){
<<<<<<< HEAD
    	
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String dados = super.toString();
        dados += "\nVálido até " + formato.format(dataDeValidade);
        
        return dados;
    }
    
    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    /**
     * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro são formatados com 2 casas decimais.
     * Data de validade é formatada no formato dd/mm/aaaa
     * @return Uma string no formato "2;descrição;preçoDeCusto;margemDeLucro;dataDeValidade"
     */
	@Override
    public String gerarDadosTexto() {
    
		String precoCustoFormatado = String.format("%.2f", precoCusto).replaceAll(",", ".");
		String margemLucroFormatada = String.format("%.2f", margemLucro).replaceAll(",", ".");
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = formatoData.format(dataDeValidade);
		
		return String.format("2;%s;%s;%s;%s", descricao, precoCustoFormatado, margemLucroFormatada, dataFormatada);
		
	}
=======
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String dados = super.toString();
        dados += " - Válido até "+formato.format(dataDeValidade);
        return dados;
    }

    /**
     * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro vão formatados com 2 casas decimais.
     * Data de validade vai no formato dd/mm/aaaa
     * @return Uma string no formato "2; descrição;preçoDeCusto;margemDeLucro;dataDeValidade"
     */
    @Override
    public String gerarDadosTexto() {
        DateTimeFormatter formatador =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = formatador.format(dataDeValidade);
        
        return String.format("2;%s;%.2f;%.2f;%s", 
                descricao, precoCusto, margemLucro, dataFormatada);

    }        
    
>>>>>>> 7603c795dc92eee47ef8b529990c311c36f6ff2b
}
