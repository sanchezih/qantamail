package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entidades.Mensaje;

public class EnviarMensaje {

	public void EnviarMensaje(Mensaje pMensaje) {

		try {
			// Propiedades de la conexion
			Properties propiedades = new Properties();
			propiedades.setProperty("mail.smtp.host", basics.Constantes.SERVIDOR_SMTP);
			propiedades.setProperty("mail.smtp.starttls.enable", "true");
			propiedades.setProperty("mail.smtp.port", basics.Constantes.PUERTO_SMTP);
			propiedades.setProperty("mail.smtp.user", basics.Constantes.CUENTA);
			propiedades.setProperty("mail.smtp.auth", basics.Constantes.AUTENTICACION_SMTP);

			// Armo la sesion
			Session sesion = Session.getDefaultInstance(propiedades);

			// Armo el mensaje
			MimeMessage mensajeMime = new MimeMessage(sesion);
			mensajeMime.setFrom(new InternetAddress(basics.Constantes.CUENTA));

			mensajeMime.addRecipients(Message.RecipientType.TO, pMensaje.getPara());
			mensajeMime.addRecipients(Message.RecipientType.CC, pMensaje.getCc());
			mensajeMime.addRecipients(Message.RecipientType.BCC, pMensaje.getCco());

			mensajeMime.setSubject(pMensaje.getAsunto());
			mensajeMime.setText(pMensaje.getTexto());

			// Envio
			Transport transporte = sesion.getTransport(basics.Constantes.PROTOCOLO_SMTP);
			transporte.connect(basics.Constantes.CUENTA, basics.Constantes.PASSWORD);
			transporte.sendMessage(mensajeMime, mensajeMime.getAllRecipients());

			// Cierro
			transporte.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
