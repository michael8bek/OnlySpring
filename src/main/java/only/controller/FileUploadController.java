package only.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import only.model.Post;
import only.model.Post_Image;
import only.service.PostService;

@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private PostService ps;

	@RequestMapping(value = "/postWrite", method = RequestMethod.POST)
	public String postWrite(Post post, HttpServletRequest request
	/*
	 * @RequestParam("member_id") String member_id, @RequestParam("path") String
	 * path,
	 * 
	 * @RequestParam("text") String text, @RequestParam("imageUpload")
	 * List<MultipartFile> imageUpload,
	 * 
	 * @RequestParam("videoUpload") MultipartFile videoUpload
	 */) {
		String rootPath =  request.getSession().getServletContext().getRealPath("/WEB-INF/img_timeline");
		char[] type = { 'n', 'n', 'n', 'n', 'n', 'n', 'n' };
		String text = post.getText();
		if (text != null && !text.equals("")) {
			type[0] = 'y';
		}
		List<MultipartFile> files = post.getFiles();
		if (files.size() > 0) { // upload된 image파일이 있을 경우
			for (MultipartFile file : files) {
				type[1] = 'y';
				try {
					byte[] bytes = file.getBytes();
					// Creating the directory to store file
					/*File dir = new File(rootPath + File.separator + "uploadFiles");
					if (!dir.exists())
						dir.mkdirs();*/

					// Create the file on server
					File serverFile = new File(rootPath + File.separator + file.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					logger.info("Server File Location=" + serverFile.getAbsolutePath());
					System.out.println("Server File Location=" + serverFile.getAbsolutePath());

				} catch (Exception e) {
					return "You failed to upload " + file.getName() + " => " + e.getMessage();
				}
			}
		}

		// Add Post
		System.out.println(String.copyValueOf(type) + String.copyValueOf(type).length());
		post.setType(String.copyValueOf(type));
		int pid = ps.nextPid();
		System.out.println("포스트 현재 아이디 pid: " + pid);
		post.setPid(pid);
		int result = ps.insertPost(post);
		if (result > 0) {
			System.out.println("작성성공 from PostWrite");
		} else {
			System.out.println("작성실패");
		}
		for (int i = 0; i < type.length; i++) {
			if (type[i] == 'y') {
				if (i == 0) { // Add text
					if (ps.insertText(pid, text) > 0) {
						System.out.println("Text 입력성공");
					} else {
						System.out.println("Text 입력실패");
					}
				}
				if (i == 1) { // Add Image/Video
					for (int j = 0; j < files.size(); j++) {
						System.out.println(files.get(j).getOriginalFilename());
						Post_Image postImage = new Post_Image();
						postImage.setPid(post.getPid());
						postImage.setUrl(files.get(j).getOriginalFilename());
						postImage.setSeq(j);
						if (ps.insertImage(pid, postImage) > 0) {
							System.out.println("Image 입력성공");
						} else {
							System.out.println("Image 입력실패");
						}
					}
				}
			}
		}
		return "timeline";
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}