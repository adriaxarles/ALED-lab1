package es.upm.aled.lab1.measurements;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	protected int[] validChannels;

	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * FilterExtractChannels: este filtro genera un nuevo EEGModel que solo incluye
	 * los canales que se pasan como argumento a su constructor. Así, si el array
	 * validChannels que se pasa como argumento contiene, por ejemplo, los enteros
	 * 3, 6 y 8, el filtro creará un nuevo EEGModel con tres canales, que
	 * corresponderán a los números 3, 6 y 8 del EEGModel sobre el que se ejecuta el
	 * método applyFilter. En este ejemplo, los tres canales del nuevo EEGModel
	 * serán los números 0, 1 y 2; y se corresponderán con los canales 3, 6 y 8,
	 * respectivamente, del EEGModel original.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	public FilterExtractChannels(int[] validChannels) {
		// TODO: YA HECHO
		this.validChannels = validChannels;

	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// TODO: YA HECHO
		/*Measurement[] orig = eeg.getMeasurements();
		Measurement[] filtro = new Measurement[orig.length];
		for (int i = 0; i < orig.length; i++) {
			Measurement m = orig[i];
			float[] canalesElegidos = new float[validChannels.length];
			for (int j = 0; j < validChannels.length; j++) {
				if (0 <= validChannels[j] && validChannels[j] < m.numChannels()) {
					canalesElegidos[j] = m.getChannel(validChannels[j]);
				} else {
					return null;
				}
			}
			filtro[i] = new Measurement(canalesElegidos);
		}
		return new EEGModel(filtro); */
		EEGModel filtrado= new EEGModel();
		for(Measurement m: eeg.measurements) {
			float[] canalesValidos= new float[validChannels.length];
			for(int i=0; i< m.numChannels(); i++) {
				if(validChannels[i]>=m.numChannels()|| validChannels[i]<0) {
					return null;
				}
				canalesValidos[i]= m.getChannel(validChannels[i]); 
				}
			Measurement p= new Measurement(canalesValidos);
			filtrado.addMeasurement(p);
			}
		return filtrado;
		}
}
