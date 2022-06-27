package com.example.demo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controller.FileUploadController;

@SpringBootApplication
@ComponentScan({"com.example.demo","controller"})
public class FileUploadSbApplication {

	public static void main(String[] args) {
		File f=new File(FileUploadController.uploadDirectory);
		if(f.exists()&&f.isDirectory()) {
			try {
				FileUtils.deleteDirectory(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(FileUploadSbApplication.class, args);
	}

}
