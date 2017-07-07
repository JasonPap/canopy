package sampling.evaluation.ngrams.hashmap;

import java.util.*;

class Node<K, V> implements Map.Entry<K, V>
{
    final int hash;
    final K key;
    V value;
    Node<K, V> next;
    
    Node(final int hash, final K key, final V value, final Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
    
    @Override
    public final K getKey() {
        return this.key;
    }
    
    @Override
    public final V getValue() {
        return this.value;
    }
    
    @Override
    public final String toString() {
        return this.key + "=" + this.value;
    }
    
    @Override
    public final V setValue(final V newValue) {
        final V oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Map.Entry) {
            final Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
            if (this.key.equals(e.getKey()) && this.value.equals(e.getValue())) {
                return true;
            }
        }
        return false;
    }
    
    public static int hash(final Object key, final int capacity) {
        return (key.hashCode() & Integer.MAX_VALUE) % capacity;
    }
}
