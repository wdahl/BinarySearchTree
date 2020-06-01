import java.util.ArrayList;

/*
    Driver for the BinarySeachTree class 
*/
public class BinarySearchTreeDriver{
    public static void main(String[] args){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(); // Instatiates a new Binary Search Tree Containg Integers

        // builds the intial tree
        tree.add(44);
        tree.add(17);
        tree.add(88);
        tree.add(32);
        tree.add(65);
        tree.add(97);
        tree.add(28);
        tree.add(54);
        tree.add(82);
        tree.add(29);
        tree.add(76);
        tree.add(80);

        // prints out order list of tree
        System.out.println(tree);

        tree.add(78); // adds 78 to the tree

        System.out.println(tree); // prints out new tree

        tree.remove(32); // removes 32 from the tree

        System.out.println(tree); // prints out new tree

        tree.remove(65); // removes 65 from the tree

        System.out.println(tree); // prints out new tree

        tree.remove(44); // removes root

        System.out.println(tree);

        tree.add(76);

        System.out.println(tree);

        tree.remove(76);

        System.out.println(tree);
    }
}