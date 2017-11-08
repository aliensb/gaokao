package win.ccav.utils.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JsonDateValueProcessor implements JsonValueProcessor {

	private String datePattern = "yyyy-MM-dd";

    
    public JsonDateValueProcessor() {
        super();
    }
    
    public JsonDateValueProcessor(String format) {
        super();
        this.datePattern = format;
    }
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(value);
	}
	
	private Object process(Object value) {
        try {
        	if(value instanceof Timestamp){
            	String temp = value.toString();
            	int lastDot = temp.lastIndexOf(".");
            	return temp.substring(0, lastDot);
            }else if (value instanceof Date || value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern,Locale.UK);
                return sdf.format((Date) value);
            }
            return value == null ? "" : value.toString();
        } catch (Exception e) {
            return "";
        }

    }

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

}
