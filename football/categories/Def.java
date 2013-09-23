package football.categories;

public enum Def
{
	SCK("SCK"),
	INT("INT"),
	FUMB("FUMB"),
	SAF("SAF"),
	TD("TD"),
	RET("RET"),
	PTS("PTS"),
	YDS("YDS");

	private final String text;
	private static final int size = values().length;

	private Def(String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}

	public static int size() {
		return size;
	}
}