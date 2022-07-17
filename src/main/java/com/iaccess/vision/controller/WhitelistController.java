package com.iaccess.vision.controller;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.service.IWhitelistService;
import com.iaccess.vision.data.entity.WhitelistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/whitelists")
public class WhitelistController {

    @Autowired
    private IWhitelistService whitelistService;

    @GetMapping
    public List<WhitelistEntity> list(){
        return whitelistService.list();
    }

    @PostMapping
    public WhitelistEntity create(@RequestBody final WhitelistForm form) {
        return whitelistService.create(form);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long id) {
        whitelistService.delete(id);
    }
}
