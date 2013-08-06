package com.kelepi.biz.uri;

import com.alibaba.citrus.service.uribroker.interceptor.URIBrokerInterceptor;
import com.alibaba.citrus.service.uribroker.uri.ContentURIBroker;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;

/**
 * @author dongbai mailto:dongbai@taobao.com
 * @create Apr 7, 2008
 */
public class UpyunImageServerCustomHandler implements URIBrokerInterceptor {

	public void perform(URIBroker broker) {
		if (broker instanceof ContentURIBroker) {
			ContentURIBroker tBroker = (ContentURIBroker) broker;
			String path = tBroker.getContentPath();
			String[] imgs = new String[]{"img01", "img02", "img03", "img04", "img05", "img11", "img12", "img13", "img14", "img15"};
			int  index = urlToInt(path, 10);
			tBroker.setServerName(tBroker.getServerName().replace("img.", imgs[index] + ".")); // URIBroker�Ƕ�ʵ�����Կ�����������servername

		}
	}
	
	private int urlToInt(String url, int max) {
		byte[] bytes = url.getBytes();
		int total = 0;
		for (byte by : bytes) {
			total += by;
		}
		
		return total%max;
	}
	
/*	public static void main(String[] args) {
		int t = 250;
		
		long s = System.currentTimeMillis();
		for (int i = 0; i < 40; i++) {
			String url = "http://img12.juwaimai.com/statics/images/shop/_0SzHI6URyLznGpfQRN2F0GRBha7sgz.jpg!" + t;
			byte[] bytes = url.getBytes();
			int total = 0;
			for (byte by : bytes) {
				total += by;
			}
			//System.out.println(total%5);
			
			t++;
		}
		long e = System.currentTimeMillis();
		System.out.println(e-s);
	}*/

}