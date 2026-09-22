package es.upm.aled.lab1.measurements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExamenEjercicio2 {

	public static boolean checkConsistency(String contenidoArchivo) {
		// Dividimos el contenido en líneas.
		String[] lineas = contenidoArchivo.split("\n");

		// Creo un contador. Contador%256 deberá ser igual al numero de medida.
		// Mejor crear un contador, pues si hacemos un for (int i=0; ...
		// las lineas que empiezan por % tambien incrementan el numero i, 
		// cosa que no facilita el procesado del fichero
		int contador = 0;
		for (String medida: lineas) {
			// Analizar cada linea

			// Quitar las lineas que empiezan por %
			if (medida.startsWith("%")) {
				continue;
			}

			// Dividimos la línea por las comas para obtener los valores.
			String[] valores = medida.split(",");
			try {
				// Parseamos el primer valor (índice) a entero.
				int indiceActual = Integer.parseInt(valores[0].trim());

				// Comparamos el índice actual con el que esperamos.
				if (indiceActual != (contador % 256)) {
					return false; // No son consecutivos.
				}
			} catch (NumberFormatException e) {
				// Si el primer valor no es un número, el formato es incorrecto.
				return false;
			}
			
			contador++;
		}
		// Si hemos recorrido todas las líneas y los índices eran correctos,
		// la consistencia es correcta.
		return true;
	}

	public static void main(String[] args) {
		final String FICHERO_BUENO = "recordings/OpenBCI_raw_1.txt";
		final String FICHERO_MALO1 = "recordings/OpenBCI_raw_3.txt";
		final String FICHERO_MALO2 = "recordings/OpenBCI_raw_4.txt";
		
		final String fichero = FICHERO_MALO1;

		try {
			String contenido = Files.readString(Path.of(fichero));
			if (checkConsistency(contenido)) {
				System.out.println(fichero + " tiene el formato correcto!!!");
			} else {
				System.out.println(fichero + " NO tiene el formato correcto!!!");
			}
		} catch (IOException e) {
			System.out.println(fichero + " no encontrado!!!");
		}
	}
}
