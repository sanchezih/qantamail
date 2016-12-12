# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.16
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2012-02-14 10:30:41
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for qantamail
DROP DATABASE IF EXISTS `qantamail`;
CREATE DATABASE IF NOT EXISTS `qantamail` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `qantamail`;


# Dumping structure for table qantamail.contacto
DROP TABLE IF EXISTS `contacto`;
CREATE TABLE IF NOT EXISTS `contacto` (
  `id_contacto` int(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `direccion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id_contacto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table qantamail.contacto: ~11 rows (approximately)
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
INSERT INTO `contacto` (`id_contacto`, `nombre`, `apellido`, `email`, `telefono`, `direccion`) VALUES
	(1, 'Ignacio', 'Sanchez', 'sanchezih@gmail.com', '45420350', 'Correa 4634'),
	(4, 'Gabriel', 'Sanchez', 'eltiomorochon@yahoo.com.ar', '', ''),
	(5, 'Hugo', 'Sanchez', 'hugosanchez54@hotmail.com', '1569582360', ''),
	(6, 'Leandro', 'Antuna', 'leo_theone83@hotmail.com', '', 'Estomba 3990'),
	(7, 'Lorena', 'Perez', NULL, NULL, 'Santa Fe 4122'),
	(8, 'Mariela', 'Lopez', NULL, NULL, NULL),
	(9, 'Luis', 'Fernandez', '', '', ''),
	(10, 'Maria', 'Sanchez', NULL, NULL, NULL),
	(11, 'Victoria', 'Quiroga', 'victritri@hotmail.com', NULL, 'Colon 384'),
	(12, 'Sara', 'Quiroga', 'sarita.quiroga@gmail.com', NULL, 'Colon 384'),
	(13, 'Rosa', 'Tamburrino', 'rtamburrino@hotmail.com', '47550300', 'Calle 117 2501');
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;


# Dumping structure for table qantamail.mensaje
DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE IF NOT EXISTS `mensaje` (
  `id_mensaje` int(10) NOT NULL,
  `de` varchar(1000) DEFAULT NULL,
  `para` varchar(1000) DEFAULT NULL,
  `cc` varchar(1000) DEFAULT NULL,
  `cco` varchar(1000) DEFAULT NULL,
  `asunto` varchar(1000) DEFAULT NULL,
  `texto` text,
  `fecha` datetime DEFAULT NULL,
  `estado` varchar(120) DEFAULT NULL,
  `caracter` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table qantamail.mensaje: ~0 rows (approximately)
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
INSERT INTO `mensaje` (`id_mensaje`, `de`, `para`, `cc`, `cco`, `asunto`, `texto`, `fecha`, `estado`, `caracter`) VALUES
	(1, 'qantamail@gmail.com', 'sanchezih@gmail.com', '', '', 'Hola', 'Hola, como estas?', '2012-02-14 10:09:22', 'ENVIADO_OK', 'DE'),
	(2, 'Ignacio Sanchez <isanchez@ymail.com>', '"qantamail@gmail.com" <qantamail@gmail.com>', '', '', 'Lista de artistas', 'pericos y amigos\nbryan adams\nDaniela Mercury\nabba teens\nairbag\nsombras\nestopa\neric clapton\nbelinda\ngorilaz\nskid row\nwhitesnake\nKill for Thrills\npaul oakenfold\nyngwie malmsteen\nquiet riot\r\n', '2012-02-14 10:14:10', 'RECIBIDO_OK', 'PARA'),
	(3, 'Ignacio Sanchez <sanchezih@gmail.com>', 'qantamail@gmail.com', 'Rosa Tamburrino <rtamburrino@hotmail.com>', '', 'Re: Hola', 'Bien, y vos?<br><br><div class="gmail_quote">On Tue, Feb 14, 2012 at 10:10 AM,  <span dir="ltr">&lt;<a href="mailto:qantamail@gmail.com">qantamail@gmail.com</a>&gt;</span> wrote:<br><blockquote class="gmail_quote" style="margin:0 0 0 .8ex;border-left:1px #ccc solid;padding-left:1ex">\r\nHola, como estas?<br>\r\n</blockquote></div><br>\r\n', '2012-02-14 10:14:10', 'RECIBIDO_OK', 'PARA'),
	(4, 'qantamail@gmail.com', 'Ignacio Sanchez <isanchez@ymail.com>', '', '', 'RE: Lista de artistas', 'Buenisimo, gracias.\n\n-----Mensaje original-----\nDe: Ignacio Sanchez <isanchez@ymail.com>\nEnviado el: 2012-02-14 10:14:10.0\nPara: "qantamail@gmail.com" <qantamail@gmail.com>\nCC: \nAsunto: Lista de artistas\n\npericos y amigos\nbryan adams\nDaniela Mercury\nabba teens\nairbag\nsombras\nestopa\neric clapton\nbelinda\ngorilaz\nskid row\nwhitesnake\nKill for Thrills\npaul oakenfold\nyngwie malmsteen\nquiet riot\r\n', '2012-02-14 10:17:28', 'ENVIADO_OK', 'DE'),
	(5, 'qantamail@gmail.com', 'isanchez@ymail.com', '', '', 'Como te fue?', 'Como te fue en las vacaciones?', '2012-02-14 10:18:06', 'ENVIADO_OK', 'DE'),
	(6, 'qantamail@gmail.com', '', '', '', 'Un mensaje borrador', 'Un mensaje borrador', '2012-02-14 10:18:31', 'BORRADOR', 'DE'),
	(7, 'Ignacio Sanchez <sanchezih@gmail.com>', 'Rosa Tamburrino <rtamburrino@hotmail.com>', 'yo <isanchez@ymail.com>, qantamail@gmail.com', '', 'Steve Jobs fue investigado por el gobierno americano', 'Y la causa es que su nombre fué uno de los que mas sonaban para formar\r\nparte del gobierno de los Estados Unidos, concretamente en el puesto\r\nde consejero de comercio internacional.\r\n\r\nAl parecer no llegó a ocupar ese puesto porque las investigaciones de\r\nlos servicios de inteligencia desvelaron que Jobs tenía cierta\r\ntendencia a manipular la realidad para lograr sus objetivos. Además\r\nera testarudo, con mal carácter y demasiado ambicioso, algo que sin\r\nembargo, el informe reconoce como buenas cualidades para los negocios.\r\nTampoco pareció conveniente que formase parte del gobierno alguien que\r\nhabía consumido drogas durante su juventud.\r\n\r\nEntre las cualidades positivas se menciona que Jobs era una persona\r\ncon gran talento, muy trabajadora, creativa y capaz de hacer un\r\nservicio público.\r\n\r\nEl informe también desvela información tanto sobre sus padres, tanto\r\nbiológicos como adoptivos, y muestra la opinión, no siempre positiva,\r\nde gente que trabajó con él y que lo calificaban, sobre todo, como una\r\npersona compleja y algo manipuladora.\r\n\r\nEl informe fué realizado en 1991 y cuenta con 200 páginas... vamos,\r\nque un poco mas y hacen otro libro sobre Jobs.\r\n', '2012-02-14 10:18:40', 'RECIBIDO_OK', 'PARA'),
	(8, 'Ignacio Sanchez <isanchez@ymail.com>', '"sanchezih@gmail.com" <sanchezih@gmail.com>, "qantamail@gmail.com" <qantamail@gmail.com>', '"rtamburrino@hotmail.com" <rtamburrino@hotmail.com>', '', 'ClaroArgentina presenta: el BlackBerry Curve 9360', 'Buenos Aires, Argentina, febrero de 2012 – Claro, la compañía de comunicaciones móviles, presenta el nuevo teléfono BlackBerry® Curve™ 9360 en Argentina. El nuevo teléfono inteligente incluye, entre otras características, el nuevo y potente BlackBerry® 7 OS (sistema operativo), y ofrece las mejores experiencias sociales y de comunicación móvil del mundo con una suite integrada de teléfono, correo electrónico, mensajería y aplicaciones sociales para mantener a los usuarios conectados y productivos todo el día. \n\r\n', '2012-02-14 10:18:40', 'RECIBIDO_OK', 'PARA'),
	(9, 'Ignacio Sanchez <sanchezih@gmail.com>', 'qantamail@gmail.com, yo <isanchez@ymail.com>', '', '', 'Fwd: Test de idioma', 'Test de idioma\r\n\r\n---------- Forwarded message ----------\r\nFrom: UP Facultad de Ingeniería <inginf@palermo.edu>\r\nDate: 2012/2/8\r\nSubject: Test de idioma\r\nTo: sanchezih@gmail.com\r\n\r\nFacultad de Ingeniería\r\n\r\nEl Departamento de Inglés de la UP informa la fecha en que se tomará\r\nel Test de Nivel de este año 2012:\r\n\r\n\r\n              Jueves 1° de Marzo 12:00 hs   o bien\r\n\r\nJueves 1° de Marzo 17:00 hs\r\n\r\n\r\nEl test de nivel  no devengará aranceles.\r\n\r\nLa inscripción al test se realiza hasta 72 horas hábiles antes del\r\nexamen, en el Departamento de Alumnos, Cabrera 3507 o por e-mail a:\r\nalumnos@palermo.edu (confirmar recepción del mail)\r\n\r\n\r\n\r\nEl test de nivel se toma en la sede de Mario Bravo 1259.\r\n\r\n\r\n\r\nEl test de nivel se rinde una sola vez. Una vez rendido el test de\r\nnivel, el alumno deberá rendir el final del mayor nivel aprobado en el\r\ntest, en el turno y fecha de examen que el alumno elija. El final es\r\nescrito y oral. El alumno deberá cursar el o los niveles que no haya\r\naprobado en el test de nivel\r\n\r\nComienzo de las clases de inglés: 19 de Marzo.\r\n\r\nInscripción a los cursos: del 12 al 16 de Marzo a través del sistema de alumnos\r\n\r\nMayor información:\r\n\r\nDepartamento de Inglés\r\n\r\nFacultad de Ciencias Sociales\r\n\r\nMario Bravo 1259\r\n\r\ne-mail: idiomas@palermo.edu\r\n\r\n\r\nUNIVERSIDAD DE PALERMO / Facultad de Ingeniería\r\nCentros de Informes e Inscripción: Av. Córdoba 3501, esq. Mario Bravo\r\n| Av. Santa Fe esq. Larrea 1079. Ciudad de Buenos Aires - Argentina |\r\nTel: (5411) 4964-4600 | informes@palermo.edu\r\nwww.palermo.edu/ ingenieria\r\n', '2012-02-14 10:21:11', 'RECIBIDO_OK', 'PARA'),
	(10, 'Rosa Tamburrino <rtamburrino@hotmail.com>', 'qantamail@gmail.com', '', '', 'Examen Final de Principios de la Epidemiologia', '\r\nExamen Final de Principios de la Epidemiología\r\n\r\n18/12/2008\r\n\r\n1) Un veterinario necesita conocer la prevalencia de Leucosis Bovina del establecimiento a su cargo. Tiene a su disposición una prueba serológica para tal fin cuya especificidad es máxima pero su sensibilidad es baja. Explique cómo influirán estas características de la prueba en los resultados obtenidos. \r\n\r\n2) A través de un muestreo sistemático se conoció el número de animales parasitados de un establecimiento ganadero, en junio de 2003. Explique cómo se realiza un muestreo sistemático. \r\n\r\n3) Durante el año 2002 en un establecimiento de la provincia de Entre Ríos se registraron casos nuevos de Diarrea Viral Bovina. La población bajo observación fue de 500 bovinos. Los casos registrados mes a mes son los siguientes: Enero: 0 casos; Febrero: 10 casos; Marzo: 0 casos; Abril: 12 casos; Mayo: 6 casos; Junio: 21 casos; Julio: 0 casos; Agosto: 12 casos; Septiembre: 9 casos; Octubre: 11 casos; Noviembre: 8 casos; Diciembre: 6 casos. Calcule la/s medida/s de resumen adecuada/s para describir la situación e interprete los resultados. \r\n\r\n4) Se realizó un estudio en un establecimiento ganadero a raíz de la aparición de casos de una enfermedad aguda, cuyo período de incubación es de 3-5 días y que no se tenían antecedentes en el lugar. La población total era de 120 animales. La cronología de aparición de la totalidad de enfermos diagnosticados clínicamente y de los muertos fue la siguiente:', '2012-02-14 10:25:18', 'RECIBIDO_OK', 'PARA'),
	(11, 'qantamail@gmail.com', 'sanchezih@gmail.com', '', '', 'Mensaje borrados numero 2', 'Mensaje borrados numero 2', '2012-02-14 10:30:02', 'BORRADOR', 'DE');
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;


# Dumping structure for table qantamail.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `nombre_usuario` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table qantamail.usuario: ~1 rows (approximately)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`nombre_usuario`, `password`) VALUES
	('admin', 'admin'),
	('usuario', 'usuario');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
