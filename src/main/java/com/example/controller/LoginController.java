package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.service.LoginService;

import jakarta.servlet.http.HttpSession;

/**
 * ユーザー情報を操作するコントローラークラス.
 * 
 * @author izawa
 *
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private HttpSession session;

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	/**
	 * ログインします.
	 * 
	 * @param form ユーザー情報フォーム
	 * @param model
	 * @return　ログイン後の商品一覧画面
	 */
	@RequestMapping("/")
	public String login(LoginForm form, Model model) {
		User user = loginService.login(form.getEmail(),form.getPassword());
		if (user == null) {
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
			return toLogin();
		}
		if(session.getAttribute("url") != null) {
			return "redirect:"+session.getAttribute("url");
		}
		return "forward:/";
	}

}
