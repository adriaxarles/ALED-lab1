package es.upm.aled.lab1.measurements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MxbFilterThreshold implements Filter {
	private float threshold; // Umbral a superar
	private int[] channels;

	// Canales en los que se aplica el umbral
	public MxbFilterThreshold(float threshold, int[] channels) {
		this.threshold = threshold;
		this.channels = channels;
	}

	public EEGModel applyFilter(EEGModel eeg) {
		// Lista de muestras originales
		// como getMeasurements en esta implementacion devuelve un array
		// accedo utilizando el atributo de la clase directamente
		List<Measurement> measurements = eeg.measurements;

		// Lista de muestra filtradas, inicialmente vacía
		List<Measurement> filteredMeasurements = new ArrayList<Measurement>();

		// Recorremos las muestras originales
		for (Measurement measurement : measurements) {
			// vamos a procesar la muestra mesurement
			// detectar si tiene algun float entre los canales que
			// nos interese que no supere el umbral

			// Indicala muestra supera las comprobaciones
			boolean valido = true;

			// Extraemos los canales de la muestra
			// en el fichero de prueba eran unos 11 floats
			float[] mChannels = measurement.getChannels();

			// recorremos los canales que tienen que superar el threshold
			for (int valor : this.channels) {
				// comprobar que el valor del canal es correcto
				// el valor tiene que estar entre 0 y el numero de floats en la medida

				if (valor < 0 || valor >= mChannels.length) {
					// no hay ningun canal con este valor
					// error el crear el filtro
					return null;
				}
				// comprobar que el canal asociado a valor supera el umbral
				if (mChannels[valor] < this.threshold) {
					// en este caso la medida no vale
					// este canal no supera el umbral
					valido = false;
					System.out.println("Esta medida no es valida: " + Arrays.toString(measurement.getChannels()));
					break; // salimos del for Y listos para comprobar la siguiente medida
				}
			}

			// Si todo valido, añadimos la muestra a la nueva lista
			if (valido)
				filteredMeasurements.add(measurement);
		}

		// Crea y devuelve el nuevo modelo
		return new EEGModel(filteredMeasurements);
	}

	public static void main(String[] args) {

		final String FICHERO_IN = "recordings/OpenBCI_raw_1_ejemplo_basico.txt";
		final String FICHERO_OUT = "recordings/OpenBCI_raw_1_filtrado_ej_basico.txt";

		try {
			// cargamos el modelo con el fichero de prueba
			EEGModel eeg = new EEGModel(FICHERO_IN);

			// filtramos el eeg
			MxbFilterThreshold filtro = new MxbFilterThreshold(10, new int[] { 2, 3 });
			eeg = eeg.filter(filtro);
			if (eeg == null)
				throw new Exception("Canales fuera de rango");
			else
				eeg.saveFile(FICHERO_OUT);

		} catch (IOException e) {
			System.out.println("Error al leer o escribir el fichero");
		} catch (Exception e) {
			System.out.println("Error!!: " + e.getMessage());
		}

	}
}
