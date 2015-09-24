package algorithms;

/**
 * @tag:栈
 * @link:
 * @Num:N_004.java
 * @author hijj
 * Create at: 2015年9月16日 下午1:54:21
 */
public class N_004<T> {
	private T data;
	private N_004<T> next;
	public N_004(){
		this.next=null;
	}
	public N_004(T data){
		this.data=data;
		this.next=null;
	}
	public void push(T data){
		N_004<T> st=new N_004<T>();
		st.data=this.data;
		st.next=this.next;
		this.data=data;
		this.next=st;
	}
	public boolean pop(){
		if(this.next==null){
			return false;
		}
		this.data=(T) this.next.data;
		this.next=this.next.next;
		return true;
	}
	public T top(){
		if(this.next==null){
			return null;
		}
		else
			return this.data;
	}
	public N_004<T> getNext(){
		return this.next;
	}
	public T getData(){
		if(this.next==null){
			return null;
		}
		return this.data;
	}
	public boolean isEmpty(){
		if(this.next==null)
			return true;
		else return false;
	}
	
}
