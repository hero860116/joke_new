package com.kelepi.web.common.tool;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class PageTool {

	@Autowired
	private HttpServletRequest request;

	public String paging(int page) {
		StringBuffer buffer = request.getRequestURL();

		String queryString = StringUtil.trimToNull(request.getQueryString());

		if (queryString != null) {
			buffer.append('?').append(queryString);
		}

		String url = buffer.toString();

		try {
			url = new String(url.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			//ignore...
		}

		Pattern pattern = Pattern.compile("(.*[\\?&]?page=)(\\d*)([&#]?.*)");
		Matcher m = pattern.matcher(url);
		if (!m.matches()) {
			url = url + (url.indexOf('?') == -1 ? '?' : '&') + "page=" + page;
		} else {
			url = m.replaceAll("$1" + page + "$3");
		}


        Pattern pattern1 = Pattern.compile("http://(.*)/\\?page=(\\d*)([&#]?.*)");
        Matcher m1 = pattern1.matcher(url);
        if (m1.matches()) {
            url = m1.replaceAll("http://$1" + "/all" + "/$2$3");
        } else {
            Pattern pattern2 = Pattern.compile("http://(.*)/homepage\\.htm\\?recommendType=2&page=(\\d*)([&#]?.*)");
            Matcher m2 = pattern2.matcher(url);
            if (m2.matches()) {
                url = m2.replaceAll("http://$1" + "/hot" + "/$2$3");
            } else {
                Pattern pattern3 = Pattern.compile("http://(.*)/user_creation\\.htm\\?userId=(\\d*)&page=(\\d*)([&#]?.*)");
                Matcher m3 = pattern3.matcher(url);
                if (m3.matches()) {
                    url = m3.replaceAll("http://$1" + "/u/$2" + "/$3$4");
                } else {

                    Pattern pattern4 = Pattern.compile("http://(.*)/user_top\\.htm\\?userId=(\\d*)&page=(\\d*)([&#]?.*)");
                    Matcher m4 = pattern4.matcher(url);
                    if (m4.matches()) {
                        url = m4.replaceAll("http://$1" + "/u/$2/t" + "/$3$4");
                    }
                }
            }
        }

        return url;
	}
}
