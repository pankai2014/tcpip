package org.kaipan.www.socket.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.kaipan.www.socket.log.Logger;

public class StringUtil 
{
	public static ByteBuffer string2Bytes(String str)  
    {  
	    byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
            
            return ByteBuffer.wrap(bytes);  
        } 
        catch (UnsupportedEncodingException e) {
        	Logger.write(e.getMessage(), Logger.ERROR);
        }
        
        return null;
    }  
	
	public static String bytes2String(ByteBuffer buffer)  
    {  
		Charset charset	= null;
        CharsetDecoder decoder = null;  
        CharBuffer charBuffer  = null;
        
        try {
            // used for reading data            
            charset = Charset.forName("UTF-8");  
            decoder = charset.newDecoder();  
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());  
            
            return charBuffer.toString();  
        }  
        catch (Exception e) {  
        	Logger.write(e.getMessage(), Logger.ERROR);
        }
        
        return null;
    }
}
