package com.example.demo.controller;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/v2")
public class FileOperationController {
	
	/** upload file api **/

	@PostMapping("/api/file/upload")
	public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			return "file not found";
		}
		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			String filename = file.getOriginalFilename();
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(bytes);
			String url = "http://localhost:5000/upload?" + "fName=" + filename;
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return file.getOriginalFilename() + " " + "uploaded";
	}
	
	/** Download file api **/

	@GetMapping("/api/file/download")
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("id") Integer id) {

		String url = "http://localhost:5000/download?" + "id=" + id;

		ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		ResponseEntity<ByteArrayResource> a = restTemplate.getForEntity(url, ByteArrayResource.class);
		// Execute the request.
		System.out.println(a.getHeaders());
		return  a;
	}


}
