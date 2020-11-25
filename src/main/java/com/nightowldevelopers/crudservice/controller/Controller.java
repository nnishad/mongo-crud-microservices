package com.nightowldevelopers.crudservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nightowldevelopers.crudservice.model.Model;
import com.nightowldevelopers.crudservice.service.Service;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	Service service;

	@GetMapping("/data")
	public ResponseEntity<List<Model>> getAllData(@RequestParam(required = false) String title) {
		try {
			List<Model> response = service.fetchAllData(title);
			if (response.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/data/{id}")
	public ResponseEntity<Model> getDataById(@PathVariable("id") String id) {
		Optional<Model> _data = service.findById(id);
		  if (_data.isPresent()) {
		    return new ResponseEntity<>(_data.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }

	}

	@PostMapping("/data")
	public ResponseEntity<Model> createTutorial(@RequestBody Model data) {
		try {
			Model _data = service.save(new Model(data.getTitle(), data.getDescription(), false));
			return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/data/{id}")
	public ResponseEntity<Model> updateDataById(@PathVariable("id") String id, @RequestBody Model data) {
		Model _data = service.updateDataById(id,data);

		  if (_data!=null) {
		    return new ResponseEntity<>(_data, HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	}

	@DeleteMapping("/data/{id}")
	public ResponseEntity<HttpStatus> deleteDataById(@PathVariable("id") String id) {
		try {
		    service.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	@DeleteMapping("/data")
	public ResponseEntity<HttpStatus> deleteAllData() {
		try {
		    service.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	@GetMapping("/data/published")
	public ResponseEntity<List<Model>> findByPublished() {
		try {
		    List<Model> _data = service.findByPublished(true);

		    if (_data.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(_data, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }

	}

}
