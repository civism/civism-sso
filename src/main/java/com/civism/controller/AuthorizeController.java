package com.civism.controller;

import com.civism.error.ErrorType;
import com.civism.utils.SsoResponse;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author star
 * @date 2018/8/20 下午3:36
 */
@RestController
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class AuthorizeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public ResponseEntity authorize(HttpServletRequest request) {
        String url = request.getParameter("url");
        logger.info("url={}", url);
        if (SecurityUtils.getSubject().isPermitted(url)) {
            return new ResponseEntity(new SsoResponse<>(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new SsoResponse<>(ErrorType.NO_AUTHORITY), HttpStatus.OK);
        }
    }
}
