package com.example.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Jasypt加解密
 *
 * @author wakening
 */
@Slf4j
@RequestMapping("/jasypt")
@RestController
public class JasyptController {

    @Autowired
    private StringEncryptor encryptor;

    @PostMapping("/encrypt")
    public Map<String, Object> encrypt(@RequestBody String str) {
        String encrypt = encryptor.encrypt(str);
        log.info("{}加密: {}", str, encrypt);
        Map<String, Object> map = new HashMap<>(4);
        map.put(str, encrypt);
        return map;
    }

    @PostMapping("/decrypt")
    public Map<String, Object> decrypt(@RequestBody String encStr) {
        log.info(encStr);
        String decryptStr = encryptor.decrypt(encStr);
        log.info("{}解密: {}", encStr, decryptStr);
        Map<String, Object> map = new HashMap<>(4);
        map.put(encStr, decryptStr);
        return map;
    }
}
