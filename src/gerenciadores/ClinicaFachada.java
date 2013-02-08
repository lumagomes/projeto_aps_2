package gerenciadores;

import java.util.List;

import negocio.Cargo;
import negocio.Funcionario;
import negocio.Paciente;
import negocio.Plano;
import negocio.Tipo;
import excecao.Excecao;


public class ClinicaFachada {

	private GerenciadorCargo gc;
	private GerenciadorFuncionario gf;
	private GerenciadorPaciente gpa;
	private GerenciadorPlano gpl;

	public ClinicaFachada() {
		gc = new GerenciadorCargo();
		gf = new GerenciadorFuncionario();
		gpa = new GerenciadorPaciente();
		gpl = new GerenciadorPlano();
	}
	
	//Metodos de Cargo
	public boolean addCargo(Cargo c){
		return gc.addCargo(c);
	}

	public boolean removerCargo(Cargo cargoParaRemover) throws Excecao {
		return gc.removerCargo(cargoParaRemover);
	}

	public boolean editarCargo(Cargo antigo, Cargo novo) throws Excecao {
		return gc.editarCargo(antigo, novo);
	}

	public List<Cargo> buscarCargoPelaFuncao(String funcao) {
		return gc.buscarCargoPelaFuncao(funcao);
	}
	
	public List<Cargo> buscarCargoPeloTipo(Tipo tipo) {
		return gc.buscarCargoPeloTipo(tipo);
	}
	
	public Cargo buscaAvancada(String funcao, Tipo tipo){
		return gc.buscaAvancada(funcao, tipo);
	}
	
	public List<Cargo> listarCargos (){
		return gc.getCargos();
	}

	//Metodos de Funcionario
	public boolean addFuncionario(Funcionario f) {
		return gf.addFuncionario(f);		
	}
	
	public List<Funcionario> listarFuncionarios(){
		return gf.getFuncionarios();
	}
	
	public Funcionario buscarFuncionarioPeloCpf(String cpf) {
		return gf.buscarPeloCpf(cpf);
	}

	public List<Funcionario> buscarFuncionarioPeloNome(String nome) {
		return gf.buscarPeloNome(nome);
	}
	
	public boolean editarCargoFuncionario(Funcionario f, Cargo c) {		
		return gf.editarCargoFuncionario(f, c);
	}
	
	public boolean editarFuncionario(Funcionario antigo, Funcionario novo) {
		return gf.editarFuncionario(antigo, novo);		
	}
	
	public boolean removerFuncionario(Funcionario f) {
		return gf.removerFuncionario(f);
	}
	
	//Metodos de Paciente
	public void addPaciente(Paciente p) {
		gpa.addPaciente(p);		
	}
	
	public List<Paciente> listarPacientes(){
		return gpa.getPacientes();
	}

	//Metodos de Plano
	public boolean addPlano(Plano plano) {
		return gpl.addPlano(plano);
		
	}

	public List<Plano> listarPlanos() {
		return gpl.getPlanos();
	}

	public Plano buscarPlanoPeloCodigo(String cod) {
		return gpl.buscarPlanoPeloCodigo(cod);
	}

	public Plano buscarPlanoPeloNome(String nome) {
		return gpl.buscarPlanoPeloNome(nome);
	}

	public boolean editarPlano(Plano antigo, Plano novo) {
		return gpl.editarPlano(antigo, novo);
	}

	public boolean removerPlano(Plano plano) {
		return gpl.removerPlano(plano);
	}
	
}
