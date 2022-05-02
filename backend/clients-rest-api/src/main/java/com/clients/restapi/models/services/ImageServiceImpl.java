package com.clients.restapi.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements IImageService {
	
	private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
	private final static String IMAGES_DIRECTORY = "images";
	private final static String STATIC_IMAGES_DIRECTORY = "src/main/resources/static/images";
	
	@Override
	public Resource load(String namePhoto) throws MalformedURLException {
		
		Path path = this.getPath(IMAGES_DIRECTORY,namePhoto);
		logger.info(path.toString());
		
		Resource resource = null;

		resource = new UrlResource(path.toUri());
		
		if(!resource.exists() && !resource.isReadable()) {
			path = this.getPath(STATIC_IMAGES_DIRECTORY, "default.png");
			
			resource = new UrlResource(path.toUri());

			logger.error("Error: cannot load the image");
		}
		return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename().replace(" ", "");
		Path path = this.getPath(IMAGES_DIRECTORY, fileName);
		logger.info(path.toString());
		
		Files.copy(file.getInputStream(), path);
		return fileName;
		
	}

	@Override
	public boolean delete(String namePhoto) {
		if(namePhoto != null && namePhoto.length()>0) {
			Path previousPath = this.getPath(IMAGES_DIRECTORY, namePhoto);
			File filePreviousPhoto = previousPath.toFile();
			if(filePreviousPhoto.exists() && filePreviousPhoto.canRead()) {
				filePreviousPhoto.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String directory, String namePhoto) {
		return Paths.get(directory).resolve(namePhoto).toAbsolutePath();
	}

}
