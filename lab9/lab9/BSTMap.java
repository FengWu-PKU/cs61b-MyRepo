package lab9;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V extends Comparable<V>> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p==null) {
            return null;
        }
        if(p.key.compareTo(key)==0) {
            return p.value;
        }else if(p.key.compareTo(key)>0) {
            return getHelper(key,p.left);
        }else {
            return getHelper(key,p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if(key==null) {
            return null;
        }
        return getHelper(key,root);
    }

    private Node getNodeHelper(K key, Node p) {
        if(p==null) {
            return null;
        }
        if(p.key.compareTo(key)==0) {
            return p;
        }else if(p.key.compareTo(key)>0) {
            return getNodeHelper(key,p.left);
        }else {
            return getNodeHelper(key,p.right);
        }
    }
    /** Get the Node with key. */
    private Node getNode(K key) {
        return getNodeHelper(key,root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p==null) {
            return new Node(key,value);
        }
        if(p.key.compareTo(key)==0) {
            p.value=value;
            return null;
        }else if(p.key.compareTo(key)>0) {
            p.left=putHelper(key,value,p.left);
        }else {
            p.right=putHelper(key,value,p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root=putHelper(key,value,root);  // if root is null still works.
        size+=1;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    private void keySetHelper(Node p, Set<K> curSet) {
        if(p==null) {
            return;
        }
        curSet.add(p.key);
        keySetHelper(p.left,curSet);
        keySetHelper(p.right,curSet);
    }
    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        keySetHelper(root,keys);
        return keys;
    }

    /** Return the childNum of Node p. */
    private int childNum(Node p) {
        int child=0;
        if(p.right!=null) {
            child++;
        }
        if(p.left!=null) {
            child++;
        }
        return child;
    }

    private Node findParentHelper(Node child,Node cur) {
        if(child==null || cur==null) {
            return null;
        }
        if(cur.left==child || cur.right==child) {
            return cur;
        }
        Node parent1,parent2;
        parent1=findParentHelper(child, cur.left);
        parent2=findParentHelper(child, cur.right);
        if(parent1==null) {
            if(parent2==null) {
                return null;
            }else {
                return parent2;
            }
        }else {
            return parent1;
        }
    }

    /** Find the child's parent Node. */
    private Node findParent(Node child) {
        return findParentHelper(child, root);
    }

    /** Find the smallest Node in the subtree rooted in p. */
    private Node findSmallest(Node p) {
        if(p==null) {
            return null;
        }
        if(p.left==null) {
            return p;
        }else {
            return findSmallest(p.left);
        }
    }
    /** Find the biggest Node in the subtree rooted in p. */
    private Node findBiggest(Node p) {
        if(p==null) {
            return null;
        }
        if(p.right==null) {
            return p;
        }else {
            return findSmallest(p.right);
        }
    }

    /** Delete the given Node p which has two children. */
    private void deleteNodeWith2Children(Node p) {
        if(p==null) {
            return;
        }
        Node newp=findSmallest(p.right);
        deleteNodeWith1or2Child(newp);
        newp.left=p.left;
        newp.right=p.right;
        p.left=null;
        p.right=null;
    }


    /** Delete the given Node p. */
    private void deleteNodeWith1or2Child(Node p) {
        if(p==null) {
            return;
        }
        int child=childNum(p);
        if(child==0) {
            Node parent=findParent(p);
            if(parent.left==p) {
                parent.left=null;
            }else if(parent.right==p) {
                parent.right=null;
            }else {
                throw new RuntimeException("Delete Node Wrong!");
            }
        }else if(child==1) {
            Node parent=findParent(p);
            Node p_child;
            if(p.left!=null) {
                p_child=p.left;
            }else {
                p_child=p.right;
            }
            if(parent.left==p) {
                parent.left=p_child;
            }else if(parent.right==p) {
                parent.right=p_child;
            }else {
                throw new RuntimeException("Delete Node Wrong!");
            }
        }
    }

    /** Delete Node p. */
    private void deleteNode(Node p) {
        if(p==null) {
            return;
        }
        int childNum=childNum(p);
        if(childNum==0 || childNum==1) {
            deleteNodeWith1or2Child(p);
        }else {
            deleteNodeWith2Children(p);
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node p=getNode(key);
        deleteNode(p);
        size--;
        return null;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        Node p=getNode(key);
        if(p.value.compareTo(value)==0) {
            deleteNode(p);
            return value;
        }else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
