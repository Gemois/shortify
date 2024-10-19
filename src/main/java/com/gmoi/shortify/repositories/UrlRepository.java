package com.gmoi.shortify.repositories;

import com.gmoi.shortify.entities.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
}
