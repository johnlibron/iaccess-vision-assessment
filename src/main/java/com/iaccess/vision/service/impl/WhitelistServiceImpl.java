package com.iaccess.vision.service.impl;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.controller.form.WhitelistSearchForm;
import com.iaccess.vision.data.mapper.WhitelistMapper;
import com.iaccess.vision.data.model.ResponseModel;
import com.iaccess.vision.service.IWhitelistService;
import com.iaccess.vision.data.entity.WhitelistEntity;
import com.iaccess.vision.data.repository.WhitelistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WhitelistServiceImpl implements IWhitelistService {

    @Autowired
    private WhitelistMapper whitelistMapper;

    @Autowired
    private WhitelistRepository whitelistRepository;

    @Override
    public ResponseModel<List<String>> search(WhitelistSearchForm form) {
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
        List<String> list = whitelistMapper.searchIpAddress(clientName, appName, envName);

        if (list.isEmpty()) {
            return new ResponseModel<>(list, HttpStatus.NO_CONTENT);
        }

        return new ResponseModel<>(list, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseModel<WhitelistEntity> create(WhitelistForm form) {
        WhitelistEntity entity = new WhitelistEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setApplicationName(form.getApplicationName().name());
        entity.setEnvironmentName(form.getEnvironmentName().name());
        boolean isExists = whitelistRepository.exists(Example.of(entity));
        if (isExists) {
            return new ResponseModel<>("Whitelist is already existed - " + form, HttpStatus.CONFLICT);
        } else {
            return new ResponseModel<>(whitelistRepository.save(entity), HttpStatus.CREATED);
        }
    }

    @Override
    @Transactional
    public ResponseModel<Void> delete(String ipAddress) {
        WhitelistEntity entity = new WhitelistEntity();
        entity.setIpAddress(ipAddress);
        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                .withMatcher("ipAddress", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<WhitelistEntity> example = Example.of(entity, customExampleMatcher);
        List<WhitelistEntity> list = whitelistRepository.findAll(example);
        if (list.isEmpty()) {
            return new ResponseModel<>("IP Address not found - " + ipAddress, HttpStatus.NOT_FOUND);
        }
        whitelistRepository.deleteAll(list);
        return new ResponseModel<>(HttpStatus.NO_CONTENT);
    }
}
