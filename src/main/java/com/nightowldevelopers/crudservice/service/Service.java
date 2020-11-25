package com.nightowldevelopers.crudservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.nightowldevelopers.crudservice.model.Model;
import com.nightowldevelopers.crudservice.repository.Repository;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	Repository repo;

	public Model save(Model model) {
		return repo.save(model);
	}

	public List<Model> fetchAllData(String title) {
		List<Model> data = new ArrayList<Model>();

		if (title == null)
			repo.findAll().forEach(data::add);
		else
			repo.findByTitleContaining(title).forEach(data::add);

		return data;

	}

	public Optional<Model> findById(String id) {
		return repo.findById(id);
	}

	public List<Model> findByPublished(boolean published) {
		return repo.findByPublished(published);
	}

	public Model updateDataById(String id, Model data) {
		Optional<Model> _data = repo.findById(id);
		if (_data.isPresent()) {
			Model newData = _data.get();
			newData.setTitle(data.getTitle());
			newData.setDescription(data.getDescription());
			newData.setPublished(data.isPublished());
			return repo.save(newData);
		} else {
			return null;
		}
	}

	public void deleteById(String id) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			throw e;
		}

	}

	public void deleteAll() {
		try {
			repo.deleteAll();
		} catch (Exception e) {
			throw e;
		}
	}

}
