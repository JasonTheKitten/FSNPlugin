package everyos.plugin.fsn.random;

public class UnweightedRandomNumberGenerated implements RandomNumberGenerator {

	@Override
	public float generateNumber(float low, float high, float luck) {
		return (float) (low + Math.random() * (high - low));
	}

}
