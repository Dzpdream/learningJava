class Node{
	Node left,right;
	int data;
	public Node(int value){
		this.data = value;
	}
	
	public void insert(int value){
		if(value <= data){
			if(left == null){
				left = new Node(value);
			}else{
				left.insert(value);
			}
		}else {
			if(right == null){
				right = new Node(value);
			}else{
				right.insert(value);
			}
		}
	}
	
	public boolean contains(int value){
		if(data == value)
			return true;
		else if(value < data){
			if(left == null)
				return false;
			else return left.contains(value);
		}
		else{
			if(right == null)
				return false;
			else return right.contains(values);
		}
	}
	
	//先序遍历
	public void printFirstTree(){
		if(left != null)
			left.printFirstTree();
		System.out.println(data);
		if(right != null)
			right.printFirstTree();
	}
	
	//中序遍历
	public void printMidTree(){
		System.out.println(data);
		if(left != null)
			left.printMidTree();
		if(right != null)
			right.printMidTree();
	}
	
	//后序遍历
	public void printLastTree(){
		if(left != null)
			left.printLastTree();
		if(right != null)
			right.printLastTree();
		System.out.println(data);
	}
	
	
	
	
	
	
}