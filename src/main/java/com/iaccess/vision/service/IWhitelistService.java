package com.iaccess.vision.service;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.controller.form.WhitelistSearchForm;
import com.iaccess.vision.data.entity.WhitelistEntity;

import java.util.Set;

public interface IWhitelistService {

    Set<String> search(WhitelistSearchForm form);

    WhitelistEntity create(WhitelistForm form);

    void delete(Long id);
}
