package negocio;

public class Paciente {
	
	private Pessoa pessoa;
	
	public Paciente(Pessoa pessoa){
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}	

}
