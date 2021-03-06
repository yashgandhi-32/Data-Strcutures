package l16_17;
import java.util.Scanner;

import l15.QueueEmptyException;
import l15.QueueUsingLinkedList;

class Pair<T, V> {
	T first;
	V second;
	
	public Pair(T first, V second) {
		this.first = first;
		this.second = second;
	}
	
}


public class TreeUse {

	// 1 2 3 4 5 6 7 -1 -1 -1 -1 -1 -1 -1 -1
	public static TreeNode<Integer> takeTreeInputLevelWise() {
		System.out.println("Enter root data");
		Scanner s = new Scanner(System.in);
		int rootData = s.nextInt();
		if (rootData == -1) {
			return null;
		}

		TreeNode<Integer> root = new TreeNode<>(rootData);
		QueueUsingLinkedList<TreeNode<Integer>> queue = new QueueUsingLinkedList<>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			TreeNode<Integer> parent;
			try {
				parent = queue.dequeue();
			} catch (QueueEmptyException e) {
				return null;
			}
			System.out.println("Enter left child of " + parent.data);
			int leftData = s.nextInt();
			if (leftData != -1) {
				TreeNode<Integer> leftChild = new TreeNode<Integer>(leftData);
				queue.enqueue(leftChild);
				parent.left = leftChild;
			}

			System.out.println("Enter right child of " + parent.data);
			int rightData = s.nextInt();
			if (rightData != -1) {
				TreeNode<Integer> rightChild = new TreeNode<Integer>(rightData);
				queue.enqueue(rightChild);
				parent.right = rightChild;
			}
		}
		return root;
	}

	public static int height(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(height(root.left), height(root.right));
	}
	
	public static Pair<Integer, Boolean> heightAndBalanced(TreeNode<Integer> root) {
		if (root == null) {
			Pair<Integer, Boolean> output = new Pair<>(0, true);
			return output;
		}
		Pair<Integer, Boolean> leftOutput = heightAndBalanced(root.left);
		Pair<Integer, Boolean> rightOutput = heightAndBalanced(root.right);
		int height = 1 + Math.max(leftOutput.first, rightOutput.first);
		boolean isBalanced = 
				leftOutput.second && rightOutput.second 
				&& (Math.abs(leftOutput.first - rightOutput.first) <= 1);
		Pair<Integer, Boolean> output = new Pair<>(height, isBalanced);
		return output;
	}
	
	public static boolean isBalanced(TreeNode<Integer> root) {
		Pair<Integer, Boolean> output = heightAndBalanced(root);
		return output.second;
//		if (root == null) {
//			return true;
//		}
//		int lh = height(root.left);
//		int rh = height(root.right);
//		if (lh - rh > 1 || rh - lh > 1) {
//			return false;
//		}
//		return isBalanced(root.left) && isBalanced(root.right);
	}
	
	// 1 2 4 -1 -1 5 -1 -1 3 6 -1 -1 7 -1 -1 
	public static TreeNode<Integer> takeTreeInput(Scanner s) {
		System.out.println("Enter root data");
		int rootData = s.nextInt();
		if (rootData == -1) {
			return null;
		}

		TreeNode<Integer> root = new TreeNode<>(rootData);
		root.left = takeTreeInput(s);
		root.right = takeTreeInput(s);
		return root;
	}

	public static void inorder(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		System.out.println(root.data);
		inorder(root.right);
	}
	
	public static void postorder(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}
		postorder(root.left);
		postorder(root.right);
		System.out.println(root.data);
	}
	
	public static void preorder(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}
		System.out.println(root.data);
		preorder(root.left);
		preorder(root.right);
	}
	
	public static void print(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + ":");
		if (root.left != null) {
			System.out.print(root.left.data + ",");
			
		}
		if (root.right != null) {
			System.out.print(root.right.data + ",");
			
		}
		System.out.println();

		print(root.left);
		print(root.right);
	}
	
	public static int sum(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return sum(root.left) + sum(root.right) + root.data;
	}
	
	public static int numberNodes(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return numberNodes(root.left) + numberNodes(root.right) + 1;
	}

	public static void main(String[] args) {
		TreeNode<Integer> root = takeTreeInputLevelWise();
		print(root);
	}

}
