package com.clients.restapi.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
	public Resource load(String namePhoto) throws MalformedURLException;
	public String copy(MultipartFile file) throws IOException;
	public boolean delete(String namePhoto);
	public Path getPath(String directory, String namePhoto);
}
