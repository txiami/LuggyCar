package com.br.luggycar.api.services;

import com.br.luggycar.api.entities.OptionalItem;
import com.br.luggycar.api.repositories.OptionalItemRepository;
import com.br.luggycar.api.requests.OptionalItemRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionalItemService {

    @Autowired
    private OptionalItemRepository optionalItemRepository;

    public List<OptionalItem> findAllOptionalItem() {
        return optionalItemRepository.findAll();
    }

    public Optional<OptionalItem> findOptionalItemById(Long id) {
        return optionalItemRepository.findById(id);
    }

    public OptionalItem insertOptionalItem(OptionalItemRequest optionalItemRequest) {
        OptionalItem optionalItem = new OptionalItem();
        BeanUtils.copyProperties(optionalItemRequest, optionalItem);
        return optionalItemRepository.save(optionalItem);
    }

    public OptionalItem updateOptionalItem(Long id, OptionalItemRequest optionalItemRequest) {
        Optional<OptionalItem> optionalItem = findOptionalItemById(id);

        if (optionalItem.isPresent()) {
            OptionalItem optionalItemToUpdate = optionalItem.get();
            BeanUtils.copyProperties(optionalItemRequest, optionalItemToUpdate);
            return optionalItemRepository.save(optionalItemToUpdate);
        }

        return null;
    }

    public boolean deleteOptionalItem(Long id) {
        Optional<OptionalItem> optionalItem = findOptionalItemById(id);

        if (optionalItem.isPresent()) {
            optionalItemRepository.delete(optionalItem.get());
            return true;
        }

        return false;
    }
}
