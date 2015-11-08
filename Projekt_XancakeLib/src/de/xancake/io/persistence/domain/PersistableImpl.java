package de.xancake.io.persistence.domain;

import java.io.IOException;
import java.util.Objects;
import de.xancake.io.persistence.bind.TypeBinding;
import de.xancake.io.persistence.broker.PersistenceBroker;

public class PersistableImpl<T> implements Persistable<T> {
	public static final int ID_UNPERSISTED = -1;
	
	private TypeBinding<T> _binding;
	private int _id;
	private T _object;
	
	public PersistableImpl(TypeBinding<T> binding, T object) {
		this(binding, ID_UNPERSISTED);
		_object = Objects.requireNonNull(object);
	}
	
	public PersistableImpl(TypeBinding<T> binding, int id) {
		_binding = Objects.requireNonNull(binding);
		_id = id;
	}
	
	@Override
	public int getId() {
		return _id;
	}
	
	@Override
	public boolean isUnpersisted() {
		return getId() == ID_UNPERSISTED;
	}
	
	@Override
	public T getObject() {
		return _object;
	}
	
	@Override
    public void load(PersistenceBroker broker) throws IOException {
		_object = broker.load(_binding, getId());
    }
	
	@Override
	public void store(PersistenceBroker broker) throws IOException {
		if(isUnpersisted()) {
			_id = broker.acquireId(_binding);
			broker.insert(_binding, _id, _object);
		} else {
			broker.update(_binding, _id, _object);
		}
	}
	
	@Override
    public void delete(PersistenceBroker broker) throws IOException {
	    broker.delete(_binding, _id);
    }
}
