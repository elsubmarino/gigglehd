package com.gigglehd.restController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gigglehd.domain.BoardIP;
import com.gigglehd.persistence.BoardRepository;
import com.gigglehd.persistence.UserRepository;

@RestController
@RequestMapping("/board/")
public class BoardRestController {
	@Autowired
	BoardRepository boardRepository;

/*	@Autowired
	BoardIDMapper boardIDMapper;
	*/
	@Autowired
	UserRepository userRepository;
	
/*	@PostMapping("updateRecommendation")
	public @ResponseBody Map<String, Object> updateRecommendation(@RequestBody Map<String, Object> test,
			HttpServletRequest request,HttpSession sess) {
		int num = Integer.parseInt((String) test.get("num"));

		BoardID dto = new BoardID();
		dto.setCategory("recommendationCount");
		dto.setId(request.getRemoteAddr());
		dto.setNum(num);
		int count = boardIDMapper.getOne(dto);
		int result=0;
		if (count == 0) {
			boardIDMapper.insert(dto);
			 result = boardRepository.updateRecommendation(dto.getNum());
			 User udto=(User)sess.getAttribute("user");
			 udto.setPoints(5);
			 userRepository.updatePoints(udto);
		}
		Map<String, Object> haha = new HashMap<>();

		if (result == 1) {
			// return new ResponseEntity<String>("success",HttpStatus.OK);
			haha.put("status", "success");
			return haha;
		} else {
			// return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
			haha.put("status", "fail");
			return haha;

		}
	}
*/
/*	@PostMapping("updateDelete")
	public @ResponseBody Map<String, Object> updateDelete(@RequestBody Map<String, Object> test,
			HttpServletRequest request) {
		int num = Integer.parseInt((String) test.get("num"));

		BoardID dto = new BoardID();
		dto.setCategory("deleteCount");
		dto.setId(request.getRemoteAddr());
		dto.setNum(num);
		int count = boardIDMapper.getOne(dto);
		int result=0;
		if (count == 0) {
			boardIDMapper.insert(dto);
			 result = boardRepository.updateDelete(num);
		}
		Map<String, Object> haha = new HashMap<>();

		if (result == 1) {
			// return new ResponseEntity<String>("success",HttpStatus.OK);
			haha.put("status", "success");
			return haha;
		} else {
			// return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
			haha.put("status", "fail");
			return haha;

		}
	}*/
}
