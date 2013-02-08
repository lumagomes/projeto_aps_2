package testes;

import java.util.ArrayList;
import java.util.List;

import excecao.Excecao;
import gerenciadores.ClinicaFachada;
import negocio.Cargo;
import negocio.Tipo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestCargo {

	ClinicaFachada c;
	Cargo cargo;

	@Before
	public void instanciarFachada(){
		c = new ClinicaFachada();
		cargo = new Cargo ("Cargo 1", Tipo.Estagiário, 800, 8);
		c.addCargo(cargo);
	}
	
	@After
	public void limparCargos(){
		c.listarCargos().clear();
	}
	
	@Test
	public void adicionarCargo(){
		//Já foi adicionado um Cargo no Before
		Assert.assertEquals(cargo, c.listarCargos().get(0));
	}
	
	@Test
	public void addMesmoCargoTipoDiferente(){
		Cargo c2 = new Cargo ("Cargo 1", Tipo.Efetivo, 800, 8);
		Assert.assertTrue(c.addCargo(c2));
	}
	
	@Test
	public void addMesmoCargoTipoIgual(){
		Cargo c2 = new Cargo ("Cargo 1", Tipo.Estagiário, 800, 8);
		Assert.assertFalse(c.addCargo(c2));
	}
	
	@Test(expected = Excecao.class)
	public void addCampoEmBranco(){
		Cargo c2 = new Cargo ("", null , 0, 0);
		c.addCargo(c2);
	}
	
	@Test(expected = Excecao.class)
	public void addCargoFuncaoInvalida(){
		Cargo c2 = new Cargo ("Teste!", Tipo.Estagiário, 800, 8);
		c.addCargo(c2);
	}
	
	@Test(expected = Excecao.class)
	public void addCargoComAcento(){
		Cargo c2 = new Cargo ("Cargô", Tipo.Efetivo, 600, 8);
		c.addCargo(c2);
	}
	
	@Test
	public void addCargoCargaHorariaNegativa(){
		Cargo c2 = new Cargo("Cargo", Tipo.Efetivo, 600, -1);
		Assert.assertFalse(c.addCargo(c2));
	}
	
	@Test
	public void addCargoSalarioNegativo(){
		Cargo c2 = new Cargo("Cargo", Tipo.Efetivo, -1, 8);
		Assert.assertFalse(c.addCargo(c2));
	}
	
	@Test
	public void addCargoComTipoNulo(){
		Cargo c2 = new Cargo("Cargo", null, 600, 8);
		Assert.assertFalse(c.addCargo(c2));
	}
	
	@Test
	public void buscarCargoPelaFuncao(){
		Assert.assertNotNull("Um cargo foi encontrado", c.buscarCargoPelaFuncao("Teste"));
	}
	
	@Test
	public void buscarCargoPeloTipo(){
		Assert.assertNotNull("Um cargo foi encontrado", c.buscarCargoPeloTipo(Tipo.Estagiário));
	}
	
	@Test
	public void buscarMaisDeUmCargoExistente(){
		Cargo c2 = new Cargo("Cargo 1", Tipo.Efetivo, 500, 8);
		c.addCargo(c2);
		Assert.assertEquals(2, c.buscarCargoPelaFuncao("Cargo 1").size());
	}
	
	@Test
	public void buscarPorUmCargoInexistente(){
		Assert.assertEquals(0, c.buscarCargoPelaFuncao("Teste1").size());
	}
	
	@Test
	public void removerCargoExistente(){
		c.removerCargo(c.buscaAvancada("Cargo 1", Tipo.Estagiário));
		Assert.assertEquals(0, c.listarCargos().size());
	}
	
	@Test(expected = Excecao.class)
	public void removerCargoInexistente(){
		Cargo cargoParaRemover = new Cargo("TestRemove",Tipo.Efetivo, 800, 20);
		c.removerCargo(cargoParaRemover);
	}
	
	@Test
	public void editarCargoExistente(){
		List<Cargo> antigoCargo = new ArrayList<Cargo>();
		antigoCargo = c.buscarCargoPelaFuncao("Cargo 1");
		Cargo novoCargo = new Cargo("Teste1", Tipo.Efetivo, 800, 8);
		int indiceDoCargoAntigo = c.listarCargos().indexOf(antigoCargo.get(0));
		Assert.assertTrue("Cargo foi editado", c.editarCargo(antigoCargo.get(0), novoCargo));
		Assert.assertEquals(novoCargo, c.listarCargos().get(indiceDoCargoAntigo));
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoInexistente(){
		Cargo antigoCargoQueNaoExisteMais = new Cargo("CargoInexistente", Tipo.Efetivo, 800, 8);
		Cargo novoCargo = new Cargo("novoCargo", Tipo.Efetivo, 800, 8);
		c.editarCargo(antigoCargoQueNaoExisteMais, novoCargo);
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoComNomeDaFuncaoInvalida(){
		List<Cargo> antigoCargo = new ArrayList<Cargo>();
		antigoCargo = c.buscarCargoPelaFuncao("Cargo 1");
		Cargo novoCargo = new Cargo("Teste!", Tipo.Efetivo, 800, 8);
		c.editarCargo(antigoCargo.get(0), novoCargo);
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoPraSerIgualExistente(){
		Cargo c2 = new Cargo("Cargo 2", Tipo.Efetivo, 200, 5);
		c.addCargo(c2);
		c.editarCargo(c.buscaAvancada("Cargo 2", Tipo.Efetivo), new Cargo("Cargo 1", Tipo.Estagiário, 800, 8));
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoComTipoNulo(){
		Cargo c2 = new Cargo("Cargo 2", Tipo.Efetivo, 200, 5);
		c.addCargo(c2);
		c.editarCargo(c.buscaAvancada("Cargo 2", Tipo.Efetivo), new Cargo("Cargo 4", null, 800, 8));
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoComSalarioMenorQueZero(){
		Cargo c2 = new Cargo("Cargo 2", Tipo.Efetivo, 200, 5);
		c.addCargo(c2);
		c.editarCargo(c.buscaAvancada("Cargo 2", Tipo.Efetivo), new Cargo("Cargo 3", Tipo.Efetivo, -1 , 8));
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoComCargaHorariaMenorQueZero(){
		Cargo c2 = new Cargo("Cargo 2", Tipo.Efetivo, 200, 5);
		c.addCargo(c2);
		c.editarCargo(c.buscaAvancada("Cargo 2", Tipo.Efetivo), new Cargo("Cargo 3", Tipo.Efetivo, 700 , -1));
	}
}