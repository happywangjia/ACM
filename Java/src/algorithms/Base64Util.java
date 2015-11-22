package algorithms;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64Util extends FilterOutputStream{
	private static char[] toBase64={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6',
			'7','8','9','+','/'
	};
	private int col=0;
	private int i=0;
	private int[] inbuf=new int[3];
	
	public Base64Util(OutputStream out){
		super(out);
	}
	public void write(int c) throws IOException{
		inbuf[i]=c;
		i++;
		if(i==3){
			if(col>=76){
				super.write('\n');
				col=0;
			}
			super.write(toBase64[(inbuf[0]&0xfc)>>2]);
			super.write(toBase64[((inbuf[0]&0x03)<<4)|((inbuf[1]&0xf0)>>4)]);
			super.write(toBase64[((inbuf[1]&0x0f)<<2)|((inbuf[2]&0xc0)>>6)]);
			super.write(toBase64[inbuf[2]&0x3f]);
			col+=4;
			i=0;
		}
	}
	public void flush() throws IOException{
		if(i>0&&col>=76){
			super.write('\n');
			col=0;
		}
		if(i==1){
			super.write(toBase64[(inbuf[0]&0xfc)>>2]);
			super.write(toBase64[(inbuf[0]&0x03)<<4]);
			super.write('=');
			super.write('=');
		}else if(i==2){
			super.write(toBase64[(inbuf[0]&0xfc)>>2]);
			super.write(toBase64[((inbuf[0]&0x03)<<4)|((inbuf[1]&0xf0)>>4)]);
			super.write(toBase64[(inbuf[1]&0x0f)<<2]);
			super.write('=');
		}
	}
	public String base64Encode(String s){
		ByteArrayOutputStream bOut=new ByteArrayOutputStream();
		Base64Util out=new Base64Util(bOut);
		try{
			out.write(s.getBytes());
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return bOut.toString();
	}
}
