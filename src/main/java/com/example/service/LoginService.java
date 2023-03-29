package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザー情報を操作するサービスクラス.
 * 
 * @author izawa
 *
 */
@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * メールアドレスとパスワードからユーザー情報を取得します.
	 * 
	 * @param email メールアドレス
	 * @param password　パスワード
	 * @return　ユーザー情報
	 */
	public User login(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return null;
		}
		if (!password.equals(user.getPassword())) {
			return null;
		}
		return user;
	}

}
