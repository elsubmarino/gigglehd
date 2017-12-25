package com.gigglehd.restController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gigglehd.persistence.UserRepository;

@RestController
public class LoginRestController {
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/idCheck")
	public @ResponseBody Map<String,Object> idCheck(@RequestBody Map<String,Object> userName) {
		//int count=userRepository.getId((String)userName.get("username"));
		int count=userRepository.countByUsername((String)userName.get("userName"));
		Map<String,Object> map=new HashMap<>();
		if(count==0) {
			map.put("status", "success");
		}else {
			map.put("status", "fail");
		}
		return map;
	}
}
