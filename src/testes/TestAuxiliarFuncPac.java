package testes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import negocio.Cargo;
import negocio.Endereco;
import negocio.Estados;
import negocio.Funcionario;
import negocio.Pessoa;
import negocio.Tipo;

import org.junit.Before;
import org.junit.Test;

import excecao.Excecao;

import gerenciadores.ClinicaFachada;

public class TestAuxiliarFuncPac {
	
	SimpleDateFormat format;
	ClinicaFachada clinica;
	Pessoa pessoa;
	Endereco endereco;
	Cargo cargo;
	Funcionario funcionario;
	
	@Before
	public void instanciarFachada() throws ParseException{
		format = new SimpleDateFormat("dd/MM/yyyy");
		clinica = new ClinicaFachada();
		endereco = new Endereco("Rua", "bairro", Estados.PARAIBA, "55.999-999", "11");
		pessoa = new Pessoa("Fulano", "111.222.333-00", (Date)format.parse("01/02/1987") ,"9999-9999", endereco);
		cargo = new Cargo("Cargo 1", Tipo.Efetivo, 1000, 8);
		funcionario = new Funcionario(pessoa, cargo);
		clinica.addFuncionario(funcionario);
	}
	
	@Test(expected = Excecao.class)
	public void addPessoaCepInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "aa.aaa-aaa", "11");
		Pessoa p = new Pessoa("Fulano", "111.222.333-01", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addPessoaNumeroInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "///");
		Pessoa p = new Pessoa("Fulano", "111.222.333-08", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
	}
	
	@Test
	public void addPessoaEnderecoSemNumero() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "S/N");
		Pessoa p = new Pessoa("Fulano", "111.222.333-18", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		Assert.assertTrue(clinica.addFuncionario(f));
	}
	
	@Test(expected = Excecao.class)
	public void addCampoBairroInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "!@#$%¨&*", Estados.PARAIBA, "11.111-111", "S/N");
		Pessoa p = new Pessoa("Fulano", "111.222.333-18", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addCampoRuaInvalido() throws ParseException{
		Endereco e = new Endereco("!@#$%", "Bairro", Estados.PARAIBA, "11.111-111", "S/N");
		Pessoa p = new Pessoa("Fulano", "111.222.333-18", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
	}
	
	//A partir daqui talvez saia
	@Test(expected = Excecao.class)
	public void addPessoaCpfInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "aaa.aaa.aaa-aa", (Date)format.parse("01/02/1984"),"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addPessoaTelefoneInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "111.111.111-11", (Date)format.parse("01/02/1984") ,"aaaa-aaaa", e);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addPessoaDataNascimentoInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "111.111.111-33", (Date)format.parse("01/01/2014") ,"2222-2222", e);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
	}
	
	@Test
	public void addPessoaCpfExistente() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.222-333", "11");
		Pessoa p = new Pessoa("Fulano Dois", "111.222.333-00", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		Assert.assertFalse(clinica.addFuncionario(f));
	}
	
	@Test
	public void addPessoaComCamposObrigatoriosVazios() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.222-333", "11");
		Pessoa p = new Pessoa("", "", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		Assert.assertFalse(clinica.addFuncionario(f));
	}

	@Test(expected  = Excecao.class)
	public void editarPessoaComCpfInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "aaa.aaa.aaa-aa", (Date)format.parse("01/02/1984") ,"9999-9999", e);		
		Funcionario f = new Funcionario(p, cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test(expected  = Excecao.class)
	public void editarPessoaComCpfExistente() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano Segundo", "111.222.333-00", (Date)format.parse("01/02/1984"),"9999-9999", e);		
		Funcionario f = new Funcionario(p, cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test(expected = Excecao.class)
	public void editarPessoaEnderecoNumeroComLetra() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "aa");
		Pessoa p = new Pessoa("Cicrano", "111.222.333-38", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test(expected  = Excecao.class)
	public void editarPessoaComTelefoneInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano Segundo", "111.222.333-11", (Date)format.parse("01/02/1984"),"aaaa-aaaa", e);		
		Funcionario f = new Funcionario(p, cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test(expected  = Excecao.class)
	public void editarPessoaComDataNascimentoMaiorQueAnoAtual() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano Segundo", "111.222.333-11", (Date)format.parse("01/01/2014"),"1111-1111", e);		
		Funcionario f = new Funcionario(p, cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test
	public void editarPessoaComCamposObrigatoriosVazios() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.222-333", "11");
		Pessoa p = new Pessoa("", "", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		Assert.assertFalse(clinica.editarFuncionario(funcionario,f));
	}

}
