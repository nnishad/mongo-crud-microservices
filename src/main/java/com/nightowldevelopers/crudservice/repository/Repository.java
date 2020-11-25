/**
 * 
 */
package com.nightowldevelopers.crudservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nightowldevelopers.crudservice.model.Model;

/**
 * @author nikhi
 *
 */
@org.springframework.stereotype.Repository
public interface Repository extends MongoRepository<Model, String> {
	  List<Model> findByTitleContaining(String title);
	  List<Model> findByPublished(boolean published);
}