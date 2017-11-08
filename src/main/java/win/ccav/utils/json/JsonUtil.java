package win.ccav.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import win.ccav.utils.StringUtil;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;


public class JsonUtil {
	

	private static Gson timestampGson = createGson();
	public static Gson gson = new Gson();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map convertToMap(String jsonStr) {
		JSONObject localJSONObject = JSONObject.fromObject(jsonStr);
		HashMap localHashMap = new HashMap();
		Iterator localIterator = localJSONObject.keys();
		while (localIterator.hasNext()) {
			Object localObject = localIterator.next();
			if(null != localJSONObject.get(localObject)){
				localHashMap.put(localObject, localJSONObject.get(localObject));
			}
			
		}
		return localHashMap;
	}
	
	public static Map<String,Object> convertJsonToMap(String jsonStr){
		JSONObject localJSONObject = JSONObject.fromObject(jsonStr);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Iterator<String> iter = localJSONObject.keys();
		String key = null;
		Object value = null;
		while(iter.hasNext()){
			key = iter.next();
			value = localJSONObject.get(key);
			if(value instanceof JSONNull){
				resultMap.put(key, null);
			}else if(null != value){
				resultMap.put(key, value);
			}
		}
		return resultMap;
	}
	
	public static Map<String,String> convertToStringMap(String jsonStr) {
		Gson gson = createGson();
		return gson.fromJson(jsonStr, new TypeToken<Map<String,String>>(){}.getType());
	}

	public static String convertArrayToString(Object jsArray, String split) {
		if ((jsArray == null) || (!(jsArray instanceof JSONArray))) {
			return null;
		}
		JSONArray localJSONArray = (JSONArray) jsArray;
		if (StringUtil.isBlank(split)) {
			split = ",";
		}
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < localJSONArray.size(); i++) {
			if (i > 0) {
				localStringBuffer.append(split);
			}
			localStringBuffer.append(localJSONArray.get(i).toString());
		}
		return localStringBuffer.toString();
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static List paserJsonArray(Class type, String jsArrayStr) {
		if ((StringUtil.isBlank(jsArrayStr)) || (jsArrayStr.equals("[]"))) {
			return new ArrayList();
		}
		JSONArray localJSONArray = JSONArray.fromObject(jsArrayStr);
		List list = (List) JSONArray.toList(localJSONArray, type);
		return list;
	}

	public static String paserJsonObject(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		registerJsonConfig(jsonConfig);
		String json = JSONArray.fromObject(obj, jsonConfig).toString();
		json = json.substring(1, json.length() - 1);
		return json;
	}
	
	public static String paserJsonPropertyString(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		registerJsonConfig(jsonConfig);
		jsonConfig.registerJsonValueProcessor(BigDecimal.class,new JsonStringValueProcessor());
		String json = JSONArray.fromObject(obj, jsonConfig).toString();
		json = json.substring(1, json.length() - 1);
		return json;
	}

	public synchronized static String toJSON(Object obj) {
		/*Gson gson = createGson();
		String json = gson.toJson(obj);
		return json;*/
		return timestampGson.toJson(obj);
	}

	public static Gson createGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter())
				.setDateFormat(DateFormat.FULL);
		return builder.create();
	}
	
	public static void registerJsonConfig(JsonConfig jsonConfig){
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new JsonDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonDateValueProcessor());
	}

	public static <T> T fromJSONToObject(String json, Class<T> clazz) {
		return timestampGson.fromJson(json, clazz);
	}

	public static <T> List<T> fromJSONToListByType(String json, Type typeToken) {
		return timestampGson.fromJson(json, typeToken);
	}

	public static <T> List<T> fromJSONToList(String json, Class<T> clazz) {
		List<T> list = timestampGson.fromJson(json, new TypeToken<List<T>>() {
		}.getType());
		return list;
	}
	public static List<Map<String, Object>> fromJSONToListKeyMaps(String json) {
		return timestampGson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
		}.getType());
	}

	public static Map<String, Object> fromJSONToMaps(String json) {
		Map<String, Object> map = timestampGson.fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());
		if(null == map){
		    return new HashMap<String, Object>();
		}
		return map;
	}
	
	/**
	 * 将xml转换为map
	 * @param element
	 * @return object
	 */
	public static Object xmlToMap(Element element) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Element> elements = element.elements();
		if (elements.size() == 0) {
			map.put(element.getName(), element.getText().trim());
			if (!element.isRootElement()) {
				return element.getText().trim();
			}
		} else if (elements.size() == 1) {
			if ("LIST".equals(elements.get(0).getName()) || "detail".equals(elements.get(0).getName())) {
				Map<String, Element> tempMap = new HashMap<String, Element>();
				for (Element ele : elements) {
					tempMap.put(ele.getName(), ele);
				}
				Set<String> keySet = tempMap.keySet();
				for (String key : keySet) {
					Namespace namespace = tempMap.get(key).getNamespace();
					List<Element> elements2 = element.elements(new QName(key, namespace));
					if (elements2.size() == 1) {
						List<Object> list = new ArrayList<Object>();
						for (Element ele : elements2) {
							list.add(xmlToMap(ele));
						}
						map.put(key, list);
					} else {
						map.put(key, xmlToMap(elements2.get(0)));
					}
				}
			} else {
				map.put(elements.get(0).getName(), xmlToMap(elements.get(0)));
			}
		} else if (elements.size() > 1) {
			Map<String, Element> tempMap = new HashMap<String, Element>();
			for (Element ele : elements) {
				tempMap.put(ele.getName(), ele);
			}
			Set<String> keySet = tempMap.keySet();
			for (String key : keySet) {
				Namespace namespace = tempMap.get(key).getNamespace();
				List<Element> elements2 = element.elements(new QName(key, namespace));
				if (elements2.size() > 1) {
					List<Object> list = new ArrayList<Object>();
					for (Element ele : elements2) {
						list.add(xmlToMap(ele));
					}
					map.put(key, list);
				} 
				//处理只有一个List的情况
				else if (elements2.size() == 1 && elements2.get(0).getName().equals("LIST")) {
					Map<String, Element> tempMap2 = new HashMap<String, Element>();
					for (Element ele : elements2) {
						tempMap2.put(ele.getName(), ele);
					}
					Set<String> keySet2 = tempMap2.keySet();
					for (String key2 : keySet2) {
						List<Element> elements3 = elements2.get(0).elements();
						if (elements3.size() > 1) {
							List<Object> list = new ArrayList<Object>();
							Map<String, Object> tempMap3 = new HashMap<String, Object>();
							for (Element ele : elements3) {
								tempMap3.put(ele.getName(),ele.getText().trim());
							}
							list.add(tempMap3);
							map.put(key2, list);
						} 
					}
				}
				else {
					map.put(key, xmlToMap(elements2.get(0)));
				}
			}
		}
		return map;
	}
	
	/**
	 * 转换xml字符串为map
	 * @param
	 * @return map
	 */
	public static Map<String,Object> convertXMLtoMap(Document document){
		Element element = document.getRootElement(); //获得根节点
		return (Map)xmlToMap(element);
	}
	

}
