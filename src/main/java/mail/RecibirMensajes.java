package mail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import entidades.Mensaje;

public class RecibirMensajes {

	static ArrayList<Mensaje> listaDeMensajes = null;

	public static ArrayList<Mensaje> recibir() {

		listaDeMensajes = new ArrayList<Mensaje>();

		// Se obtiene la Session
		Properties propiedades = new Properties();
		propiedades.setProperty("mail.pop3.starttls.enable", "false");
		propiedades.setProperty("mail.pop3.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		propiedades.setProperty("mail.pop3.socketFactory.fallback",
				basics.Constantes.FALLBACK);
		propiedades.setProperty("mail.pop3.port", basics.Constantes.PUERTO_POP);
		propiedades.setProperty("mail.pop3.socketFactory.port",
				basics.Constantes.PUERTO_POP);

		Session sesion = Session.getInstance(propiedades);
		// sesion.setDebug(true);

		try {
			// Se obtiene el Store y el Folder, para poder leer el correo.
			Store store = sesion.getStore(basics.Constantes.PROTOCOLO_POP3);
			store.connect(basics.Constantes.SERVIDOR_POP,
					basics.Constantes.CUENTA, basics.Constantes.PASSWORD);

			Folder carpeta = store.getFolder("INBOX");
			carpeta.open(Folder.READ_ONLY);

			// Se obtienen los mensajes.
			Message[] mensajes = carpeta.getMessages();
			Mensaje mensaje = null;

			// Se escribe from y subject de cada mensaje
			for (int i = 0; i < mensajes.length; i++) {
				mensaje = new Mensaje();

				// Analizo el asunto
				if (mensajes[i].getSubject() == null
						|| mensajes[i].getSubject().isEmpty()) {
					mensaje.setAsunto("(Sin asunto)");
				} else {
					mensaje.setAsunto(mensajes[i].getSubject().toString());
				}

				// Analizo el "De"
				mensaje.setDe(mensajes[i].getFrom()[0].toString());
				System.out
						.println("De: " + mensajes[i].getFrom()[0].toString());

				// Seteo los receptoresTO
				String receptoresTO = "";
				for (int j = 0; j < mensajes[i]
						.getRecipients(Message.RecipientType.TO).length; j++) {
					String direccion = mensajes[i]
							.getRecipients(Message.RecipientType.TO)[j]
							.toString();
					System.out.println(direccion);
					if (j > 0) {
						receptoresTO = receptoresTO + ", ";
					}
					receptoresTO = receptoresTO + direccion;
				}
				System.out.println("receptoresTO: " + receptoresTO);
				mensaje.setPara(receptoresTO);

				// Seteo los receptoresCC
				String receptoresCC = "";
				if (mensajes[i].getRecipients(Message.RecipientType.CC) != null) {
					for (int j = 0; j < mensajes[i]
							.getRecipients(Message.RecipientType.CC).length; j++) {
						String direccion = mensajes[i]
								.getRecipients(Message.RecipientType.CC)[j]
								.toString();
						System.out.println(direccion);

						if (j > 0) {
							receptoresCC = receptoresCC + ", ";
						}

						receptoresCC = receptoresCC + direccion;
						System.out.println(receptoresCC);
					}
				}
				System.out.println("receptoresCC: " + receptoresCC);
				mensaje.setCc(receptoresCC);

				// Seteo los receptoresCCO
				String receptoresCCO = "";
				if (mensajes[i].getRecipients(Message.RecipientType.BCC) != null) {
					for (int j = 0; j < mensajes[i]
							.getRecipients(Message.RecipientType.BCC).length; j++) {
						String direccion = mensajes[i]
								.getRecipients(Message.RecipientType.BCC)[j]
								.toString();
						System.out.println(direccion);
						if (j > 0) {
							receptoresCCO = receptoresCCO + ", ";
						}
						receptoresCCO = receptoresCCO + direccion;
						System.out.println(receptoresCCO);
					}
				}
				System.out.println("receptoresCCO: " + receptoresCCO);
				mensaje.setCco(receptoresCCO);

				// Analizo el asunto
				System.out.println("Asunto: " + mensajes[i].getSubject());

				// Se visualiza, si se sabe como, el contenido de cada mensaje
				analizaParteDeMensaje(mensajes[i], mensaje);

				listaDeMensajes.add(mensaje);
			}

			carpeta.close(false);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaDeMensajes;
	}

	private static void analizaParteDeMensaje(Part pUnaParte, Mensaje pMensaje) {
		try {
			System.out.println("entra\n");

			// Si es multipart, se analiza cada una de sus partes
			// recursivamente.
			if (pUnaParte.isMimeType("multipart/*")) {
				System.out.println("Es un mensaje MultiPart\n");
				Multipart multi;
				multi = (Multipart) pUnaParte.getContent();

				for (int j = 0; j < multi.getCount(); j++) {
					analizaParteDeMensaje(multi.getBodyPart(j), pMensaje);
				}

			} else {
				System.out.println("Es un mensaje de Simple\n");

				/* Si es texto, se escribe el texto. */
				if (pUnaParte.isMimeType("text/*")) {
					System.out.println("Texto " + pUnaParte.getContentType());
					System.out.println(pUnaParte.getContent());

					pMensaje.setTexto((String) pUnaParte.getContent());

					System.out.println("---------------------------------");
				} else {
					// Si es imagen, se guarda en fichero y se visualiza en
					// JFrame
					if (pUnaParte.isMimeType("image/*")) {
						System.out.println("Imagen "
								+ pUnaParte.getContentType());
						System.out
								.println("Fichero=" + pUnaParte.getFileName());
						System.out.println("---------------------------------");

						guardarImagenEnArchivo(pUnaParte);
						visualizarImagenEnJFrame(pUnaParte);
					} else {
						// Si no es ninguna de las anteriores, se escribe en
						// pantalla el tipo.
						System.out.println("Recibido "
								+ pUnaParte.getContentType());
						System.out.println("---------------------------------");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void visualizarImagenEnJFrame(Part pUnaParte)
			throws IOException, MessagingException {
		JFrame v = new JFrame();
		ImageIcon icono = new ImageIcon(
				ImageIO.read(pUnaParte.getInputStream()));
		JLabel label = new JLabel(icono);
		v.getContentPane().add(label);
		v.pack();
		v.setVisible(true);
	}

	private static void guardarImagenEnArchivo(Part pUnaParte)
			throws FileNotFoundException, MessagingException, IOException {
		FileOutputStream fichero = new FileOutputStream("c:\\"
				+ pUnaParte.getFileName());
		InputStream imagen = pUnaParte.getInputStream();
		byte[] bytes = new byte[1000];
		int leidos = 0;

		while ((leidos = imagen.read(bytes)) > 0) {
			fichero.write(bytes, 0, leidos);
		}
	}
}
