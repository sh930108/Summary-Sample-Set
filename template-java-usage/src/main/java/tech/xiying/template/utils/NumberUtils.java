package tech.xiying.template.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Objects;

public class NumberUtils {
	public static Long defaultLong(Long longValue, Long defaultValue) {
		return (longValue == null) ? defaultValue : longValue;
	}

	public static Integer defaultInteger(Integer integerValue, Integer defaultValue) {
		return (integerValue == null) ? defaultValue : integerValue;
	}

	public static Long add(Long longValue1, Long longValue2) {
		if (longValue1 == null) {
			longValue1 = Long.valueOf(0L);
		}

		if (longValue2 == null) {
			longValue2 = Long.valueOf(0L);
		}

		return Long.valueOf(longValue1.longValue() + longValue2.longValue());
	}

	public static boolean longEquals(Long longValue1, Long longValue2) {
		if (longValue1 == null || longValue2 == null) {
			return false;
		}
		return longValue1.equals(longValue2);
	}

	public static boolean intEquals(Integer intValue1, Integer intValue2) {
		if (intValue1 == null || intValue2 == null) {
			return false;
		}
		return intValue1.equals(intValue2);
	}

	public static boolean notEquals(Long longValue1, Long longValue2) {
		if (longValue1 == null || longValue2 == null) {
			return false;
		}
		return !longValue1.equals(longValue2);
	}

	public static Integer toInteger(Object source) {
		if (source == null) {
			return null;
		}

		if (source instanceof Integer) {
			return (Integer) source;
		}

		Number parseNumber = null;
		try {
			parseNumber = NumberFormat.getInstance().parse(Objects.toString(source));
		} catch (ParseException parseException) {
		}

		if (parseNumber == null) {
			return null;
		}

		return Integer.valueOf(parseNumber.intValue());
	}

	public static Integer toInteger(Object source, Integer defaultVal) {
		Integer result = toInteger(source);
		return (result == null) ? defaultVal : result;
	}

	public static Long toLong(Object source) {
		if (source == null) {
			return null;
		}

		if (source instanceof Long) {
			return (Long) source;
		}

		Number parseNumber = null;
		try {
			parseNumber = NumberFormat.getInstance().parse(Objects.toString(source));
		} catch (ParseException parseException) {
		}

		if (parseNumber == null) {
			return null;
		}

		if (parseNumber instanceof Long) {
			return (Long) parseNumber;
		}
		return Long.valueOf(parseNumber.longValue());
	}

	public static Long toLong(Object source, Long defaultVal) {
		Long result = toLong(source);
		return (result == null) ? defaultVal : result;
	}
}
