class BinaryTree<K extends Comparable<K>, V> {
    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    private Node root;

    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }

    private Node insertRec(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }
        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key, value);
        }
        return root;
    }

    public V search(K key) {
        return searchRec(root, key);
    }

    private V searchRec(Node root, K key) {
        if (root == null) {
            return null;
        }
        if (key.equals(root.key)) {
            return root.value;
        }
        if (key.compareTo(root.key) < 0) {
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }

    public void inOrderTraversal() {
        inOrderRec(root);
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.key + ": " + root.value);
            inOrderRec(root.right);
        }
    }
}