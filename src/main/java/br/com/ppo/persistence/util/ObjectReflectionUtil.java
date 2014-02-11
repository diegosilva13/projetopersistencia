package br.com.ppo.persistence.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectReflectionUtil {

	public static Object newInstance(Class<?> clazz)
			throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}

	public List<Field> fields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		while (!clazz.equals(Object.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				fields.add(field);
			}
			clazz = iteratorClazz(clazz);
		}
		return fields;
	}

	public Object setValue(Object obj, Object value, String nameField)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		while (!clazz.equals(Object.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.getName().equalsIgnoreCase(nameField)) {
					field.setAccessible(true);
					value = this.convertObject(field, value);
					field.set(obj, value);
					return obj;
				}
			}
			clazz = iteratorClazz(clazz);
		}
		return obj;
	}

	private static Class<?> iteratorClazz(Class<?> clazz) {
		return clazz.getSuperclass();
	}

	public Object convertObject(Field field, Object value) {
		if(!field.getType().equals(value.getClass())){
			if(value instanceof Integer && field.getType().equals(Long.class)){
				return Long.parseLong(String.valueOf(value));
			}else if(value instanceof Long && field.getType().equals(Integer.class)){
				return Integer.parseInt(String.valueOf(value));
			}
		}
		return value;
	}
	
	public Object getValue(Object obj, String nameField)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		while (!clazz.equals(Object.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.getName().equalsIgnoreCase(nameField)) {
					field.setAccessible(true);
					return field.get(obj);
				}
			}
			clazz = iteratorClazz(clazz);
		}
		return null;
	}
	
	public Object setAllValues(Map<Field, Object> fieldAndValue, Class<?> clazz) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object object = newInstance(clazz);
		for(Field field: fieldAndValue.keySet()){
			Object value = fieldAndValue.get(field);
			if(value != null){
				value = this.convertObject(field, value);
				field.setAccessible(true);
				field.set(object, value);
			}
		}
		return object;
	}
	
	public Object newInstanceOfField(Class<?> clazz, String name) throws InstantiationException, IllegalAccessException{
		while (!clazz.equals(Object.class)) {
			for(Field field: clazz.getDeclaredFields()){
				if(field.getName().equalsIgnoreCase(name)){
					return newInstance(field.getType());
				}
			}
			clazz = iteratorClazz(clazz);
		}
		return null;
	}
	
	public boolean hasField(Class<?> clazz, String name){
		while (!clazz.equals(Object.class)) {
			for(Field field: clazz.getDeclaredFields()){
				if(field.getName().equalsIgnoreCase(name)){
					return true;
				}
			}
			clazz = iteratorClazz(clazz);
		}
		return false;
	}
}
