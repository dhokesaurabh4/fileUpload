package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
public static String uploadDirectory=System.getProperty("user.dir")+"//uploads";
	
public Model modelG=null;	
@RequestMapping("/")
public String UploadPage(Model model) {

	
	return "uploadView.html";
}

@RequestMapping("/upload")
public String upload(Model model,@RequestParam("files") MultipartFile[] files) {
	this.modelG=model;
	StringBuilder fileNames=new StringBuilder();
	for(MultipartFile file:files) {
		
		Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename()+" ");
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//model.addAttributes("msg","successfully upladed files "+fileNames.toString());
		
	}
	//System.out.println("tomact dir-> "+System.getProperty("catalina.base"));
	model.addAttribute("msg","successfully upladed files "+fileNames.toString());
	
	
	
	File f=new File(uploadDirectory);
	List<File> listFiles=new ArrayList<File>();
	File[]list=f.listFiles();
	for(File file:list) {
		listFiles.add(file);
	}
	modelG.addAttribute("listFiles",listFiles);
	return "uploadView.html";
	//return "";
}

	
}
