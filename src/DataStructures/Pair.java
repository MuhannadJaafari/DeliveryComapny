package DataStructures;

public class Pair<K, V> implements Cloneable {
    private K key;
    private V value;

    public Pair() {
    }

    public boolean equals(Pair<K,V> pair) {
        return this.first() == pair.first() && this.second() == pair.second();
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K first() {
        return this.key;
    }

    public V second() {
        return this.value;
    }


    @Override
    public Pair<K, V> clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            //
            return (Pair<K, V>) (Pair) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair<K,V> newObj = (Pair<K,V>) obj;
        return this.key==newObj.key&&this.value==newObj.value;
    }
}
