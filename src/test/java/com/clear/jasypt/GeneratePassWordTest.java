package com.clear.jasypt;

import cn.hutool.crypto.digest.BCrypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @describe jasypt进行数据库密码加密
 *
 * @author zhd
 *
 * @version 创建时间：2020年4月26日 上午11:54:39
 *
 */
@RunWith(SpringRunner.class)
public class GeneratePassWordTest {

	@Test
	public void getPassWord() {
		boolean flag = BCrypt.checkpw("3clear11", "$2a$12$b7Pro5EOQXDqjs4w3asuheu0TgYzekBlxdMNzua1hTR5Ka3k78aOu");
		boolean xixi = BCrypt.checkpw("sdsd", "$2a$12$b7Pro5EOQXDqjs4w3asuheu0TgYzekBlxdMNzua1hTR5Ka3k78aOu");
		System.out.println(flag);
		System.out.println(xixi);
	}



}
