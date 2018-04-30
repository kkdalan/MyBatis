package tk.mybatis.simple.type;

public enum Enabled {
	DISABLED(0), ENABLED(1);

	private final int value;

	private Enabled(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
