package com.gigglehd.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gigglehd.domain.Board;
import com.gigglehd.domain.BoardID;
import com.gigglehd.domain.Reply;
import com.gigglehd.domain.SubCategory;
import com.gigglehd.domain.User;
import com.gigglehd.persistence.BoardIDMapper;
import com.gigglehd.persistence.BoardMapper;
import com.gigglehd.persistence.CategoryMapper;
import com.gigglehd.persistence.ReplyMapper;
import com.gigglehd.persistence.UserMapper;
import com.gigglehd.util.Criteria;
import com.gigglehd.util.PageMaker;

@Controller
@RequestMapping("/board/")
public class BoardController {
	@Autowired
	BoardMapper boardMapper;

	@Autowired
	ReplyMapper replyMapper;

	@Autowired
	ServletContext servletContext;

	@Autowired
	BoardIDMapper boardIDMapper;

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	UserMapper userMapper;

	@RequestMapping("search")
	public String search(Model model, Criteria cri) {
		List<Board> list = boardMapper.getUltimateSearch(cri);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int totalCount = boardMapper.getCountForUltimateSearch(cri);
		pm.setTotalCount(totalCount);
		model.addAttribute("list", list);
		model.addAttribute(pm);
		return "/board/search";
	}

	@RequestMapping("list")
	public String list(Model model, Criteria cri) {
		System.out.println(cri.getMainCategory() + " mainCategory");
		model.addAttribute("mainCategory", categoryMapper.getMainCategories(cri.getMainCategory()));
		model.addAttribute("subCategory", categoryMapper.getList(cri.getMainCategory()));
		int totalCount = boardMapper.getCount(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		if (cri.getMainCategory().equals("picture"))
			cri.setPerPageNum(9);
		List<Board> list = boardMapper.getList(cri);
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		List<Reply> replyList = replyMapper.getListBy30();
		model.addAttribute("replyList", replyList);
		if (cri.getMainCategory().equals("picture"))
			return "/board/pictureList";
		else if (cri.getMainCategory().equals("community"))
			return "/board/communityList";
		return "/board/list";
	}

	@GetMapping("read")
	@Transactional
	public String read(Model model, Criteria cri, int num, HttpSession session, HttpServletRequest request) {
		BoardID idDto = new BoardID();
		idDto.setId(request.getRemoteAddr());
		idDto.setNum(num);
		idDto.setCategory("replyCount");
		int count = boardIDMapper.getOne(idDto);
		if (count == 0) {
			boardIDMapper.insert(idDto);
			boardMapper.updateHit(num);
		}
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		model.addAttribute("updateDeleteCount", boardMapper.getUpdateDeleteCount(num));
		model.addAttribute("recommendation", boardMapper.getUpdateRecommendationCount(num));
		model.addAttribute("pageMaker", pm);
		System.out.println("read page");
		Board dto = boardMapper.getOne(num);
		model.addAttribute("dto", dto);
		List<Reply> list = replyMapper.getList(num);
		model.addAttribute("list", list);
		return "/board/read";
	}

	@GetMapping("write")
	public String write(Model model, Criteria cri) {
		List<SubCategory> list = categoryMapper.getSubcategoriesByBigcategory(cri.getMainCategory());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", list);
		return "/board/write";
	}

	@GetMapping("modify")
	public String modify(Model model, Criteria cri, int num) {
		Board dto = boardMapper.getOne(num);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		model.addAttribute("mode", "modify");
		model.addAttribute("pageMaker", pm);
		model.addAttribute("dto", dto);
		return "/board/write";
	}

	@PostMapping("modify")
	public String modifyPOST(Model model, Criteria cri, Board dto) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		boardMapper.modify(dto);
		return "redirect:/board/list" + pm.makeQuery(cri.getPage());
	}

	@PostMapping("write")
	@Transactional
	public String writePOST(Model model, Criteria cri, Board dto, HttpSession session) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		if (dto.getGroupnum() != 0) {
			System.out.println("is it working?");
			boardMapper.updateSequence(dto);
			dto.setSequence(dto.getSequence() + 1);
		}
		boardMapper.insert(dto);
		if (dto.getGroupnum() == 0) {
			dto.setGroupnum(dto.getNum());
			boardMapper.updateGroupNum(dto);
		}
		User udto = (User) session.getAttribute("user");
		udto.setPoints(100);
		userMapper.updatePoints(udto);

		return "redirect:/board/list" + pm.makeQuery(cri.getPage());
	}

	@PostMapping("writeComments")
	@Transactional
	public String commentPOST(Model model, Criteria cri, Reply dto, HttpSession sess) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		if (dto.getGroup_num() != 0) {
			System.out.println("possible?");
			replyMapper.updateSequence(dto);
		}
		User user = (User) sess.getAttribute("user");
		System.out.println(user + " userDTO");
		dto.setWriter(user.getUsername());
		replyMapper.insert(dto);
		System.out.println(dto.getGroup_num() + " groupNum");

		if (dto.getGroup_num() == 0) {
			replyMapper.updateGroupNum(dto.getNum());

		}

		return "redirect:/board/read" + pm.makeQuery(cri.getPage()) + "&num=" + dto.getRoot_num();
	}

	@PostMapping("delete")
	public String deletePOST(Model model, Criteria cri, int num) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		boardMapper.delete(num);
		return "redirect:/board/list" + pm.makeQuery(cri.getPage());
	}

	@PostMapping("upload")
	public void uploadPOST(@RequestParam("upload") MultipartFile file, HttpServletResponse response,
			int CKEditorFuncNum) throws IOException {
		byte[] buffer = file.getBytes();
		PrintWriter printWriter = response.getWriter();
		String filename = new String(file.getOriginalFilename().getBytes(), "UTF-8");
		if (!file.getContentType().contains("image")) {
			printWriter.println("<script>alert('이미지 파일이 아닙니다.');</script>");
			return;
		}
		FileOutputStream fos = new FileOutputStream(servletContext.getRealPath("/resources/") + filename);
		FileCopyUtils.copy(buffer, fos);
		fos.flush();
		fos.close();
		String fileUrl = "/resources/" + filename;
		printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
				+ CKEditorFuncNum + ",'" + fileUrl + "','이미지를 업로드 하였습니다.'" + ")</script>");
		printWriter.flush();

		return;
	}

	@GetMapping("pictureList")
	public String photo(Model model, Criteria cri) {
		model.addAttribute("list", boardMapper.getList(cri));
		return "/board/pictureList";
	}

}
