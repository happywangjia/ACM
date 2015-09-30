package learning;

public class Stack {
	private Che[] shu=new Che[100];
	private int size;
	
	public Stack(){
		size=0;
	}
	public boolean isEmpty(){
		if(size==0) return true;
		return false;
	}
	public boolean isFull(int n){
		if(n==size) return true;
		return false;
	}
	
	public boolean push(Che x,int n){
		if(isFull(n)) return false;
		Che che=new Che();
		che=x;
		shu[size]=che;
		size++;
//		System.out.println("stack:"+shu[size-1].getJkTime());
		return true;
	}
	public Che pop(String id){
		if(isEmpty()) return null;
		boolean flag=false;
		Che pp = new Che();
		for(int i=0;i<size;i++){
			if(flag==false&&shu[i].getId().equals(id)){
				flag=true;
				pp=shu[i];
				continue;
			}
			if(flag){
				shu[i-1]=shu[i];
			}
		}
		System.out.println("flag:"+flag);
		if(flag==false) return null;
		System.out.println("stack "+pp);
		size--;
		return pp;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Che getTop() {
		return shu[size-1];
	}
}
