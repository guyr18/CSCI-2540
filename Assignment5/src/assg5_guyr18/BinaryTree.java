package assg5_guyr18;

public class BinaryTree<Customer> extends BinaryTreeBasis<Customer> 
{

  public BinaryTree() 
  {
  }  // end default constructor

  public BinaryTree(Customer rootItem) 
  {
	  
	  super(rootItem);
    
  }  // end constructor

  public BinaryTree(Customer rootItem, BinaryTree<Customer> leftTree, BinaryTree<Customer> rightTree)
  {
	  
	  root = new TreeNode<Customer>(rootItem, null, null);
	  attachLeftSubtree(leftTree);
	  attachRightSubtree(rightTree);
    
  }  // end constructor

  public void setRootItem(Customer newItem) 
  {
	  
	  if(root != null) 
	  {
		  
		  root.item = newItem;
		  
	  }
	  else 
	  {
		  
		  root = new TreeNode<Customer>(newItem, null, null);
		  
	  }  // end if
  }  // end setRootItem

  public void attachLeft(Customer newItem) 
  {
	  
	  if(!isEmpty() && root.leftChild == null)
	  {
		  // assertion: nonempty tree; no left child
		  root.leftChild = new TreeNode<Customer>(newItem, null, null);
      
	  }  // end if
  }  // end attachLeft

  public void attachRight(Customer newItem) 
  {
	  
	  if (!isEmpty() && root.rightChild == null) 
	  {
		  // assertion: nonempty tree; no right child
		  root.rightChild = new TreeNode<Customer>(newItem, null, null);
      
	  }  // end if
  }  // end attachRight

  public void attachLeftSubtree(BinaryTree<Customer> leftTree) throws TreeException
  {
                                
	  if (isEmpty()) 
	  {
		  
		  throw new TreeException("TreeException:  Empty tree");
		  
	  }
	  else if (root.leftChild != null) 
	  {
		  
		  // a left subtree already exists; it should have been
		  // deleted first
		  throw new TreeException("TreeException: " + "Cannot overwrite left subtree");
		  
	  }
	  else 
	  {
		  // assertion: nonempty tree; no left child
		  root.leftChild = leftTree.root;
		  // don't want to leave multiple entry points into
		  // our tree
		  leftTree.makeEmpty();
	  }  	// end if
  	}  	// end attachLeftSubtree

  public void attachRightSubtree(BinaryTree<Customer> rightTree) throws TreeException
  {
                                 
	  if (isEmpty()) 
	  {
		  
		  throw new TreeException("TreeException:  Empty tree");
	  }
	  else if (root.rightChild != null) 
	  {
		  
		  // a right subtree already exists; it should have been
		  // deleted first
		  throw new TreeException("TreeException: " + "Cannot overwrite right subtree");
	  }
	  else 
	  {
		  
		  // assertion: nonempty tree; no right child
		  root.rightChild = rightTree.root;
		  
		  // don't want to leave multiple entry points into
		  // our tree
		  rightTree.makeEmpty();
    }  	 // end if
  }     // end attachRightSubtree

  protected BinaryTree(TreeNode<Customer> rootNode) 
  {
	  
	  root = rootNode;
	  
  }  // end protected constructor

  public BinaryTree<Customer> detachLeftSubtree() throws TreeException
  {
                         
	  if (isEmpty()) 
	  {
		  
		  throw new TreeException("TreeException:  Empty tree");
	  }
	  else 
	  {
		  // create a new binary tree that has root's left
		  // node as its root
		  BinaryTree<Customer> leftTree;
		  leftTree = new BinaryTree<Customer>(root.leftChild);
		  root.leftChild = null;
		  return leftTree;
	  }   // end if
  	}  	 // end detachLeftSubtree

  public BinaryTree<Customer> detachRightSubtree() throws TreeException
  {
                        
	  if (isEmpty()) 
	  {
		  
		  throw new TreeException("TreeException:  Empty tree");
	  }
	  else 
	  {
		  
		  BinaryTree <Customer> rightTree;
		  rightTree = new BinaryTree<Customer>(root.rightChild);
		  root.rightChild = null;
		  return rightTree;
	  }  // end if
  }  	// end detachRightSubtree
} 		// end BinaryTree
