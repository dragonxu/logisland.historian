package com.hurence.logisland.historian.service;

import com.hurence.logisland.historian.repository.OpcRepository;
import com.hurence.logisland.historian.repository.SolrSelectionRepository;
import com.hurence.logisland.historian.repository.SolrTagRepository;
import com.hurence.logisland.historian.rest.v1.api.NotFoundException;
import com.hurence.logisland.historian.rest.v1.model.*;
import com.hurence.opc.da.OpcDaConnectionProfile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SelectionsApiService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private TagsApiService tagsApiService;
    private SecurityService securityService;
    @Resource
    private SolrSelectionRepository repository;

    private long socketTimeoutMillis;

    @Autowired
    public void setTagsApiService(TagsApiService tagsApiService) {
        this.tagsApiService = tagsApiService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setSelectionRepository(SolrSelectionRepository repository) {
        this.repository = repository;
    }

    @Value("${opc.socket.timeout:2000}")
    public void setSocketTimeoutMillis(long socketTimeoutMillis) {
        this.socketTimeoutMillis = socketTimeoutMillis;
    }

    public Optional<PrivateSelection> deleteSelection(String itemId) {
        logger.info("deleting Selection {}", itemId);
        Optional<PrivateSelection> selectionToRemove = repository.findById(itemId);
        if (selectionToRemove.isPresent()) {
            repository.delete(selectionToRemove.get());
        }
        return selectionToRemove;
    }


    public Optional<PrivateSelection> getSelection(String itemId) {
        logger.debug("getting Selection {}", itemId);
        return repository.findById(itemId);
    }


    public Optional<PrivateSelection> updateSelection(PrivateSelection selection) {
        logger.debug("updating Selection {}", selection.getId());
        if (repository.existsById(selection.getId())) {
            return Optional.of(repository.save(selection));
        } else {
            logger.error("Selection {} not found, unable to update", selection.getId());
            return Optional.empty();
        }
    }

    public Optional<PrivateSelection> updateSelection(PrivateSelection selection, String itemId) {
        if (!selection.getId().equals(itemId)) {
            return updateSelection(selection.id(itemId));
        } else {
            return updateSelection(selection);
        }
    }

    public Optional<PrivateSelection> addSelectionWithId(PrivateSelection selection, String itemId) {
        Optional<PrivateSelection> datasourceToRemove = getSelection(itemId);
        if (datasourceToRemove.isPresent()) {
            logger.info("Selection already {} exists, delete it first", itemId);
            return Optional.empty();
        } else {
            selection.setId(itemId);
            return Optional.of(repository.save(selection));
        }
    }

    public List<PrivateSelection> getAllUserSelection() {
        String owner = this.securityService.getUserName();
        String query = "owner:" + owner;

        List<PrivateSelection> selections = repository.findByText(query);

        return selections;
    }
}
