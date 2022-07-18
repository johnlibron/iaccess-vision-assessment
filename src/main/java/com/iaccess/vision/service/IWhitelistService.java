package com.iaccess.vision.service;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.controller.form.WhitelistSearchForm;
import com.iaccess.vision.data.entity.WhitelistEntity;
import com.iaccess.vision.data.model.ResponseModel;

import java.util.List;

public interface IWhitelistService {

    ResponseModel<List<String>> search(WhitelistSearchForm form);

    ResponseModel<WhitelistEntity> create(WhitelistForm form) throws Exception;

    ResponseModel<Void> delete(String ipAddress) throws Exception;
}
