package com.gigglehd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
import com.gigglehd.domain.BoardIP;
import com.gigglehd.domain.BoardPictures;
import com.gigglehd.domain.Reply;
import com.gigglehd.domain.SubCategory;
import com.gigglehd.domain.User;
import com.gigglehd.persistence.BigCategoryRepository;
import com.gigglehd.persistence.BoardIDRepository;
import com.gigglehd.persistence.BoardPicturesRepository;
import com.gigglehd.persistence.BoardRepository;
import com.gigglehd.persistence.ReplyRepository;
import com.gigglehd.persistence.SubCategoryRepository;
import com.gigglehd.persistence.UserRepository;
import com.gigglehd.util.Criteria;
import com.gigglehd.util.PageMaker;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@ComponentScan("com.gigglehd.domain")
@RequestMapping("/board/")
public class BoardController {
	@Autowired
	BoardRepository boardRepository;

	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	ServletContext servletContext;
	@Autowired
	BoardIDRepository boardIDRepository;
	@Autowired
	BigCategoryRepository bigCategoryRepository;
	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BoardPicturesRepository boardPicturesRepository;

	@RequestMapping("search")
	public String search(Model model, Criteria cri) {
		List<Board> list = boardRepository.getUltimateSearch(cri);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int totalCount = boardRepository.getCountForUltimateSearch(cri);
		pm.setTotalCount(totalCount);
		model.addAttribute("list", list);
		model.addAttribute(pm);
		return "/board/search";
	}

	@RequestMapping("list")
	public String list(Model model, Criteria cri) {
		System.out.println(cri.getMaincategory() + " maincategory");
//		model.addAttribute("maincategory", categoryMapper.getMainCategories(cri.getMaincategory()));
		model.addAttribute("maincategory", bigCategoryRepository.findByName(cri.getMaincategory()));
//		model.addAttribute("subcategory", categoryMapper.getList(cri.getMaincategory()));
		model.addAttribute("subcategory", subCategoryRepository.findByMainCategory(cri.getMaincategory()));

		int totalCount = boardRepository.getCount(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		if (cri.getMaincategory().equals("picture"))
			cri.setPerPageNum(9);
		List<Board> list = boardRepository.getList(cri);
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		List<Reply> replyList = replyRepository.getListBy30();
		model.addAttribute("replyList", replyList);
		if (cri.getMaincategory().equals("picture"))
			return "/board/pictureList";
		else if (cri.getMaincategory().equals("community")) {
			model.addAttribute("dateNow",LocalDate.now().plus(-1, ChronoUnit.DAYS));
			return "/board/communityList";
		}
		return "/board/list";
	}

	@GetMapping("read")
	@Transactional
	public String read(Model model, Criteria cri, int num, HttpServletRequest request) {
		BoardIP idDto = new BoardIP();
		idDto.setIp(request.getRemoteAddr());
		idDto.setRootnum(num);
		idDto.setCategory("replycount");
		//int count = boardIDMapper.getOne(idDto);
		int count=boardIDRepository.countByIdAndNumAndCategory(idDto);
		System.out.println(count+" countcount");
		if (count == 0) {
			//boardIDMapper.insert(idDto);
			boardIDRepository.save(idDto);
			boardRepository.updateHit(num);
		}
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		model.addAttribute("updateDeleteCount", boardRepository.getUpdateDeleteCount(num));
		model.addAttribute("recommendation", boardRepository.getUpdateRecommendationCount(num));
		model.addAttribute("pageMaker", pm);
		System.out.println("read page");
		Board dto = boardRepository.getOne(num);
		model.addAttribute("dto", dto);
		List<Reply> list = replyRepository.getList(num);
		model.addAttribute("list", list);
		return "/board/read";
	}

	@GetMapping("write")
	public String write(Model model, Criteria cri) {
		//List<SubCategory> list = categoryMapper.getSubcategoriesByBigcategory(cri.getMaincategory());
		List<SubCategory> list=subCategoryRepository.findByMainCategory(cri.getMaincategory());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", list);
		return "/board/write";
	}

	@GetMapping("modify")
	public String modify(Model model, Criteria cri, int num) {
		//List<SubCategory> list = categoryMapper.getSubcategoriesByBigcategory(cri.getMaincategory());
		List<SubCategory> list=subCategoryRepository.findByMainCategory(cri.getMaincategory());


		Board dto = boardRepository.getOne(num);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		model.addAttribute("mode", "modify");
		model.addAttribute("pageMaker", pm);
		model.addAttribute("dto", dto);
		model.addAttribute("list",list);
		return "/board/write";
	}

	@PostMapping("modify")
	public String modifyPOST(Model model, Criteria cri, Board dto) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		boardRepository.modify(dto);
		return "redirect:/board/read" + pm.makeQuery(cri.getPage())+"&num="+dto.getNum();
	}

	@PostMapping("write")
	@Transactional
	public String writePOST(Model model, Criteria cri, Board dto, HttpSession session) throws IOException {

		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		if (dto.getGroup_num() != 0) {
			System.out.println("is it working?");
			boardRepository.updateSequence(dto);
			dto.setSequence(dto.getSequence() + 1);
		}
		boardRepository.insert(dto);
		if (cri.getMaincategory().equals("picture") && dto.getContent().contains("<img")) {
			System.out.println(dto.getContent() + " getContent");
			Pattern p = Pattern.compile("<img[^>]*src=[\"']?/resources/([^>\"']+)[\"']?[^>]*>");
			Matcher m = p.matcher(dto.getContent());
			while (m.find()) {
				File thumbnail = new File(servletContext.getRealPath("/resources/thumbnails/") + m.group(1));
				if (!thumbnail.getParentFile().exists())
					thumbnail.getParentFile().mkdirs();
				File image=new File(servletContext.getRealPath("/resources/")+m.group(1));
				Thumbnails.of(image).size(100, 100).outputFormat("png").toFile(thumbnail);
				BoardPictures bpdto = new BoardPictures();
				bpdto.setRootNum(dto.getNum());
				bpdto.setUrl("/resources/thumbnails/"+m.group(1));
				boardPicturesRepository.save(bpdto);
			
				break;
			}
		}
		if (dto.getGroup_num() == 0) {
			dto.setGroup_num(dto.getNum());
			boardRepository.updateGroupNum(dto);
		}
		User udto = (User) session.getAttribute("user");
		udto.setPoints(100);
		userRepository.savePointsByUsername(udto);
		return "redirect:/board/read" + pm.makeQuery(cri.getPage())+"&num="+dto.getNum();
	}

	@PostMapping("writeComments")
	@Transactional
	public String commentPOST(Model model, Criteria cri, Reply dto, HttpSession sess) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		if (dto.getGroup_num() != 0) {
			System.out.println("possible?");
			replyRepository.updateSequence(dto);
		}
		User user = (User) sess.getAttribute("user");
		if(user==null)
			return "redirect:/";
		System.out.println(user + " userDTO");
		dto.setWriter(user.getUsername());
		replyRepository.insert(dto);
		System.out.println(dto.getGroup_num() + " group_num");
		if (dto.getGroup_num() == 0) {
			replyRepository.updateGroupNum(dto.getNum());
		}

		return "redirect:/board/read" + pm.makeQuery(cri.getPage()) + "&num=" + dto.getRoot_num();
	}

	@PostMapping("delete")
	public String deletePOST(Model model, Criteria cri, int num) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		boardRepository.delete(num);
		return "redirect:/board/list" + pm.makeQuery(cri.getPage());
	}

	// 파일 업로드
	@PostMapping("upload")
	public void uploadPOST(@RequestParam("upload") MultipartFile file, HttpServletResponse response,
			int CKEditorFuncNum) throws IOException {
		byte[] buffer = file.getBytes();
		PrintWriter printWriter = response.getWriter();
		String fullname = new String(file.getOriginalFilename().getBytes(), "UTF-8");
		int lastIndex = fullname.lastIndexOf(".");
		String filename = fullname.substring(0, lastIndex);
		String extname = fullname.substring(lastIndex);

		if (!file.getContentType().contains("image")) {
			printWriter.println("<script>"
					+ "$(\"#alertContent\").text(\"이미지 파일이 아닙니다!\");" + 
					"				$(\"#myModal\").modal(\"toggle\");"
					+ "</script>");
			return;
		}
		fullname = filename + UUID.randomUUID() + extname;
		FileOutputStream fos = new FileOutputStream(servletContext.getRealPath("/resources/") + fullname);
		FileCopyUtils.copy(buffer, fos);
		fos.flush();
		fos.close();

		String fileUrl = "/resources/" + fullname;


		printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
				+ CKEditorFuncNum + ",'" + fileUrl + "','이미지를 업로드 하였습니다.'" + ")</script>");
		printWriter.flush();

		return;
	}

	@GetMapping("pictureList")
	public String photo(Model model, Criteria cri) {
		model.addAttribute("list", boardRepository.getList(cri));
		return "/board/pictureList";
	}

}
