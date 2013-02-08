package testes;

import excecao.Excecao;
import gerenciadores.ClinicaFachada;
import junit.framework.Assert;
import negocio.Plano;

import org.junit.Before;
import org.junit.Test;

public class TestPlano {
	
	ClinicaFachada clinica;
	Plano plano;
	
	@Before
	public void instanciarFachada(){
		clinica = new ClinicaFachada();
		plano = new Plano("SuperPlus", 30.00, "1234");
		clinica.addPlano(plano);
	}
	
	@Test
	public void addPlano(){
		Assert.assertEquals(plano, clinica.listarPlanos().get(0));
	}
	
	@Test
	public void addPlanoComCamposNulo(){
		Plano p = new Plano ("", 0, "");
		Assert.assertFalse(clinica.addPlano(p));
	}
	
	@Test
	public void addPlanoComValorNegativo(){
		Plano p = new Plano("Familia", -2, "123");
		Assert.assertFalse(clinica.addPlano(p));
	}
	
	@Test(expected = Excecao.class)
	public void addPlanoComCodigoExistente(){
		Plano p = new Plano("Familia", 100, "1234");
		clinica.addPlano(p);
	}
	
	@Test(expected = Excecao.class)
	public void addPlanoComNomeExistente(){
		Plano p = new Plano("Superplus", 100, "123");
		clinica.addPlano(p);
	}
	
	@Test(expected = Excecao.class)
	public void addPlanoComCaracteresCodigoAcimaDoLimite(){
		Plano p = new Plano("Familia", 20.0, "123456789");
		clinica.addPlano(p);
	}
	
	@Test(expected = Excecao.class)
	public void addPlanoComCodigoInvalido(){
		Plano p = new Plano("Familia", 100.0, "aa");
		clinica.addPlano(p);
	}
	
	@Test
	public void buscarPlanoPeloCodigo(){
		Assert.assertNotNull(clinica.buscarPlanoPeloCodigo("1234"));
	}
	
	@Test
	public void buscarPlanoPeloNome(){
		Assert.assertNotNull(clinica.buscarPlanoPeloNome("Superplus"));
	}
	
	@Test
	public void buscarPeloCodigoPlanoInexistente(){
		Assert.assertNull(clinica.buscarPlanoPeloCodigo("123"));
	}
	
	@Test
	public void buscarPeloNomePlanoInexistente(){
		Assert.assertNull(clinica.buscarPlanoPeloNome("Familia"));
	}
	
	@Test
	public void editarPlano(){
		Plano p = new Plano("Familia", 100.0, "123");
		Assert.assertTrue(clinica.editarPlano(plano, p));
	}
	
	@Test(expected = Excecao.class)
	public void editarPlanoInexistente(){
		Plano p = new Plano("Familia", 100.0, "123");
		clinica.editarPlano(p, plano);
	}
	
	@Test
	public void removerPlano(){
		Assert.assertTrue(clinica.removerPlano(plano));
	}
	
	@Test(expected = Excecao.class)
	public void removerPlanoInexistente(){
		Plano p = new Plano("Familia", 100.0, "123");
		clinica.removerPlano(p);
	}

}
