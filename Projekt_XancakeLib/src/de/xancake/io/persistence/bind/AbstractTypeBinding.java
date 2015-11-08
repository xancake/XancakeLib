package de.xancake.io.persistence.bind;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AbstractTypeBinding<T> implements TypeBinding<T> {
	private String myEntity;
	private List<SimpleAttributeBinding<?>> myAttributes;
	private Factory<T> myFactory;
	
	public AbstractTypeBinding(String name, Factory<T> factory) {
		setEntityName(name);
		myAttributes = new ArrayList<>();
		myFactory = Objects.requireNonNull(factory);
	}
	
	@Override
    public String getEntityName() {
	    return myEntity;
    }
	
	public void setEntityName(String name) {
		myEntity = Objects.requireNonNull(name);
	}
	
	@Override
    public List<SimpleAttributeBinding<?>> getAttributes() {
	    return Collections.unmodifiableList(myAttributes);
    }
	
	public void addAttribute(SimpleAttributeBinding<?> attribute) {
		myAttributes.add(attribute);
	}
	
	public void removeAttribute(SimpleAttributeBinding<?> attribute) {
		myAttributes.remove(attribute);
	}
	
    @Override
	@SuppressWarnings("unchecked")
	public <V> V get(T object, SimpleAttributeBinding<V> attribute) {
		if(!isValidAttribute(attribute)) {
			throw new IllegalArgumentException("The attribute '" + attribute + "' is not known to the entity '" + getEntityName() + "'");
		}
		
		try {
	        Method method = object.getClass().getDeclaredMethod(buildMethodName("get", attribute.getName()));
	        return (V)method.invoke(object);
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        	throw new RuntimeException(e);
        }
	}
	
	@Override
	public <V> void set(T object, SimpleAttributeBinding<V> attribute, V value) {
		if(!isValidAttribute(attribute)) {
			throw new IllegalArgumentException("The attribute '" + attribute + "' is not known to the entity '" + getEntityName() + "'");
		}
		
		try {
	        Method method = object.getClass().getDeclaredMethod(buildMethodName("set", attribute.getName()), attribute.getJavaType());
	        method.invoke(object, value);
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        	throw new RuntimeException(e);
        }
	}
	
	private <V> boolean isValidAttribute(SimpleAttributeBinding<V> attribute) {
		return myAttributes.contains(attribute);
	}
	
	private String buildMethodName(String prefix, String attributeName) {
		String name = Character.toUpperCase(attributeName.charAt(0)) + attributeName.substring(1);
		return prefix + name;
	}
	
	@Override
    public T create() {
	    return myFactory.create();
    }
	
	public interface Factory<C> {
		C create();
	}
}
