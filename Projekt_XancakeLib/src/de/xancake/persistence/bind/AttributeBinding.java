package de.xancake.persistence.bind;

public class AttributeBinding<T> {
	private String myName;
	private Class<T> myJavaType;
	private boolean myOptional;
	
	public AttributeBinding(String name, Class<T> javaType, boolean optional) {
		myName = name;
		myJavaType = javaType;
		myOptional = optional;
	}
	
	public String getName() {
		return myName;
	}
	
	public Class<T> getJavaType() {
		return myJavaType;
	}
	
	public boolean isOptional() {
		return myOptional;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((myName == null) ? 0 : myName.hashCode());
	    return result;
    }
	
	@Override
    public boolean equals(Object obj) {
	    if(this == obj)
		    return true;
	    if(obj == null)
		    return false;
	    if(getClass() != obj.getClass())
		    return false;
	    AttributeBinding<?> other = (AttributeBinding<?>)obj;
	    if(myName == null) {
		    if(other.myName != null)
			    return false;
	    } else if(!myName.equals(other.myName))
		    return false;
	    return true;
    }
}