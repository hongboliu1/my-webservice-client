package com.ai.webservice.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * json工具类,提供常用的工具方法
 * 
 * @author liujiangping
 * 
 */
public class JsonUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(JsonUtils.class);
	/**
	 * 默认的日期格式
	 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 单例模式
	 */
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	/**
	 * 单例模式
	 */
	public static final XmlMapper XML_MAPPER = new XmlMapper();
	static {
		OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_PATTERN));
		// 设置输出时包含属性的风格
		OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		OBJECT_MAPPER.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OBJECT_MAPPER.configure(
				DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		OBJECT_MAPPER.addHandler(new DeserializationProblemHandler());
		XML_MAPPER.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_PATTERN));
		// 设置输出时包含属性的风格
		XML_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		XML_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		XML_MAPPER.addHandler(new DeserializationProblemHandler());
	}

	/**
	 * xml字符串序列化成对象
	 * 
	 * @param str
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static <T> T deserializeXML(String str, Class<T> type) {
		try {
			return (T) XML_MAPPER.readValue(str, type);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对象序列化成xml字符串
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String serializeXML(Object object) {
		try {
			return "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ XML_MAPPER.writeValueAsString(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 转换为json.
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		if (null == object) {
			return "";
		}
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 转换为jsonp.
	 * 
	 * @param functionName
	 * @param object
	 * @return
	 */
	public static String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(EncodeUtils.urlEncode(functionName),
				object));
	}

	/**
	 * json转换为对象.
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return OBJECT_MAPPER.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * Description ： 将map转换为对象<br>
	 * 
	 * liujp@asiainfo.com
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * @since
	 *
	 */
	public static <T> T map2obj(Map map, Class<T> clazz) {
		return JsonUtils.fromJson(JsonUtils.toJson(map), clazz);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static HashMap obj2map(Object obj) {
		return JsonUtils.fromJson(JsonUtils.toJson(obj), HashMap.class);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型.
	 * 
	 * @see #constructParametricType(Class, Class...)
	 */
	public static <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T) OBJECT_MAPPER.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 
	 * @param jsonString
	 * @param parametrized
	 *            泛型类
	 * @param parameterClasses
	 *            泛型参
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<?> parametrized,
			Class<?>... parameterClasses) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		return (T) fromJson(jsonString,
				constructParametricType(parametrized, parameterClasses));
	}

	/**
	 * 構造泛型的Type如List<MyBean>,
	 * 则调用constructParametricType(ArrayList.class,MyBean.class)
	 * Map<String,MyBean>则调用(HashMap.class,String.class, MyBean.class)
	 */
	public static JavaType constructParametricType(Class<?> parametrized,
			Class<?>... parameterClasses) {
		return OBJECT_MAPPER.getTypeFactory().constructParametricType(
				parametrized, parameterClasses);
	}

	/**
	 * 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
	 */
	public static <T> T update(T object, String jsonString) {
		try {
			return (T) OBJECT_MAPPER.readerForUpdating(object).readValue(
					jsonString);
		} catch (JsonProcessingException e) {
			logger.error("update json string:" + jsonString + " to object:"
					+ object + " error.", e);
		} catch (IOException e) {
			logger.error("update json string:" + jsonString + " to object:"
					+ object + " error.", e);
		}
		return null;
	}
}
