package gerenciadores;

import java.util.ArrayList;

import excecao.Excecao;

import negocio.Paciente;

public class GerenciadorPaciente {
	
	private ArrayList<Paciente> pacientes;
	
	public GerenciadorPaciente(){
		pacientes = new ArrayList<Paciente>();
	}
	
	//Os metodos desta classe ainda serao trabalhados. Foram feitos apenas para ajudar nos testes de funcionario
	public void addPaciente(Paciente p){
		//falta colocar as validacoes
		pacientes.add(p);
	}
	
	public boolean removerFuncionario(Paciente p) {
		int numeroDeRegistrosAntes = getPacientes().size();
		getPacientes().remove(p);
		if (getPacientes().size() == numeroDeRegistrosAntes) {
			throw new Excecao(Excecao.PACIENTE_INEXISTENTE);
		} else
			return true;
	}
	
	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public boolean verificarExistencia(String cpf){
		for (Paciente p : pacientes) {
			if(p.getPessoa().getCpf().equals(cpf)){
				pacientes.remove(p);
				return true;
			}
		}return false;
	}

}
