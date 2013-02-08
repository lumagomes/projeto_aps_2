package gerenciadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import negocio.Endereco;

import excecao.Excecao;

public class GerenciadorEndereco {
		
	public void validate(Endereco endereco) throws Excecao{
		Pattern padrao1 = Pattern.compile("[0-9]{2}\\.[0-9]{3}-[0-9]{3}");
		Pattern padrao2 = Pattern.compile("[0-9]+");
		Pattern padrao3 = Pattern.compile("[A-Za-z0-9αγκικνξστυϊϋ]+");
		Matcher pesquisa = padrao1.matcher(endereco.getCep());

		if(!pesquisa.matches())
			throw new Excecao(Excecao.ENDERECO_CEP_INVALIDO);

		pesquisa = padrao2.matcher(endereco.getNumero());

		if(!endereco.getNumero().equals("S/N")){
			if(!pesquisa.matches()){
				throw new Excecao(Excecao.ENDERECO_NUMERO_INVALIDO);
			}
		}

		pesquisa = padrao3.matcher(endereco.getRua());

		if(!pesquisa.matches())
			throw new Excecao(Excecao.ENDERECO_RUA_INVALIDO);

		pesquisa = padrao3.matcher(endereco.getBairro());

		if(!pesquisa.matches())
			throw new Excecao(Excecao.ENDERECO_BAIRRO_INVALIDO);

	}

}
