import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;

public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>,Cloneable, Serializable{

    //默认初始容量, 必须是2的次幂
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //16

    //构造函数未指定时的加载因子
    static final int DEFAULT_LOAD_FACTORY = 0.75f;

    //table数组，HashMap 底层就是这样的一个数组,数组里是一个个Node节点连成的单链表
    transient Node<K,V>[] table;

    //节点数据结构
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;
        Node(int hash, K key, V value, Node<K,V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public final K getKey() {return key;}
        public final V getValue() {return value;}
        public final String toString() {return key + "=" + value;}
    }

    public HashMap(){
        this.DEFAULY_LOAD_FACTORY = DEFAULT_LOAD_FACTORY;
    }

    //HashMap put()方法
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict){
        Node<K,V>[] tab; Node<K,V> p; int n,i;
        //如果初始表为空，则进行resize操作
        if((tab = table) == null || (n=table.length) == 0)
            n= (tab = resize()).length;
        //计算出i，即表中的位置，判断是否为空，如果为空，则根据已有参数创建新的Node
        if((p = tab[i = (n-1) & hash]) == null)
            tab[i] = new Node(hash, key, value, null);
        else {
            java.util.HashMap.Node<K,V> e; K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof java.util.HashMap.TreeNode)
                e = ((java.util.HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
}