package com.gmoi.shortify.repositories;

import com.gmoi.shortify.entities.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
    List<Url> findByExpirationDateBefore(LocalDateTime now);
    List<Url> findByLastClickAtBefore(LocalDateTime date);
}
