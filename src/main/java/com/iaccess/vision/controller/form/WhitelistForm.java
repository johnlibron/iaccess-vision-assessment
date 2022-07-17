package com.iaccess.vision.controller.form;

import com.iaccess.vision.data.enumerate.ApplicationEnum;
import com.iaccess.vision.data.enumerate.EnvironmentEnum;
import lombok.Data;

@Data
public class WhitelistForm {

    private String clientName;

    private String ipAddress;

    private ApplicationEnum applicationName;

    private EnvironmentEnum environmentName;
}
