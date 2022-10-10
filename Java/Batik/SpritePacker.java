import java.util.List;

/**
 * @author zzy
 * @date 2022/9/27 14:48
 */
public class SpritePacker {

    private Node root = new Node(0, 0, 0, 0);

    public void pack(List<Sprite> sprites) {
        root.w = sprites.get(0).getWidth();
        root.h = sprites.get(0).getHeight();
        Node fit, node;

        for (Sprite sprite : sprites) {
            if ((node = findNode(root, sprite.getWidth(), sprite.getHeight())) != null) {
                fit = splitNode(node, sprite.getWidth(), sprite.getHeight());
            } else {
                fit = growNode(sprite.getWidth(), sprite.getHeight());
                if (fit == null) {
                    continue;
                }
            }
            sprite.setX(fit.x);
            sprite.setY(fit.y);
        }
    }

    public int getW() {
        return root.w;
    }

    public int getH() {
        return root.h;
    }

    private Node findNode(Node node, int w, int h) {
        if (node.used) {
            Node n;
            if ((n = findNode(node.right, w, h)) != null) {
                return n;
            }
            if ((n = findNode(node.down, w, h)) != null) {
                return n;
            }
        } else if (w <= node.w && h <= node.h) {
            return node;
        }
        return null;
    }

    private Node splitNode(Node node, int w, int h) {
        node.used = true;
        node.right = new Node(node.x + w, node.y, node.w - w, h);
        node.down = new Node(node.x, node.y + h, node.w, node.h - h);
        return node;
    }

    private Node growNode(int w, int h) {
        boolean canGrowDown = (w <= root.w);
        boolean canGrowRight = (h <= root.h);

        boolean shouldGrowRight = canGrowRight && (root.h >= (root.w + w));
        boolean shouldGrowDown = canGrowDown && (root.w >= (root.h + h));
        if (shouldGrowRight) {
            return growRight(w, h);
        } else if (shouldGrowDown) {
            return growDown(w, h);
        } else if (canGrowRight) {
            return growRight(w, h);
        } else if (canGrowDown) {
            return growDown(w, h);
        }

        return null;
    }

    private Node growRight(int w, int h) {
        Node newRoot = new Node(0, 0, root.w + w, root.h);
        newRoot.used = true;
        newRoot.down = root;
        newRoot.right = new Node(root.w, 0, w, root.h);
        root = newRoot;
        Node node;
        if ((node = findNode(root, w, h)) != null) {
            return splitNode(node, w, h);
        }
        return null;
    }

    private Node growDown(int w, int h) {
        Node newRoot = new Node(0, 0, root.w, root.h + h);
        newRoot.used = true;
        newRoot.down = new Node(0, root.h, root.w, h);
        newRoot.right = root;
        root = newRoot;
        Node node;
        if ((node = findNode(root, w, h)) != null) {
            return splitNode(node, w, h);
        }
        return null;
    }

    private static class Node {
        private final int x;
        private final int y;
        private int w;
        private int h;
        private boolean used = false;

        private Node right;
        private Node down;

        public Node(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

    }
}
