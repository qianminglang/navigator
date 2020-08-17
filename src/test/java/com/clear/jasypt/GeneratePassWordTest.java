package com.clear.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	StringEncryptor encryptor;

	@Test
	public void getPassWord() {
		String passWord = encryptor.encrypt("admin");
		System.out.println(passWord);
	}

}
