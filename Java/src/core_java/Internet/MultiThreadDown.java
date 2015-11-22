package core_java.Internet;

import java.io.IOException;

public class MultiThreadDown {
	public static void main(String[] args) throws Exception{
		String path="http://localhost/wall2.png";
		String targetFile="/home/hijj/桌面/ios.png";
		int threadNum=5;
		
		final DownUtil downUtil=new DownUtil(path, targetFile, threadNum);
		downUtil.download();
		new Thread(()->{
			while(downUtil.getCompleteRate()<1){
				System.out.println(downUtil.getCompleteRate());
				try{
					Thread.sleep(1);
				}catch(Exception e){}
			}
			//System.out.println(downUtil.getCompleteRate());
		}).start();
	}

}
