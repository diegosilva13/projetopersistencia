package persistence;

public class Gerente extends Funcionario {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
