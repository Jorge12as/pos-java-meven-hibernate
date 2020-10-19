package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity /*Transformando a classe TelefoneUser em uma tabela no banco*/
public class TelefoneUser {

	@Id //transformando o id em uma chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)//transformando o atributo tipo em uma coluna na base
	private String tipo;

	@Column(nullable = false)
	private String numero;

	//Criando o relacionamento de MUITOS PARA UM
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private UsuarioPessoa usuarioPessa;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public UsuarioPessoa getUsuarioPessa() {
		return usuarioPessa;
	}

	public void setUsuarioPessa(UsuarioPessoa usuarioPessa) {
		this.usuarioPessa = usuarioPessa;
	}

}
