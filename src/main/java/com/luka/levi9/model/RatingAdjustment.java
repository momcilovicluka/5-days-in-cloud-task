package com.luka.levi9.model;

public enum RatingAdjustment {
	K10(10), K20(20), K30(30), K40(40), K50(50);

	private final int value;

	RatingAdjustment(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static RatingAdjustment getRatingAdjustment(int hoursPlayed) {
		if (hoursPlayed < 500)
			return K50;
		if (hoursPlayed < 1000)
			return K40;
		if (hoursPlayed < 3000)
			return K30;
		if (hoursPlayed < 5000)
			return K20;
		return K10;
	}
}