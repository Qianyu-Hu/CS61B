import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* Using loop as far as possible*/
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        private final K key;
        private V val;
        private BSTNode left;
        private BSTNode right;

        private BSTNode(K key, V value) {
            this.key = key;
            this.val = value;
            this.left = null;
            this.right = null;
        }

        private List<BSTNode> nodesInOrder() {
            List<BSTNode> keys = new ArrayList<>();
            if (left != null) {
                keys.addAll(left.nodesInOrder());
            }
            keys.add(this);
            if (right != null) {
                keys.addAll(right.nodesInOrder());
            }
            return keys;
        }
    }

    private BSTNode root;

    private int size;

    public BSTMap() {
        clear();
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (this.root == null) {
            return false;
        }
        return(find(this.root,key)!=null);
    }

    @Override
    public V get(K key) {
        if (this.root == null) {
            return null;
        }
        BSTNode node = find(this.root,key);
        return node.val;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        this.root = put(this.root, key, value);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("remove method not implemented");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("remove method not implemented");
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public void printInOrder() {
        StringBuilder s = new StringBuilder();
        for (BSTNode node : this.root.nodesInOrder()) {
            s.append(node.val.toString());
        }
        System.out.print(s);
    }

    private BSTNode find(BSTNode node, K key) {
        if(node==null){return null;}
        if(key.equals(node.key)){return node;}
        else if(key.compareTo(node.key) < 0){return find(node.left,key);}
        else{return find(node.right,key);}
    }

    private BSTNode put(BSTNode node,K key,V value){
            if (node == null) {
                size++;
                return new BSTNode(key, value);
            }

            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = put(node.left, key, value);
            } else if (cmp > 0) {
                node.right = put(node.right, key, value);
            } else {
                // 如果key已存在，更新value（根据Map的规范，应该更新而不是忽略）
                node.val = value;
            }
            return node;
        }

    }