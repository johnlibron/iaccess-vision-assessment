package com.iaccess.vision.controller;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.controller.form.WhitelistSearchForm;
import com.iaccess.vision.data.entity.WhitelistEntity;
import com.iaccess.vision.data.model.ResponseModel;
import com.iaccess.vision.service.IWhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/whitelists")
public class WhitelistController {

    @Autowired
    private IWhitelistService whitelistService;

    @PostMapping(value = "search")
    public ResponseModel<List<String>> search(@RequestBody WhitelistSearchForm form) {
        try {
            return whitelistService.search(form);
        } catch (Exception e) {
            return new ResponseModel<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseModel<WhitelistEntity> create(@RequestBody @Valid WhitelistForm form) {
        try {
            return whitelistService.create(form);
        } catch (Exception e) {
            return new ResponseModel<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "{ip_address}")
    public ResponseModel<Void> delete(@PathVariable("ip_address") String ipAddress) {
        try {
            return whitelistService.delete(ipAddress);
        } catch (Exception e) {
            return new ResponseModel<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
