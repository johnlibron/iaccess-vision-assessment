package com.iaccess.vision.service;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.data.entity.WhitelistEntity;

import java.util.List;

public interface IWhitelistService {

    List<WhitelistEntity> list();

    WhitelistEntity create(WhitelistForm form);

    void delete(Long id);
}
