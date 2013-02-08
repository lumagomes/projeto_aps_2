package negocio;

import java.util.ArrayList;
import java.util.List;

public class Tratamento {

	private String tipo;
	private List<Funcionario> especialistas;
	
	public Tratamento(String tipo, Funcionario f){
		especialistas = new ArrayList<Funcionario>();
		this.tipo = tipo;
		especialistas.add(f);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Funcionario> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(List<Funcionario> especialistas) {
		this.especialistas = especialistas;
	}

}
