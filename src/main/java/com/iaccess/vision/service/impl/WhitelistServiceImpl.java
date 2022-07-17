package com.iaccess.vision.service.impl;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.controller.form.WhitelistSearchForm;
import com.iaccess.vision.data.enumerate.ApplicationEnum;
import com.iaccess.vision.data.enumerate.EnvironmentEnum;
import com.iaccess.vision.service.IWhitelistService;
import com.iaccess.vision.data.entity.WhitelistEntity;
import com.iaccess.vision.data.repository.WhitelistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WhitelistServiceImpl implements IWhitelistService {

    @Autowired
    private WhitelistRepository whitelistRepository;

    @Override
    public Set<String> search(WhitelistSearchForm form) {
        String clientName = form.getClientName();
        Enum<ApplicationEnum> appName = form.getApplicationName();
        Enum<EnvironmentEnum> envName = form.getEnvironmentName();

        Stream<WhitelistEntity> stream = whitelistRepository.findAll().stream();

        if (clientName != null && !clientName.trim().isEmpty()) {
            stream = stream.filter(e -> e.getClientName().contains(clientName.trim()));
        }
        if (appName != null) {
            stream = stream.filter(e -> e.getApplicationName().equals(appName.name()));
        }
        if (envName != null) {
            stream = stream.filter(e -> e.getEnvironmentName().equals(envName.name()));
        }

        return stream.map(WhitelistEntity::getIpAddress).collect(Collectors.toSet());
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
    @Transactional
    public void delete(Long id) {
        final Optional<WhitelistEntity> optional = whitelistRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException(String.valueOf(id));
        }
        whitelistRepository.deleteById(id);
    }
}
