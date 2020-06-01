import java.util.ArrayList;

/*  
    Generic Class of Node type that mkaes up a Tree 
    Generic type must implement the Comparable Interface 
*/
public class TreeNode<T extends Comparable<T>>{
    private T element; // element stored in node
    private TreeNode<T> parent; // parent of node
    private TreeNode<T> leftChild; // left child of node
    private TreeNode<T> rightChild; // right child of node

    /*
        TreeNode Constructor
        @parama TreeNode<T> p: parent node of the node being created
    */
    public TreeNode(TreeNode<T> p){
        this.element = null;
        this.parent = p;
        this.leftChild = null;
        this.rightChild = null;
    }

    /*
        TreeNode Contructor
        @parama T e: element to be stored in the node
    */
    public TreeNode(T e){
        this.element = e;
        this.parent = null;
        this.leftChild = new TreeNode<T>(this);
        this.rightChild = new TreeNode<T>(this);
    }

    /*
        Gets element in node
        @return T this.element
    */
    public T getElement(){
        return this.element;
    }

    /*
        Gets Parent of node
        @return TreeNode<T> this.parent
    */
    public TreeNode<T> getParent(){
        return this.parent;
    }

    /*
        Gets left child of node
        @return TreeNode<T> this.leftChild
    */
    public TreeNode<T> getLeftChild(){
        return this.leftChild;
    }

    /*
        Gets right child of node
        @return TreeNode<T> this.rightChild
    */
    public TreeNode<T> getRightChild(){
        return this.rightChild;
    }

    /*
        Sets the elment of the node
        @parama T e: element to be stored in node
    */
    public void setElement(T e){
        this.element = e;
    }

    /*
        Sets the parent of the node
        @parama TreeNode<T> p: node to be set as the parent
    */
    public void setParent(TreeNode<T> p){
        this.parent = p;
    }

    /*
        Sets the left child of the node
        @parama TreeNode<T> l: node to be set as the left child
    */
    public void setLeftChild(TreeNode<T> l){
        this.leftChild = l;
    }

    /*
        Sets the right child of the node
        @parama TreeNode<T> r: node to be set as the right child
    */
    public void setRightChild(TreeNode<T> r){
        this.rightChild = r;
    }

    /*
        Gets the children of the node
        @return ArrayList<TreeNode<T>> children: list of the children of the node
    */
    public ArrayList<TreeNode<T>> getChildren(){
        ArrayList<TreeNode<T>> children = new ArrayList<TreeNode<T>>(2);
        children.add(this.leftChild);
        children.add(this.rightChild);
        return children;
    }

    /*
        Checks if node is an Internal node
        @return boolean true: if the node is an internal node
        @return boolean false: if the node is an external node
    */
    public boolean isInternal(){
        // If the node is an internal node then either the left child or right child will not be null
        if(this.leftChild != null || this.rightChild != null){
            return true;
        }

        return false;
    }

    /*
        Checks if the node is the root of the tree it is in
        @return boolean true: the node is the root of the tree
        @return boolean false: the node is not the root of the tree
    */
    public boolean isRoot(){
        // The node will be the root of the tree if it does not have a parent
        if(this.parent == null){
            return true;
        }

        return false;
    }

    /*
        Gets the sibling of the node
        @return TreeNode<T> null: the node has no sibling
        @return TreeNode<T> this.parent.getLeftChild(): The sibling of the node is the left child of the nodes parent
        @return TreeNode<T> this.parent.getRightChild(): The sibling of the node is the right child of the nodes parent
    */
    public TreeNode<T> getSibling(){
        // If the node has no parent is does not have a sibling
        if(this.parent == null){
            return null;
        }

        // If the node is the leftchild of the its parent then its sibling is the left child of its parent
        else if(this.parent.getLeftChild() != this){
            return this.parent.getLeftChild();
        }

        return this.parent.getRightChild();
    }

    /*
        Checks if the node is the left child of it parent
        @return boolean true: the node is the left child of its parent
        @return boolean false: the node is the right child of its parent
    */
    public boolean isLeftChild(){
        // If the node is the left child of the parent, the parent left child will equal this node
        if(this.parent.getLeftChild() == this){
            return true;
        }

        return false;
    }
}