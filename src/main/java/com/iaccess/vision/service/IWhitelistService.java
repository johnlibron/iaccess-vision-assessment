package com.iaccess.vision.service;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.controller.form.WhitelistSearchForm;
import com.iaccess.vision.data.entity.WhitelistEntity;

import java.util.List;

public interface IWhitelistService {

    List<String> search(WhitelistSearchForm form);

    WhitelistEntity create(WhitelistForm form) throws Exception;

    void delete(String ipAddress) throws Exception;
}
