package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.board.model.Board;
import com.example.board.model.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.UserRepository;

@CrossOrigin
@Controller
@RequestMapping("/api")
public class APIController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	HttpSession session;
	
	@GetMapping("/board/list")
	@ResponseBody
	public List<Board> boardList() {
		Sort sort = Sort.by(Order.desc("id"));
		List<Board> list = boardRepository.findAll(sort);
		return list;
	}

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	@PostMapping("/signin")
	@ResponseBody
	public Map<String, Object> signinPost(@ModelAttribute User user) {
		User dbUser = 
			userRepository.findByEmailAndPwd(
				user.getEmail(), user.getPwd());
		Map<String, Object> map = new HashMap<>();
		if(dbUser != null) { // 로그인 성공
			map.put("code", 200);
			map.put("msg", "success");
		} else { // 로그인 실패
			map.put("code", 201);
			map.put("msg", "fail");
		}
		return map;
	}

	@GetMapping("/signout")
	public String signout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/signup") 
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	@ResponseBody
	public User signupPost(@ModelAttribute User user) {
		userRepository.save(user);
		return user;
	}
}