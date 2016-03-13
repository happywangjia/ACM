package learning.jClass1;

public class jClass1 {
	public static void main(String[] args){
		for(int i=2;i<=1000;i++){
			int k=(int) Math.sqrt(i);
			int sum=0;
			if(k*k==i){
				sum+=k;
				k--;
			}
			for(;k>1;k--){
				if(i%k==0){
					sum+=k;
					sum+=i/k;
				}
			}
			sum+=1;
			if(sum==i)
				System.out.println("数 "+i);
		}
	}
}
