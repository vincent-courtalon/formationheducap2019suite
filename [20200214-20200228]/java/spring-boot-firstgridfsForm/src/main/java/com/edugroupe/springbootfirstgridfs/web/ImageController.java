package com.edugroupe.springbootfirstgridfs.web;

import java.io.IOException;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edugroupe.springbootfirstgridfs.metier.Image;
import com.edugroupe.springbootfirstgridfs.repositories.ImageRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
@RequestMapping("/images")
public class ImageController {

	@Autowired private GridFsTemplate gridFsTemplate;
	@Autowired private ImageRepository imageRepository;

	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<Image> listeImages() {
		return this.imageRepository.findAll();
	}
	
	/*@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String uploadFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("description") String description
			) throws IOException {
		ObjectId id = this.gridFsTemplate.store(
								file.getInputStream(),
								file.getOriginalFilename(),
								file.getContentType(),
								new BasicDBObject("description", description));
		return id.toString();
	}*/
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Image> uploadFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("description") String description
			) throws IOException {
		// dans gridfs
		ObjectId id = this.gridFsTemplate.store(
								file.getInputStream(),
								file.getOriginalFilename(),
								file.getContentType(),
								new BasicDBObject("description", description));
		// dans la table image
		Image img = new Image(0, id.toString(), description);
		return new ResponseEntity<Image>(imageRepository.save(img), HttpStatus.CREATED);
	}
	
	

	@GetMapping("/file")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getFile(@RequestParam("id") int id) throws IllegalStateException, IOException {
		Optional<Image> img = imageRepository.findById(id);
		if (!img.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		GridFSFile fileInfos = this.gridFsTemplate.findOne(
				new Query(Criteria.where("_id").is(
						img.get().getStorageId()
					)));
		
		if (fileInfos == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		GridFsResource resource = this.gridFsTemplate.getResource(fileInfos);
		InputStreamResource binaryData = new InputStreamResource(
				resource.getInputStream());
		
		HttpHeaders headers = new HttpHeaders();
		
		// le content type
		headers.setContentType(MediaType.parseMediaType(resource.getContentType()));
		// la taille du fichier
		headers.setContentLength(resource.contentLength());
		// le nom du fichier original
		headers.set("Content-Disposition", "attachment; filename="+ resource.getFilename());
		
		return new ResponseEntity<InputStreamResource>(binaryData, headers, HttpStatus.OK);
		
	}
	
	
	
	/*
	@GetMapping
	@ResponseBody
	public ResponseEntity<InputStreamResource> getFile(@RequestParam("id") String id) throws IllegalStateException, IOException {
		
		GridFSFile fileInfos = this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		
		if (fileInfos == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		GridFsResource resource = this.gridFsTemplate.getResource(fileInfos);
		InputStreamResource binaryData = new InputStreamResource(
				resource.getInputStream());
		
		HttpHeaders headers = new HttpHeaders();
		
		// le content type
		headers.setContentType(MediaType.parseMediaType(resource.getContentType()));
		// la taille du fichier
		headers.setContentLength(resource.contentLength());
		// le nom du fichier original
		headers.set("Content-Disposition", "attachment; filename="+ resource.getFilename());
		
		return new ResponseEntity<InputStreamResource>(binaryData, headers, HttpStatus.OK);
		
	}
	*/
	
}
