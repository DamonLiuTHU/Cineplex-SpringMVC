package com.cineplex.tools;

public class MailFactory {

	/**
	 * 服务邮箱
	 */
	private static MailSender serviceSms = null;

	/**
	 * 获取邮箱
	 * 
	 * @param type
	 *            邮箱类型
	 * @return 符合类型的邮箱
	 */

	static final String mail_account = "cineplex@sina.com";
	static final String psw = "lwt962464";

	public static MailSender getSender(MailSenderType type) {
		if (type == MailSenderType.SERVICE) {

			if (serviceSms == null) {
				serviceSms = new MailSender(mail_account, psw);
			}
			return serviceSms;
		}
		return null;
	}

}
