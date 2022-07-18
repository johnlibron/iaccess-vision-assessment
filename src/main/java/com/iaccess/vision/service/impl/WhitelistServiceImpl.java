package com.iaccess.vision.service.impl;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.controller.form.WhitelistSearchForm;
import com.iaccess.vision.data.mapper.WhitelistMapper;
import com.iaccess.vision.service.IWhitelistService;
import com.iaccess.vision.data.entity.WhitelistEntity;
import com.iaccess.vision.data.repository.WhitelistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WhitelistServiceImpl implements IWhitelistService {

    @Autowired
    private WhitelistMapper whitelistMapper;

    @Autowired
    private WhitelistRepository whitelistRepository;

    @Override
    public List<String> search(WhitelistSearchForm form) {
        String clientName = null;
        String appName = null;
        String envName = null;

        if (form.getClientName() != null && !form.getClientName().trim().isEmpty()) {
            clientName = form.getClientName().trim();
        }
        if (form.getApplicationName() != null) {
            appName = form.getApplicationName().name();
        }
        if (form.getEnvironmentName() != null) {
            envName = form.getEnvironmentName().name();
        }

        return whitelistMapper.searchIpAddress(clientName, appName, envName);
    }

    @Override
    @Transactional
    public WhitelistEntity create(WhitelistForm form) throws Exception {
        WhitelistEntity entity = new WhitelistEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setApplicationName(form.getApplicationName().name());
        entity.setEnvironmentName(form.getEnvironmentName().name());
        boolean isExists = whitelistRepository.exists(Example.of(entity));
        if (isExists) {
            throw new Exception("Whitelist is already existed - " + form);
        } else {
            return whitelistRepository.save(entity);
        }
    }

    @Override
    @Transactional
    public void delete(String ipAddress) throws Exception {
        WhitelistEntity entity = new WhitelistEntity();
        entity.setIpAddress(ipAddress);
        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                .withMatcher("ipAddress", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<WhitelistEntity> example = Example.of(entity, customExampleMatcher);
        Optional<WhitelistEntity> optional = whitelistRepository.findOne(example);
        if (optional.isEmpty()) {
            throw new Exception("IP Address not found - " + ipAddress);
        }
        whitelistRepository.deleteById(optional.get().getId());
    }
}
