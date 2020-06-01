import java.util.ArrayList;

/*
    Generic Class for a Binary Search Tree
    Generic type must extend the Comparable class
*/
public class BinarySearchTree<T extends Comparable<T>>{
    private TreeNode<T> root; // root of the tree
    private int size; //number od nodes in the tree

    /*
        Default constuctor for the Binary Search Tree
    */
    public BinarySearchTree(){
        this.root = null;
        size = 0;
    }

    /*
        Gets the root of the tree
        @return TreeNode<T> this.root
    */
    public TreeNode<T> getRoot(){
        return this.root;
    }

    /*
        Gets the number of nodes in the tree
        @return int this.size
    */
    public int getSize(){
        return this.size;
    }

    /*
        Adds a new element to the tree
        @parama T e: element to be added to the tree
    */
    public void add(T e){
        this.size++;

        // If the root is null then the new element will be the root of the tree
        if(this.root == null){
            this.root = new TreeNode<T>(e);
        }

        else{
            TreeNode<T> node = this.search(e, this.getRoot()); // Search for the value to be stored within the tree starting at the root
            while(true){
                // if the node found is external, store the element in the node and give the node children
                if(!node.isInternal()){
                    node.setElement(e);
                    node.setLeftChild(new TreeNode<T>(node));
                    node.setRightChild(new TreeNode<T>(node));
                    break;
                }

                // if the node is internal, recursivly call the search function on the right subtree until an external node is found
                else{
                    node = this.search(e, node.getRightChild());
                }
            }
        }
    }

    /*
        Removes an element from the tree
        @parama T e: The element to be removed
        @return TreeNode<T> null: The node does not exsit in the tree
        @ parama TreeNode<T> node: The node that was removed from the tree
    */
    public TreeNode<T> remove(T e){
        TreeNode<T> node = this.search(e, this.getRoot()); // search for node to remove starting at the root
        // if the node retuned is null or an external node then the value the be removed is not within the tree
        if(node == null || !node.isInternal()){
            return null;
        }

        if(node.getParent() == null){
            ArrayList<TreeNode<T>> nodes = this.preorder(node);
            nodes.remove(0);
            this.root = null;
            this.size = 0;
            for(TreeNode<T> n : nodes){
                if(n.getElement() == null){
                    continue;
                }

                this.add(n.getElement());
            }

            return node;
        }

        // else remove the node found
        else{
            this.size--;
            // if the left child of the node is an external node replace the node with the sibling of the left child
            if(!node.getLeftChild().isInternal()){
                // if the node is a left child set the sibling to be the left child of the parent
                if(node.isLeftChild()){
                    node.getParent().setLeftChild(node.getRightChild());
                }
                // otherwise right
                else{
                    node.getParent().setRightChild(node.getRightChild());
                }
            }

            // if the right child of the node is an external node replace the node with the sibling of its right child
            else if(!node.getRightChild().isInternal()){
                if(node.isLeftChild()){
                    node.getParent().setLeftChild(node.getLeftChild());
                }
                else{
                    node.getParent().setRightChild(node.getLeftChild());
                }
            }

            // else the node is an internal node
            else{
                ArrayList<TreeNode<T>> nodes = this.inorder(node.getRightChild()); // get the nodes in the right subtree in order
                // find the first external node in the right subtree 
                for(TreeNode<T> n : nodes){
                    // if the node n is external remove it from the tree, then replace node's element with the element that was in node n
                    if(!n.getLeftChild().isInternal() || !n.getRightChild().isInternal()){
                        T element = n.getElement(); // temp storage of the node n's element 
                        if(element.equals(e)){
                            // if the node is a left child set the sibling to be the left child of the parent
                            if(n.isLeftChild()){
                                n.getParent().setLeftChild(n.getRightChild());
                            }
                            // otherwise right
                            else{
                                n.getParent().setRightChild(n.getRightChild());
                            }
                            break;
                        }
                        else{
                            this.remove(element); // removes n
                            node.setElement(element); // store the element from n in the node
                            this.size++;
                            break;
                        }
                    }
                }
            }
        }
        return node;
    }

    /*
        Gets the elements in the tree
        @return ArrayList<T> null: the tree is empty
        @return ArrayList<T> elements: list of element in the tree in order
    */
    public ArrayList<T> getElements(){
        // if the root in null the tree is empty
        if(this.root == null){
            return null;
        }

        ArrayList<T> elements = new ArrayList<T>();
        ArrayList<TreeNode<T>> nodes = this.getNodes(); // gets all nodes in the tree

        // extracts the elements from the nodes in the tree
        for(TreeNode<T> n : nodes){
            elements.add(n.getElement());
        }

        return elements;
    }

    /*
        Gets the nodes in the tree in order
        @return ArrayList<TreeNode<T>> null: the tree is empty
        @return ArrayList<TreeNode<T>> this.inorder(this.root): gets the nodes in the tree in order starting from the root
    */
    public ArrayList<TreeNode<T>> getNodes(){
        if(this.root == null){
            return null;
        }

        return this.inorder(this.root);
    }

    /*
        Swaps the element contained within two given nodes in the tree
        @parama TreeNode<T> node1: 1st node to have the element swapped
        @parama TreeNode<T> node2: 2nd node to have the element swapped
    */
    public void swapElements(TreeNode<T> node1, TreeNode<T> node2){
        T temp = node1.getElement();
        node1.setElement(node2.getElement());
        node2.setElement(temp);
    }

    /*
        Gets the depth of a given node in the tree
        @parama TreeNode<T> node: node to have the depth calculated for
        @return int 0: the node given is the root
        @return int 1 + this.getDepth(node.getParent()): returns the depth of the parent of node plus 1
    */
    public int getDepth(TreeNode<T> node){
        // if node is root, the depth is 0
        if(node.isRoot()){
            return 0;
        }
        else{
            return 1 + this.getDepth(node.getParent());
        }
    }

    /*
        Gets the hight of a given node in the tree
        @parama TreeNode<T> node: the node to have the height calculated for
        @return 0: the node is a leaf
        @return 1 + h: 1 plus the hight of nodes highest child
    */
    public int getHeight(TreeNode<T> node){
        // if the node is a leaf, the height is 0
        if(!node.isInternal()){
            return 0;
        }
        else{
            int h = 0; // height of the node
            // calulate the height of each child of the node and take the max
            for(TreeNode<T> n : node.getChildren()){
                h = Math.max(h, this.getHeight(n));
            }

            return 1 + h;
        }
    }

    /*
        Gets the Nodes in the tree in preorder
        @parama TreeNode<T> node: the Node to start at
        @return TreeNode<t> null: the node given is null
        @return ArrayList<TreeNode<T>> nodes: the nodes in the tree in preorder
    */
    public ArrayList<TreeNode<T>> preorder(TreeNode<T> node){
        if(node == null){
            return null;
        }

        ArrayList<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
        nodes.add(node);
        if(node.isInternal()){
            nodes.addAll(preorder(node.getLeftChild()));
            nodes.addAll(preorder(node.getRightChild()));
        }

        return nodes;
    }

    /*
        Gets the Nodes in the tree in postorder
        @parama TreeNode<T> node: the Node to start at
        @return ArrayList<TreeNode<T>> null: the tree is empty
        @return ArrayList<TreeNode<T>> nodes: the nodes in the tree in postorder
    */
    public ArrayList<TreeNode<T>> postorder(TreeNode<T> node){
        if(node == null){
            return null;
        }

        ArrayList<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
        if(node.isInternal()){
            nodes.addAll(postorder(node.getLeftChild()));
            nodes.addAll(postorder(node.getRightChild()));
        }

        nodes.add(node);
        return nodes;        
    }

    /*
        Gets the Nodes in the tree in inorder
        @parama TreeNode<T> node: the Node to start at
        @return ArrayList<TreeNode<T>> null: the tree is empty
        @return ArrayList<TreeNode<T>> nodes: the nodes in the tree in inorder
    */
    public ArrayList<TreeNode<T>> inorder(TreeNode<T> node){
        if(node == null){
            return null;
        }

        ArrayList<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
        if(!node.isInternal()){
            return nodes;
        }

        if(node.isInternal()){
            nodes.addAll(inorder(node.getLeftChild()));
        }

        nodes.add(node);

        if(node.isInternal()){
            nodes.addAll(inorder(node.getRightChild()));
        }

        return nodes;
    }

    /*
        Searches for an element within a given tree
        @parama T e: element to find
        @parama TreeNode<T> startNode: The root node for the tree to be searched through
        @return TreeNode<T> null: The tree given in empty
        @return TreeNode<T> startNode: the node given is a leaf or the contains the element being searched for
        @return TreeNode<T> search(e, startNode.getLeftChild()): searches for the node in the left subtree
        @return TreeNode<T> search(e, startNode.getRightChild()): searches for the element in the right subtree
    */
    public TreeNode<T> search(T e, TreeNode<T> startNode){
        // tree is empty
        if(startNode == null){
            return null;
        }

        // node given is a leaf
        if(!startNode.isInternal()){
            return startNode;
        }

        // element in the start node is the element being searched for
        if(e.equals(startNode.getElement())){
            return startNode;
        }

        // if element is less that the element in the start node search down left subtree
        else if(e.compareTo(startNode.getElement()) < 0){
            return search(e, startNode.getLeftChild());
        }

        // else seach down right subtree
        else{
            return search(e, startNode.getRightChild());
        }
    }

    /*
        Returns the tree as a string of the lement in order
        @return String "": The tree is empty
        @return String string: The Tree elements in order
    */
    public String toString(){
        String string = "";

        // if tree is empty return blank string
        if(this.getElements() == null){
            return "";
        }

        // build string of elements to 
        for(T e : this.getElements()){
            string += e.toString();
            string += " ";
        }

        return string;
    }
}