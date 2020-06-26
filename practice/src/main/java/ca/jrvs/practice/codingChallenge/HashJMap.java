package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashJMap<K, V> extends HashMap<K, V> {

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Map)) return false;
        Map<?, ?> curr = (Map<?, ?>) o;
        if (this.size() != curr.size()) return false;
        Iterator<Entry<K, V>> iterator = this.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<K, V> ref = iterator.next();
            K k = ref.getKey();
            V v = ref.getValue();
            if (curr.get(k) == null) return false;
            if (curr.get(k) != v) return false;
        }
        return true;
    }
}
