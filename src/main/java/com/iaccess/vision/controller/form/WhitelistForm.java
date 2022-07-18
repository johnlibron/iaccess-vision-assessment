package com.iaccess.vision.controller.form;

import com.iaccess.vision.data.enumerate.ApplicationEnum;
import com.iaccess.vision.data.enumerate.EnvironmentEnum;
import com.iaccess.vision.utility.Utility;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class WhitelistForm {

    @NotEmpty
    private String clientName;

    @NotEmpty
    @Pattern(regexp= Utility.IPV4_PATTERN)
    private String ipAddress;

    @NotNull
    private ApplicationEnum applicationName;

    @NotNull
    private EnvironmentEnum environmentName;
}
