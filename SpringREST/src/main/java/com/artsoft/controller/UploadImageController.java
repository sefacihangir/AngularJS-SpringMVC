package com.artsoft.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import com.artsoft.error.CustomError;
import com.artsoft.model.AppUser;
import com.artsoft.service.AppUserService;
import com.artsoft.util.DateUtil;
import com.artsoft.util.PropertiesUtil;

@RestController
@RequestMapping("/api/upload_control")
@EnableWebMvc
public class UploadImageController {
    
    private final double MAX_SIZE_ALLOWED = 2000000.0;
    
    
    @Autowired
    AppUserService appUserService;
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER') or hasRole('ROLE_PROVIDER')")
	@RequestMapping(value = "/upload", headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object upload(@RequestParam(value = "file") MultipartFile uploadedFile, @RequestParam(value = "appUserId") int appUserId){
		
		Map<String,Object> response = new HashMap<String, Object>();
		PropertiesUtil propertiesUtil = new PropertiesUtil(); 
		
		String FOLDER_PATH = "";
		try {
			FOLDER_PATH = propertiesUtil.getUploadFolderPath();
		} catch (IOException e) {
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("upload path");
			error.setErrorMessage("Unknown upload folder path.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		// return with error
		}
		
        /*get file and save it to the local directory uploads*/
        String destination = FOLDER_PATH + "\\" + "userId_" + appUserId;	
        File f = new File(destination);
		
        if (!f.exists()) {
            f.mkdirs();
        }
        
        String fileName = DateUtil.getDateToString() + uploadedFile.getOriginalFilename();
        String fullPath = destination + File.separator + appUserId + "_" + fileName;
        File file = new File(fullPath);
        
        /*check if the file already exists*/
        if ((file.exists()) && (!file.isDirectory())) {
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("file object");
			error.setErrorMessage("Picture already uploaded.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		// return with error
        }
        
        /*check size*/
        double bytes = file.length();
        if (bytes > MAX_SIZE_ALLOWED) {
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("file object");
			error.setErrorMessage("Picture size to big. Max allowed " + MAX_SIZE_ALLOWED + " bytes.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		// return with error
        }
        
        /*check extension*/
        String ext = FilenameUtils.getExtension(fullPath);
        System.out.println(ext);
        boolean extensionOk = false;
        if (ext.equalsIgnoreCase("jpg")) {
            extensionOk = true;
        } else if (ext.equalsIgnoreCase("png")) {
            extensionOk = true;
        }
        
        
        if (!extensionOk) {
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("file object");
			error.setErrorMessage("The extension must be .jpg or .png");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		// return with error
        }
        
        /**** UPLOAD FILE ON LOCAL FOLDER ****/
        try {
            uploadedFile.transferTo(file);
        } catch (IOException ex) {
            ex.printStackTrace();
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("file object");
			error.setErrorMessage("Uploading process failed.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		// return with error
        }
        
        // save the path in database
        String errorMessage = saveUploadedFileToDB(appUserId, fullPath);
        if(!errorMessage.trim().equals("")){
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("user object");
			error.setErrorMessage(errorMessage);
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		// return with error
        }
        
        /**** SUCCESS ****/
        response.put("appUserId", appUserId);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	private String saveUploadedFileToDB(int appUserId, String fullPath){
		String errorMessage = "";
		
		AppUser user = appUserService.findById(appUserId);
		if(user != null){
			user.setImagePath(fullPath);
			appUserService.update(user);
		} else{
			errorMessage = "Failed to fetch user with id '" + appUserId + "' .";
		}
		
		return errorMessage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
