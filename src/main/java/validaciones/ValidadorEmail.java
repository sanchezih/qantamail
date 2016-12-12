package validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorEmail {

	private Pattern patron;
	private Matcher matcher;

	public ValidadorEmail() {
		patron = Pattern.compile(basics.Constantes.EMAIL_PATTERN);
	}

	public boolean validar(final String direccion) {

		matcher = patron.matcher(direccion);
		return matcher.matches();

	}
}