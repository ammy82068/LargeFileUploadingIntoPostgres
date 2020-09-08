package com.example.demo.controller;

import java.awt.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class pythoncaller {
	/*
	 * old uploading folder wise private static String UPLOADED_FOLDER =
	 * "//home/floder//";
	 * 
	 * @PostMapping("/api/file/upload") public String
	 * uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file,
	 * RedirectAttributes redirectAttributes) { if (file.isEmpty()) { return
	 * "file not found"; } try { // Get the file and save it somewhere byte[] bytes
	 * = file.getBytes(); Path path = Paths.get(UPLOADED_FOLDER +
	 * file.getOriginalFilename()); Path filePath = path.getParent(); Path fileName
	 * = path.getFileName();
	 * 
	 * System.out.println(path.getParent()); Files.write(path, bytes);
	 * 
	 * // String url = "http://localhost:5000/upload?"+ "id="+id +
	 * "fpath="+filePath+ // "&"+ "fName="+ fileName ; String url =
	 * "http://localhost:5000/upload?" + "fpath=" + filePath + "&" + "fName=" +
	 * fileName; System.out.println(url); RestTemplate restTemplate = new
	 * RestTemplate(); HttpEntity<?> requestEntity = null; ResponseEntity<String>
	 * result = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
	 * String.class); // System.out.println(">>>>>>>>>>>" + result);
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * return "file uploaded"; }
	 */
	@GetMapping("/api/file/download")
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("id") Integer id) {

		String url = "http://localhost:5000/download?" + "id=" + id;

		ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		ResponseEntity<ByteArrayResource> a = restTemplate.getForEntity(url, ByteArrayResource.class);
		// Execute the request.
		System.out.println(a.getHeaders());
		return  null;
	}

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
			String url = "http://localhost:5000/up?" + "fName=" + filename + "&" + "fData=" + bytes;
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<?> requestEntity = null;
			restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return file.getOriginalFilename() + " " + "uploaded";
	}
}
