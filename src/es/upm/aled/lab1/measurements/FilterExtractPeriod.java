package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified period from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractPeriod implements Filter {

	protected int min;
	protected int max;

	/**
	 * Builds the Filter from the [min, max] range defining the period that needs to
	 * be extracted. min and max are the indexes of the first and last measurements
	 * of the array obtained by calling the getMeasurements() method of EEGModel,
	 * and represent the starting and ending point of the period to be extracted.
	 * Both indexes are included and max-min must be less than the length of the
	 * Measurements array of the EGG Model.
	 * 
	 * @param min Start of the period to be extracted.
	 * @param max End of the period to be extracted.
	 */
	public FilterExtractPeriod(int min, int max) {
		// TODO: YA HECHO
		this.min = min;
		this.max = max;
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// TODO: YA HECHO
		Measurement[] orig = eeg.getMeasurements();
		if (min >= 0 && min <= max && max < orig.length) {
			Measurement[] filtro = new Measurement[max - min + 1];
			for (int i = min; i <= max; i++) {
				filtro[i - min] = orig[i];
			}
			return new EEGModel(filtro);
		} else {
			return null;
		}

	}
}
