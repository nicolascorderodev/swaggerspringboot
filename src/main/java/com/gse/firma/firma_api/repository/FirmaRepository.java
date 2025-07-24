package com.gse.firma.firma_api.repository;

import com.gse.firma.firma_api.model.FirmaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FirmaRepository extends MongoRepository<FirmaDocument, String> {
}
