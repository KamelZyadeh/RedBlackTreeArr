package main;

public class RedBlackTree {

    private Node root;
    private Node subRoot;

    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != subRoot) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != subRoot) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void  preOperation(Node node) {
        if (node != subRoot) {
            System.out.println(node.data + " ");
            preOperation(node.left);
            preOperation(node.right);
        }
    }

    private void inOperation(Node node) {
        if (node != subRoot) {
            inOperation(node.left);
            System.out.println(node.data + " ");
            inOperation(node.right);
        }
    }

    private void postOperation(Node node) {
        if (node != subRoot) {
            postOperation(node.left);
            postOperation(node.right);
            System.out.println(node.data + " ");
        }
    }

    private Node searchTree(Node node, int key) {
        if (node == subRoot || key == node.data) {
            return node;
        }

        if (key < node.data) {
            return searchTree(node.left, key);
        }
        return searchTree(node.right, key);
    }

    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                //3.1 P  is red and U is red too.
                //In this case, we flip the color of nodes P,U, and G. P and U become black and, G becomes red.
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color ==0) {
                    return;
                }
            }
        }
    }
}
