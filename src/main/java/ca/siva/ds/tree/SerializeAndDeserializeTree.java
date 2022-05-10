package ca.siva.ds.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeAndDeserializeTree {
    Integer leafRef = Integer.MIN_VALUE;

    public void serialize(BinaryTreeNode node, ObjectOutputStream stream) throws IOException {
        if (node == null) {
            stream.writeInt(leafRef);
            return;
        }
        stream.writeInt(node.data);
        serialize(node.left, stream);
        serialize(node.right, stream);
    }

    public BinaryTreeNode deSerialize(ObjectInputStream stream) throws IOException {

        int val = stream.readInt();

        if (val == Integer.MIN_VALUE) {
            return null;
        }
        BinaryTreeNode node = new BinaryTreeNode(val);
        node.left = deSerialize(stream);
        node.right = deSerialize(stream);
        return node;
    }

    public static void main(String[] args){
        try {
            List<Integer> input = new ArrayList<>();
            input.add(100);
            input.add(50);
            input.add(200);
            input.add(25);
            input.add(75);
            input.add(125);
            input.add(350);
            BinaryTreeNode root = BinaryTree.createBST(input);
            BinaryTree.displayLevelOrder(root);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            SerializeAndDeserializeTree obj = new SerializeAndDeserializeTree();
            obj.serialize(root, objectOutputStream);
//            System.out.println(objectOutputStream);
            objectOutputStream.close();

//            System.out.println(outputStream.toByteArray());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            BinaryTreeNode newRoot = obj.deSerialize(objectInputStream);
            BinaryTree.displayLevelOrder(newRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
