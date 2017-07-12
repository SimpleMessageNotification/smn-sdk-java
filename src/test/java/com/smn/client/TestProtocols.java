/**
 * 
 */
package com.smn.client;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author p00314552
 *
 */
public class TestProtocols {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
		
		SSLSocket sslSocket = (SSLSocket) f.createSocket("iam.cn-north-1.myhwclouds.com", 443);

		sslSocket.setUseClientMode(true);
		String[] protocols = sslSocket.getEnabledProtocols();

		for (int i = 0; i < protocols.length; i++) {
			System.out.println(protocols[i]);

		}
		
		System.out.println("------------");
		String[] pro = sslSocket.getSupportedProtocols();
		for (int i = 0; i < pro.length; i++) {
			System.out.println(pro[i]);

		}
	}

}
