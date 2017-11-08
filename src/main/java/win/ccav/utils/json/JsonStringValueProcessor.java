package win.ccav.utils.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.math.BigDecimal;

public class JsonStringValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof BigDecimal) {
			return ((BigDecimal)value).toPlainString();
		}
		return value.toString();
	}
}
