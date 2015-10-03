package jotto.application;

/**
 * The difficulty level as percieved by the application (not by the jotto core)
 * */
public enum JDifficulty {
	Easy(0), Normal(1), Hard(2);

	private final int _level;

	JDifficulty(int level) {
		_level = level;
	}

	public int getLevel() {
		return _level;
	}
}