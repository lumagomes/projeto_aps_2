package testes;

import excecao.Excecao;
import gerenciadores.ClinicaFachada;

import java.util.Date;

import junit.framework.Assert;
import negocio.Cargo;
import negocio.Endereco;
import negocio.Estados;
import negocio.Funcionario;
import negocio.Paciente;
import negocio.Pessoa;
import negocio.Tipo;

import org.junit.Before;
import org.junit.Test;

public class TestFuncionario {
	
	ClinicaFachada clinica;
	Funcionario funcionario;
	Cargo cargo;
	Endereco endereco;
	Pessoa pessoa;
	
	@Before
	public void instanciarFachada(){
		clinica = new ClinicaFachada();
		cargo = new Cargo("Cargo 1", Tipo.Efetivo, 1000, 8);
		endereco = new Endereco("Rua", "bairro", Estados.PARAIBA, "55.999-999", "11");
		pessoa = new Pessoa("Fulano", "111.222.333-00", new Date(),"9999-9999", endereco);
		funcionario = new Funcionario(pessoa, cargo);
		clinica.addFuncionario(funcionario);
	}
	
	@Test
	public void addFuncionario(){
		Assert.assertEquals(funcionario, clinica.listarFuncionarios().get(0));
	}
	
	@Test(expected = Excecao.class)
	public void addFuncionarioSemCargo(){
		Pessoa p = new Pessoa("Cicrano", "111.222.333-44", new Date(),"9999-9999", endereco);
		Funcionario f = new Funcionario(p, null);
		clinica.addFuncionario(f);
	}
	
	@Test
	public void addFuncionarioExistente(){
		Funcionario f = new Funcionario(pessoa, cargo);
		Assert.assertFalse(clinica.addFuncionario(f));
	}
	
	@Test
	public void addFuncionarioQueJaEhPaciente(){
		Pessoa p = new Pessoa("Cicrano", "111.222.333-44", new Date(),"9999-9999", endereco);
		Paciente pa = new Paciente(p);
		clinica.addPaciente(pa);
		Funcionario f = new Funcionario(pa.getPessoa(), cargo);
		Assert.assertTrue(clinica.addFuncionario(f));
	}
	
	@Test
	public void buscarFuncionarioPeloCpf(){
		Assert.assertNotNull(clinica.buscarFuncionarioPeloCpf("111.222.333-00"));
	}
	
	@Test
	public void buscarFuncionarioInexistente(){
		Assert.assertNull(clinica.buscarFuncionarioPeloCpf("111.222.333-44"));
	}
	
	@Test
	public void buscarFuncionarioPeloNome(){
		Assert.assertEquals(1, clinica.buscarFuncionarioPeloNome("Fulano").size());
	}
	
	@Test
	public void buscarPeloNomeFuncionarioInexistente(){
		Assert.assertEquals(0, clinica.buscarFuncionarioPeloNome("Cicrano").size());
	}
	
	@Test
	public void buscarMaisDeUmFuncionarioExistente(){
		Pessoa p = new Pessoa("Fulano", "111.222.333-44", new Date(),"9999-9999", endereco);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
		Assert.assertEquals(2, clinica.buscarFuncionarioPeloNome("Fulano").size());
	}
	
	@Test
	public void editarFuncionario() {
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "111.111.111-11", new Date() ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		Assert.assertTrue(clinica.editarFuncionario(funcionario, f));
	}
	
	@Test
	public void editarCargoDoFuncionario(){
		Cargo c = new Cargo("Cargo 2", Tipo.Estagiário, 800.0, 6);
		clinica.addCargo(c);
		Assert.assertTrue(clinica.editarCargoFuncionario(funcionario, c));
	}
	
	@Test (expected = Excecao.class)
	public void editarCargoFuncionarioInexistente(){
		Funcionario f = new Funcionario(pessoa, cargo);
		Cargo c = new Cargo("Cargo 2", Tipo.Estagiário, 800.0, 6);
		clinica.addCargo(c);
		clinica.editarCargoFuncionario(f, c);
	}
	
	@Test
	public void editarFuncionarioParaCargoNulo(){
		Assert.assertFalse(clinica.editarCargoFuncionario(funcionario, null));
		
	}
	
	@Test
	public void removerFuncionario(){
		Assert.assertTrue(clinica.removerFuncionario(funcionario));
	}
	
	@Test (expected = Excecao.class)
	public void removerFuncionarioInexistente(){
		Funcionario f = new Funcionario(pessoa, cargo);
		clinica.removerFuncionario(f);
	}

}
