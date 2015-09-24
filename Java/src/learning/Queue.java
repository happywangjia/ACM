package learning;

public class Queue {
	private Che font;
	private Che rear;
	private int size;
	public Queue(){
		font=new Che();
		font.setCdTime("font");
		rear=new Che();
		font.setCdTime("rear");
		font.setNext(rear);;
		rear.setLast(font);
		size=0;
	}
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		return false;
	}
	public boolean isFull(int n){
		if(size==n) return true;
		return false;
	}
	public boolean push(Che x,int n){
		if(isFull(n)){
			return false;
		}
		Che che=new Che();
		che=x;
		rear.getLast().setNext(che);
		che.setLast(rear.getLast());
		che.setNext(rear);
		rear.setLast(che);
		size++;
		System.out.println(che.getJkTime()+"  "+che.getCkTime()+"  "+che.getJdTime()+"  "+che.getCdTime()+"  "+che.getId());
		return true;
	}
	public Che pop(String id){
		if(isEmpty()){
			return null;
		}
		Che che=new Che();
		che=font.getNext();
		while(che!=rear&&!che.getId().equals(id)){	
			che=che.getNext();
		}
		if(che==rear) return null;
		if(che.getLast()!=null){
			che.getLast().setNext(che.getNext());
		}
		if(che.getNext()!=null)
			che.getNext().setLast(che.getLast());
		size--;
		return che;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Che getFont() {
		return font;
	}
	public void setFont(Che font) {
		this.font = font;
	}
	public Che getRear() {
		return rear;
	}
	public void setRear(Che rear) {
		this.rear = rear;
	}
}
