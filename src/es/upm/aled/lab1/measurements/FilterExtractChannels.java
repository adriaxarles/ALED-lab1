package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	private int[] validChannels;

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
		// YA ESTÁ
		this.validChannels = validChannels;

	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// YA ESTÁ
		Measurement[] orig = eeg.getMeasurements();
		Measurement[] filtro = new Measurement[orig.length];
		for (int i = 0; i < orig.length; i++) {
			float[] nuevosCanales = new float[this.validChannels.length];
			for (int j = 0; j < validChannels.length; j++) {
				nuevosCanales[j] = orig[i].getChannel(validChannels[j]);
			}
			filtro[i] = new Measurement(nuevosCanales);
		}
		return new EEGModel(filtro);
	}

}
