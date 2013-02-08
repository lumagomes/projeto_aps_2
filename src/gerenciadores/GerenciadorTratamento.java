package gerenciadores;

import java.util.ArrayList;

import negocio.Funcionario;
import negocio.Tratamento;

public class GerenciadorTratamento {

	private ArrayList<Tratamento> tratamentos;

	public GerenciadorTratamento (){
		tratamentos = new ArrayList<Tratamento>();
	}

	public ArrayList<Tratamento> getTratamentos(){
		return tratamentos;
	}

	public boolean addTratamento(Tratamento tratamento, Funcionario f) {
		boolean retorno = false;
		if(!this.verificarExistencia(tratamento.getTipo())){
			tratamentos.add(tratamento);
			retorno = true;
		}else 
			if (!this.verificarExistenciaFunc(f)){
				tratamento.getEspecialistas().add(f);
				retorno = true;
			}
		return retorno;		
	}

	public boolean verificarExistencia(String tipo){
		for(Tratamento t:tratamentos){
			if(t.getTipo().equals(tipo)) return true;
		}	
		return false;
	}

	public boolean verificarExistenciaFunc(Funcionario f){
		for (Tratamento t : tratamentos) {
			if (t.getEspecialistas().equals(f)) return true;
		}
		return false;
	}

}
