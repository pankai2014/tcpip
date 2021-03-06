package org.kaipan.jserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SslSocketServer 
{
	public  final static int port = 8000;
	
	private final static String password     = "25024466";
	private final static String keystorePath = "/home/will/Develop/workspace/ssl/keystore";
	
	public SslSocketServer() 
	{
		
	}
	
	private static SSLServerSocket getServeSocket() 
	{
		SSLServerSocket s = null;
		
		char keyStorePass[] = password.toCharArray();		//证书密码
		char keyPassword[]  = password.toCharArray();		//证书别名密码
		
		KeyStore ks = null;
		KeyManagerFactory kmf = null;
		
		try {
			ks = KeyStore.getInstance("JKS");
		} 
		catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//创建JKS密钥库
		try {
			ks.load(new FileInputStream(keystorePath), keyStorePass);
			
			//创建管理JKS密钥库的X.509密钥管理器
			kmf = KeyManagerFactory.getInstance("SunX509");
			
			SSLContext sslContext = null;
			kmf.init(ks, keyPassword);
			
			sslContext = SSLContext.getInstance("SSLV3");
			sslContext.init(kmf.getKeyManagers(), null, null);
			
			SSLServerSocketFactory factory = sslContext.getServerSocketFactory();
			
			s = (SSLServerSocket) factory.createServerSocket(port);
		} 
		catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (CertificateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
	
	public void start() 
	{
		//SslSocketServer server = new SslSocketServer();
		
		SSLServerSocket serverSocket = SslSocketServer.getServeSocket();
		System.out.println("在" + port + "端口等待连接...");
		
		
		while ( true ) {
			try {
				SSLSocket socket = (SSLSocket)serverSocket.accept();
				
				new CreateThread(socket);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class CreateThread extends Thread 
	{
		public CreateThread(SSLSocket socket) 
		{
			
		}
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
}
