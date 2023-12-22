package ufpb.disciplina.api.lap3.excecoes;

public class UsuarioInvalidoException extends IllegalArgumentException {
	

	public UsuarioInvalidoException(String s) {
        super(s);
    }

    public UsuarioInvalidoException() {
        super();
    }

}
