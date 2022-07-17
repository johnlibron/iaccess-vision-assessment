package com.iaccess.vision.controller;

import com.iaccess.vision.controller.form.WhitelistForm;
import com.iaccess.vision.controller.form.WhitelistSearchForm;
import com.iaccess.vision.service.IWhitelistService;
import com.iaccess.vision.data.entity.WhitelistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/whitelists")
public class WhitelistController {

    @Autowired
    private IWhitelistService whitelistService;

    @PostMapping(value = "search")
    public Set<String> search(@RequestBody WhitelistSearchForm form){
        return whitelistService.search(form);
    }

    @PostMapping
    public WhitelistEntity create(@RequestBody @Valid WhitelistForm form) {
        return whitelistService.create(form);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long id) {
        whitelistService.delete(id);
    }
}
