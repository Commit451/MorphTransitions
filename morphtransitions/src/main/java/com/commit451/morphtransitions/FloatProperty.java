package com.commit451.morphtransitions;

import android.util.Property;

/**
 * An implementation of {@link Property} to be used specifically with fields of
 * type
 * <code>float</code>. This type-specific subclass enables performance benefit by allowing
 * calls to a {@link #set(Object, Float) set()} function that takes the primitive
 * <code>float</code> type and avoids autoboxing and other overhead associated with the
 * <code>Float</code> class.
 *
 * @param <T> The class on which the Property is declared.
 **/
abstract class FloatProperty<T> extends Property<T, Float> {
    public FloatProperty(String name) {
        super(Float.class, name);
    }

    /**
     * A type-specific override of the {@link #set(Object, Float)} that is faster when dealing
     * with fields of type <code>float</code>.
     */
    public abstract void setValue(T object, float value);

    @Override
    final public void set(T object, Float value) {
        setValue(object, value);
    }
}
