package gerenciadores;

import java.util.Date;

import negocio.Cargo;
import negocio.Endereco;
import negocio.Estados;
import negocio.Funcionario;
import negocio.Pessoa;
import negocio.Tipo;

public class Tentando {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClinicaFachada c = new ClinicaFachada();
		
		Cargo cc = new Cargo("Teste", Tipo.Estagiário, 100, 8);
		c.addCargo(cc);
		Cargo cc1 = new Cargo("Teste 2", Tipo.Estagiário, 100, 8);
		c.addCargo(cc1);
		
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano Segundo", "111.222.333-11", new Date(),"1111-1111", e);
		
		Funcionario f = new Funcionario(p, cc);
		c.addFuncionario(f);
		System.out.println(f.getCargo());
		
		//c.editarFuncionario("111.222.333-11", cc1);
		
		System.out.println(f.getCargo());


	}

}
