package com.frikwensi.billingintegration;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class UploadsController {
    @RequestMapping("/")
    public String index() {
	return "The Uploads will be Here";
    }
}
