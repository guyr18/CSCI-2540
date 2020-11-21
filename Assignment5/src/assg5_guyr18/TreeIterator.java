package assg5_guyr18;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeIterator<Customer> implements java.util.Iterator<Customer> 
{
	
	private BinaryTreeBasis<Customer> binTree;
	private TreeNode<Customer> currentNode;
	private LinkedList <TreeNode<Customer>> queue; // from JCF

	public TreeIterator(BinaryTreeBasis<Customer> bTree) 
	{
		
		binTree = bTree;
		currentNode = null;
		// empty queue indicates no traversal type currently
		// selected or end of current traversal has been reached
		queue = new LinkedList <TreeNode<Customer>>();
	}  // end constructor

	public boolean hasNext() 
	{
		
		return !queue.isEmpty();
	}  	// end hasNext

	public Customer next() throws java.util.NoSuchElementException 
	{
		
		currentNode = queue.remove();
		return currentNode.item;
	}  	// end next

	public void remove() throws UnsupportedOperationException 
	{
		
		throw new UnsupportedOperationException();
	}  // end remove

	public void setPreorder() 
	{
		
		queue.clear();
		preorder(binTree.root);
	}  // setPreOrder

	public void setInorder()
	{
		
		queue.clear();
		inorder(binTree.root);
	}  // end setInorder

	public void setPostorder() 
	{
		
		queue.clear();
		postorder(binTree.root);
	}  // end setPostorder

	private void preorder(TreeNode<Customer> treeNode) 
	{
		
		if (treeNode != null) 
		{
			
			queue.add(treeNode);
			preorder(treeNode.leftChild);
			preorder(treeNode.rightChild);
		} // end if
	}  // end preorder

  private void inorder(TreeNode<Customer> treeNode)
  {
	  
	  if (treeNode != null) 
	  {

		  inorder(treeNode.leftChild);
		  queue.add(treeNode);
		  inorder(treeNode.rightChild);
	  } // end if
  }  // end inorder

  private void postorder(TreeNode<Customer> treeNode) 
  {
	  
	  if (treeNode != null) 
	  {
		  
		  postorder(treeNode.leftChild);
		  postorder(treeNode.rightChild);
		  queue.add(treeNode);
	  } // end if
  }  // end postorder
}  // end TreeIterator