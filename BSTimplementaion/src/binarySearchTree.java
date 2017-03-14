import java.util.Stack;

class Node{
int data;
Node left,right;
int rank;//number of left child+1

//constructors
public Node()//default constructor
{
	left=null;
	right=null;
	data=0;
	rank=1;
}

public Node(int n)//constructor with value
{
	left=null;
	right=null;
	data=n;
	rank=1;
}	
}
 class BST{

	private Node root;
	
	public BST()
	{
		root = null;
	}
	//INSERT
	public void insert(int data)
	{
		root= insert(root,data);
	}
	
	public Node insert(Node node,int data)
	{ 
		if(node== null)
			node = new Node(data);
		else
		{
			if(data<=node.data){
				node.rank++;
				node.left=insert(node.left,data);
				
			}
			else
				node.right=insert(node.right,data);
		}
		
		return node;
	}
	
	//search
	public boolean search(int data)
	{
		 return search(root,data);
	}
	
	private boolean search(Node node,int data)
	{ 
		boolean res=false;
		if(node== null)
			res= false;
		else if(node.data==data)
			res= true;
		else
		{
			if(data<node.data)
				res=search(node.left,data);
			else if(data>node.data)
				res=search(node.right,data);
		}
		
		return res;
	}
	//Delete
	public void delete(int key)
	{
		root=delete(root,key);
	}
	 private Node delete(Node node,int key)
	 { 
		if(node==null) return node;
		
		if(node.data>key){
			node.rank--;
			node.left=delete(node.left,key);
			}
		else if(node.data<key)
			node.right=delete(node.right,key);
		else
		{
			if(node.left==null)return node.right;
			else if(node.right==null)return node.left;
			else
				
			{
			node.data=minval(node.right);
			node.right=delete(node.right,node.data);
			
			}
		}
		
		 return node;
	 }
	 
	 private int minval(Node root)
	    {
	        int minv = root.data;
	        while (root.left != null)
	        {
	            minv = root.left.data;
	            root = root.left;
	        }
	        return minv;
	    }
	
	//Traversals
	
	public void inorder(){
		inorder(root);
	}
	private void inorder(Node r)
	{
		if(r!=null)
		{
		inorder(r.left);
		System.out.print(r.data+" ");
		inorder(r.right);
		}
		
	}
	public void preorder(){
		preorder(root);
	}
	private void preorder(Node r)
	{
		if(r!=null)
		{
		System.out.print(r.data+" ");
		preorder(r.left);
		preorder(r.right);
		}
		
	}
	public void postorder(){
		postorder(root);
	}
	private void postorder(Node r)
	{
		if(r!=null)
		{
		postorder(r.left);
		postorder(r.right);
		System.out.print(r.data+" ");
		}
		
	}
	
	//finding inorder successor and predessor
	public void successorAndPredecessor(int key)
	{
		if(search(key)){
		System.out.print("inorder successor of "+key+" is: ");
		inorderSuccessor(root,key);
		System.out.print("inorder predecessor of "+key+" is: ");
		inorderPredecessor(root,key);
		}
		else//if value is not in the tree then print  smaller and larger value present in the tree.
		{
			
			int small=-1,big=-1;
			Node temp=root;
			while(temp!=null)
			{
				if(temp.data>key){
					big=temp.data;
					temp=temp.left;
					}
				else 
				{
					small=temp.data;
					temp=temp.right;
				}
				
			}
			System.out.println("value present in tree that is smaller than "+key+" is "+small);
			System.out.println("value present in tree that is larger than "+key+" is "+big);
			
		}
	}
	private void inorderSuccessor(Node root,int key){
		Stack<Integer> st = new Stack<Integer>();
		while(root.data!=key)
		{
			st.push(root.data);
			if(root.data>key)
				root=root.left;
			else if(root.data<key)
				root=root.right;
		}
		if(root.right==null)
		{
			if(!st.empty()){
				while(st.peek()<key)
					st.pop();
				System.out.println(st.pop());
			}
			else
				System.out.println(root.data);
			
		}
		else
		{
			if(root.right.left!=null)
			{
				Node temp=root.right;
				while(temp.left!=null)
					temp=temp.left;
				System.out.println(temp.data);
				
			}
			else
				System.out.println(root.right.data);
		}
	}
	
	
	
	private void inorderPredecessor(Node root,int key){
		Stack<Integer> st = new Stack<Integer>();
		while(root.data!=key)
		{
			st.push(root.data);
			if(root.data>key)
				root=root.left;
			else if(root.data<key)
				root=root.right;
		}
		if(root.left==null)
		{
			if(!st.empty()){
				while(st.peek()>key)
					st.pop();
				System.out.println(st.pop());
			}
			else
				System.out.println(root.data);
			
		}
		else
		{
			if(root.left.right!=null)
			{
				Node temp=root.left;
				while(temp.right!=null)
					temp=temp.right;
				System.out.println(temp.data);
				
			}
			else
				System.out.println(root.left.data);
		}
	}
	
	//lowest common ancestor
	public void ancestor(int key1,int key2)
	{
		ancestor(root,key1,key2);
	}
	private void ancestor(Node root,int key1,int key2)
	{
		int lca=-1;
		while(true)
		{
			if(root.data==key1){
				lca=key1;
			     break;}
		   else if(root.data==key2){
			   lca=key2;
				break;}
		   else if(root.data>key2)
				root=root.left;
			else if(root.data<key1)
				root=root.right;
			else
			{
				
				lca=root.data;
					break;
			}
		}
		System.out.println(lca);
	}
	
	public void kthSmallest(int k)
	{
		kthSmallest(root,k);
	}
	private void kthSmallest(Node node,int k)
	{
		if(node.rank==k)
			System.out.println(node.data); 
		if(node.rank>k)
			kthSmallest(node.left,k);
		else if(node.rank<k){
		 k=k-node.rank;	
			kthSmallest(node.right,k);
		}
	}
	
	public void merge(BST tree2)
	{
		merge(root,tree2.root);
	}
	
	public void merge(Node root1,Node root2)
	{
		Stack<Node> s1 = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();
		Node temp=null;
	
		//go to extreme left
		while(root1.left!=null){
			s1.push(root1);
			root1=root1.left;
			}
		s1.push(root1);
		while(root2.left!=null){
			s2.push(root2);
			root2=root2.left;
			}
		s2.push(root2);
		
		while(true)
		{
			
			if(s1.empty()){
				Node t=s2.pop();
				inorder(t);
				break;
			}
			else if(s2.empty()){
				Node t=s1.pop();
				inorder(t);
				break;
			}
				
		else if(s1.peek().data>s2.peek().data)
			{
				temp = s2.pop();
				System.out.print(temp.data+" ");
				if(temp.right!=null)
				{
					s2.push(temp.right);
					while(temp.right.left!=null){
						s2.push(temp.right.left);
						temp.right.left=temp.right.left.left;
						}
					
				}
			}	
				else
				{
					temp = s1.pop();
					System.out.print(temp.data+" ");
					if(temp.right!=null)
					{
						s1.push(temp.right);
						Node k=temp.right;
						while(k.left!=null){
							s1.push(k.left);
							k=k.left;
							}
						
					}
					
				}
				
				
			
		}
}

 }
public class binarySearchTree {
	public static void main(String[] args) {
		
		/*BST tree = new BST();
		tree.insert(20);
		tree.insert(8);
		tree.insert(4);
		tree.insert(12);
		tree.insert(10);
		tree.insert(14);
		tree.insert(22);
		tree.kthSmallest(6);
		tree.delete(12);
		tree.kthSmallest(6);*/
		
		//tree.successorAndPredecessor(10);
		//tree.successorAndPredecessor(22);
		//tree.ancestor(4,20);
		
		
		/*System.out.println("inorder");
		tree.inorder();
		System.out.println("hey");
		tree.delete(20);
		tree.inorder();
		System.out.println("hy");*/
		//System.out.println(tree.search(4));
		BST tree1 = new BST();
		tree1.insert(8);
		tree1.insert(10);
		tree1.insert(4);
		tree1.insert(6);
		tree1.insert(5);
		tree1.insert(7);
		tree1.insert(1);
		
		BST tree2 = new BST();
		tree2.insert(9);
		tree2.insert(11);
		tree2.insert(12);
		tree2.insert(13);
		tree2.insert(3);
		tree2.insert(0);
		tree2.insert(2);
		
		
		tree1.merge(tree2);
	}

}
