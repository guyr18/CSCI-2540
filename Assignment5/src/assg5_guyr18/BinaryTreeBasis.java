package assg5_guyr18;

class TreeNode<Customer> 
{
	
	Customer item;
	TreeNode<Customer> leftChild;
	TreeNode<Customer> rightChild;

	public TreeNode(Customer newItem) 
	{
		// Initializes tree node with item and no children.
		item = newItem;
		leftChild  = null;
		rightChild = null;
	}  	// end constructor

	public TreeNode(Customer newItem, TreeNode<Customer> left, TreeNode<Customer> right)
	{
		
		// Initializes tree node with item and
		// the left and right children references.
		item = newItem;
		leftChild  = left;
		rightChild = right;
	}  	// end constructor
}  // end TreeNode

	public abstract class BinaryTreeBasis<Customer> 
	{
		
		protected TreeNode<Customer> root;

		public BinaryTreeBasis() 
		{
			
			root = null;
		}  // end default constructor

		public BinaryTreeBasis(Customer rootItem) 
		{
			
			root = new TreeNode<Customer>(rootItem, null, null);
		}  // end constructor

		public boolean isEmpty() 
		{
			// Returns true if the tree is empty, else returns false.
			return root == null;
		}  // end isEmpty

		public void makeEmpty() 
		{
			// Removes all nodes from the tree.
			root = null;
		}  // end makeEmpty

		public Customer getRootItem() throws TreeException 
		{
			
			// Returns the item in the tree's root.
			if (root == null) 
			{
				
				throw new TreeException("TreeException: Empty tree");
				
			}
			else
			{
				
				return root.item;
			}  // end if
		}  // end getRootItem

		public abstract void setRootItem(Customer newItem);
		// Throws UnsupportedOperationException if operation
		// is not supported.

}  // end BinaryTreeBasis
