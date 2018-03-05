/**
 * 
 */
package com.topstar.volunteer.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.topstar.volunteer.util.StringTools;

/**
 * Title: IP匹配查询工具<BR>
 * 
 */
public class IpUtil {

	public static final String[] IP_S_S = { "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])", "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])",
			"((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])", "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b" };

	// 正则表达式判断IP正确性
	public static boolean isIPV4(String ip) {
		if (ip == null || "".equals(ip.trim())) {
			return false;
		}

		String[] parts = ip.split("\\.");

		if (parts.length != 4) {
			return false;
		}

		for (String part : parts) {
			try {
				int intVal = Integer.parseInt(part);
				if (intVal < 0 || intVal > 255) {
					return false;
				}

			} catch (NumberFormatException nfe) {
				return false;
			}
		}

		return true;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private static boolean isEmpty(String _string) {
		return _string == null || _string.trim().length() == 0;
	}

	private static boolean CheckIp(String Ip, String allowedIp) {
		if (isEmpty(Ip))
			return false;
		String[] ArrayAllowedIp = allowedIp.split("\\.");
		if (ArrayAllowedIp.length != 4) {
			return false;
		}
		String patternStr = "";
		for (int i = 0; i < ArrayAllowedIp.length; i++) {
			if (i == 0) {
				if (ArrayAllowedIp[i].equals("*")) {
					patternStr = patternStr + IP_S_S[0];
				} else {
					patternStr = patternStr + ArrayAllowedIp[i];
				}
			} else if (i == 3) {
				if (ArrayAllowedIp[i].equals("*")) {
					patternStr = patternStr + "\\." + IP_S_S[3];
				} else {
					patternStr = patternStr + "\\." + ArrayAllowedIp[i];
				}
			} else {
				if (ArrayAllowedIp[i].equals("*")) {
					patternStr = patternStr + "\\." + IP_S_S[i];
				} else {
					patternStr = patternStr + "\\." + ArrayAllowedIp[i];
				}
			}
		}
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(Ip);
		return matcher.matches();
	}

	public static boolean checkRight(String _allowedIps, HttpServletRequest request) {
		boolean hasRight = false;
		String allowedIps = StringTools.showNull(_allowedIps, "");
		Set<String> m_oIPAlowed = null;
		String currIp = getIpAddr(request);
		String temp[] = (String[]) null;
		if (!isEmpty(allowedIps)) {
			temp = allowedIps.split(";");
			m_oIPAlowed = new HashSet<String>(temp.length);
			int i = 0;
			for (int len = temp.length; i < len; i++)
				m_oIPAlowed.add(temp[i]);
		}
		if ("127.0.0.1".equals(currIp)) {
			hasRight = true;
		}
		if (m_oIPAlowed != null) {
			if (m_oIPAlowed.contains(currIp)) {
				hasRight = true;
			}
			for (Iterator<String> it = m_oIPAlowed.iterator(); it.hasNext();) {
				String allowedIp = (String) it.next();
				if (CheckIp(currIp, allowedIp)) {
					hasRight = true;
				}
			}
		}
		return hasRight;
	}

	public static boolean checkRight(String _allowedIps, String currIp) {
		boolean hasRight = false;
		String allowedIps = StringTools.showNull(_allowedIps, "");
		Set<String> m_oIPAlowed = null;
		String temp[] = (String[]) null;
		if (!isEmpty(allowedIps)) {
			temp = allowedIps.split(";");
			m_oIPAlowed = new HashSet<String>(temp.length);
			int i = 0;
			for (int len = temp.length; i < len; i++)
				m_oIPAlowed.add(temp[i]);
		}
		if ("127.0.0.1".equals(currIp)) {
			hasRight = true;
		}
		if (m_oIPAlowed != null) {
			if (m_oIPAlowed.contains(currIp)) {
				hasRight = true;
			}
			for (Iterator<String> it = m_oIPAlowed.iterator(); it.hasNext();) {
				String allowedIp = (String) it.next();
				if (CheckIp(currIp, allowedIp)) {
					hasRight = true;
				}
			}
		}
		return hasRight;
	}

	public static void main(String[] args) {
		System.out.println(IpUtil.checkRight("192.168.1.*;192.168.2.*;", "192.168.2.2"));
	}

}
