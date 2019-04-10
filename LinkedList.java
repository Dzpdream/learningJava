//LinkedList 为双链表结构

//节点结构
private static Node<E>{
	E item;
	Node<E> pre;
	Node<E> next;
	Node(Node<E> pre,E item, Node<E> next){
		this.pre=pre;
		this.item=item;
		this.next=next;
	}
}

//几个重要方法

//removeFirst()
public E removeFirst(){
	final Node<E> f = first;
	if(f==null)
		throw new NoSuchElementException;
	return unlinkFirst(f);
}

public unlinkFirst(Node<E> f){
	final E element = f.item;
	final Node<E> next = f.next;
	f.item = null;
	f.next = null;
	first = next;
	if(next == null)
		last=null;
	else next.pre=null;
	size--;
	modCount++;
	return element;
}
--------------------------------------------------------------------------

//removeLast
public E removeLast(){
	final Node<E> f = last;
	if(f == null)
		throw new NoSuchElementException;
	return unlinkLast(f);
}

public E unlinkLast(Node<E> f){
	final E element = f.item;
	final Node<E> pre = f.pre;
	f.item=null;
	f.pre=null;
	last = pre;
	if(pre == null)
		first = null
	else pre.next = null;
	size--;
	modCount++;
	return element;
}

--------------------------------------------------------
//addFirst  
public void addFirst(E e){
	linkFirst(e);
}
public void linkFirst(E e){
	final Node<E> f = first;
	final Node<E> ln = new Node<E>(null,e,f);
	first = ln;
	if(f == null)
		last = ln;
	else
		f.pre = ln;
	size++;
	modCount++;
}
//addLast同理
---------------------------------------------------------
//contains

public boolean contains(Object o){
	return indexOf(o) != -1;
}
public boolean indexOf(Object o){
	int index = 0;
	if(o == null){
		for(Node<E> x=first; x!=null; x=x.next){
			if(x.item == null)
				return index;
			index ++;
		}
	}else {
		for(Node<E> x=first; x!=null; x=x.next){
			if(o.equals(x.item))
				return index;
			index++;
		}
	}
	return -1;
}

-------------------------------------------------------------------------
//remove(Object o)移除某元素，首先找到是否包含该元素，然后移除
public boolean remove(Object o){
	if(o==null){
		for(Node<E> x=first; x!=null; x=x.next){
			if(x.item == null){
				unlink(x);
				return true;
			}
		}
	}else{
		for(Node<E> x=first; x!=null; x=x.next){
			if(o.equals(x.item)){
				unlink(x);
				return true;
			}
		}
	}
	return false;
}

public E unlink(Node<E> x){
	final E element = x.item;
	final Node<E> pre = x.pre;
	final Node<E> next = x.next;
	if(pre == null){
		first = next;
	}else{
		pre.next=next;
		x.pre = null;
	}
	
	if(next == null){
		last = pre;
	}else{
		next.pre = pre;
		x.next = null;
	}
	size--;
	modCount++;
	return element;
}
----------------------------------------------------------------

//get
public E get(int index){
	checkElementIndex(index); //检查是否越界
	return node(index).item;
}
//底层为双链表,看给出的index靠近头还是尾来进行遍历查找,linkedlist查找的效率很低原因就是必须得从头或者从尾遍历
public Node<E> node(int index){
	if(index<(size >> 1)){
		Node<E> x = first;
		for(int i=0; i<index; i++){
			x = x.next;
		}
		return x;
	}else{
		Node<E> x = last;
		for(int i=size-1; i>index; i--){
			x = x.pre;
		}
		return x;
	}
}

------------------------------------------------------------------------------------------


























