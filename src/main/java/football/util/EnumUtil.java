package football.util;

public final class EnumUtil
{
	private EnumUtil() {} //NEVER USE THIS

	public static <T extends Enum<T>> String valuesToString(Class<T> enumType) {
		String values = "";
		for(T value : enumType.getEnumConstants()) {
			//TODO: make 10 below parameter
			values += String.format("%-10s ",value.toString());
		}
		return values.trim();
	}
}
