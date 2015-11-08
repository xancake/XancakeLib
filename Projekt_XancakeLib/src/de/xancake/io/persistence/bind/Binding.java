package de.xancake.io.persistence.bind;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import de.xancake.io.persistence.bind.TypeBinding_I;

public class Binding<T> implements TypeBinding_I<T> {
	private String myEntity;
	private AttributeBinding<Integer> myIDAttribute;
	private List<AttributeBinding<?>> myAttributes;
	private Factory<T> myFactory;
	
	public Binding(String name, AttributeBinding<Integer> idAttribute, Factory<T> factory) {
		setEntityName(name);
		myAttributes = new ArrayList<>();
		setIDAttribute(idAttribute);
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
    public AttributeBinding<Integer> getIDAttribute() {
	    return myIDAttribute;
    }
	
	public void setIDAttribute(AttributeBinding<Integer> idAttribute) {
		myIDAttribute = Objects.requireNonNull(idAttribute);
	}
	
	@Override
    public List<AttributeBinding<?>> getAttributes() {
	    return Collections.unmodifiableList(myAttributes);
    }
	
	public void addAttribute(AttributeBinding<?> attribute) {
		myAttributes.add(attribute);
	}
	
	public void removeAttribute(AttributeBinding<?> attribute) {
		myAttributes.remove(attribute);
	}
	
    @Override
	@SuppressWarnings("unchecked")
	public <V> V get(T object, AttributeBinding<V> attribute) {
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
	public <V> void set(T object, AttributeBinding<V> attribute, V value) {
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
	
	private <V> boolean isValidAttribute(AttributeBinding<V> attribute) {
		return myIDAttribute.equals(attribute) || myAttributes.contains(attribute);
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
