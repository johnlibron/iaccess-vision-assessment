package com.iaccess.vision.service.impl;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.service.IWhitelistService;
import com.iaccess.vision.data.entity.WhitelistEntity;
import com.iaccess.vision.data.repository.WhitelistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WhitelistServiceImpl implements IWhitelistService {

    @Autowired
    private final WhitelistRepository whitelistRepository;

    public WhitelistServiceImpl(WhitelistRepository whitelistRepository) {
        this.whitelistRepository = whitelistRepository;
    }

    @Override
    public List<WhitelistEntity> list() {
        return null;
    }

    @Override
    @Transactional
    public WhitelistEntity create(WhitelistForm form) {
        WhitelistEntity entity = new WhitelistEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setApplicationName(form.getApplicationName().name());
        entity.setEnvironmentName(form.getEnvironmentName().name());
        return whitelistRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        final Optional<WhitelistEntity> optional = whitelistRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException(String.valueOf(id));
        }
        whitelistRepository.deleteById(id);
    }
}
