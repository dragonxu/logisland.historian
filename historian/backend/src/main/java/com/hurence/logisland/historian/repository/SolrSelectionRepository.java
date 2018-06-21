package com.hurence.logisland.historian.repository;

import com.hurence.logisland.historian.rest.v1.model.PrivateSelection;
import com.hurence.logisland.historian.rest.v1.model.Selection;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolrSelectionRepository extends SolrCrudRepository<PrivateSelection, String> {

    @Query(value = "*:*", filters = { "?0", "record_type:selection" })
    List<PrivateSelection> findByText(String text);
}