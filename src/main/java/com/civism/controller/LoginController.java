package com.civism.controller;


import com.civism.constants.SsoConstants;
import com.civism.dao.RedisClient;
import com.civism.error.ErrorType;
import com.civism.service.UserService;
import com.civism.shiro.SsoUserNameToken;
import com.civism.utils.SerializeUtil;
import com.civism.utils.SsoResponse;
import com.civism.vo.LoginEntity;
import com.civism.vo.LoginWayEnum;
import com.civism.vo.Menu;
import com.civism.vo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * @author star
 * @date 2018/5/10 上午11:16
 */
@RestController
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private RedisClient redisClient;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login/loginOut", method = RequestMethod.GET)
    public ResponseEntity loginOut(HttpServletRequest request) {
        String token = getToken(request);
        String callback = request.getParameter("callback");
        userService.loginOut(token);
        return new ResponseEntity(new SsoResponse<>().getJSONP(callback), HttpStatus.OK);
    }


    @RequestMapping(value = "/login/token", method = RequestMethod.GET)
    public ResponseEntity check(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            return new ResponseEntity(new SsoResponse<>(ErrorType.COOKIE_ERROR).getJSONP(callback), HttpStatus.OK);
        }
        String sessionId = null;
        for (Cookie cookie : cookies) {
            if (SsoConstants.SESSIONID.equals(cookie.getName())) {
                sessionId = cookie.getValue();
                break;
            }
        }
        if (StringUtils.isEmpty(sessionId)) {
            return new ResponseEntity(new SsoResponse<>(ErrorType.COOKIE_ERROR).getJSONP(callback), HttpStatus.OK);
        }
        byte[] bytes = redisClient.get(sessionId);
        if (bytes == null || bytes.length <= 0) {
            return new ResponseEntity(new SsoResponse<>(ErrorType.TOKEN_INVALID).getJSONP(callback), HttpStatus.OK);
        }
        UserInfo userInfo = SerializeUtil.deserialize(bytes, UserInfo.class);
        return new ResponseEntity(new SsoResponse<>(userInfo).getJSONP(callback), HttpStatus.OK);
    }


    @RequestMapping(value = "/login/index", method = RequestMethod.GET)
    public ResponseEntity login(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String way = request.getParameter("way");
        String callback = request.getParameter("callback");
        StringBuffer url = request.getRequestURL();
        String hostName = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        if (StringUtils.isEmpty(way)) {
            return new ResponseEntity(new SsoResponse(ErrorType.INVALID_ARGUMENT).getJSONP(callback), HttpStatus.OK);
        }
        if (LoginWayEnum.ACCOUNT_LOGIN.getWay().equals(Integer.valueOf(way))) {
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
                return new ResponseEntity(new SsoResponse(ErrorType.INVALID_ARGUMENT).getJSONP(callback), HttpStatus.OK);
            }
        }
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setPassword(password);
        loginEntity.setWay(Integer.parseInt(way));
        loginEntity.setUserName(userName);
        loginEntity.setHostName(hostName);
        SsoUserNameToken ssoUserNameToken = new SsoUserNameToken(loginEntity);
        try {
            SecurityUtils.getSubject().login(ssoUserNameToken);
        } catch (UnknownAccountException e) {
            logger.error("login error ", e);
            return new ResponseEntity(new SsoResponse<>(ErrorType.USER_NO_EXIST).getJSONP(callback), HttpStatus.OK);
        } catch (IncorrectCredentialsException e) {
            logger.error("login error", e);
            return new ResponseEntity(new SsoResponse<>(ErrorType.PASSWORD_ERROR).getJSONP(callback), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseEntity(new SsoResponse<>(ErrorType.SYSTEM_ERROR).getJSONP(callback), HttpStatus.OK);
        }
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return new ResponseEntity(new SsoResponse<>(userInfo).getJSONP(callback), HttpStatus.OK);
    }

    @RequestMapping(value = "/login/getMenus", method = RequestMethod.GET)
    public ResponseEntity getMenus(HttpServletRequest request) {
        String hostName = request.getParameter("hostName");
        String callback = request.getParameter("callback");
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return new ResponseEntity(new SsoResponse(ErrorType.INVALID_ARGUMENT).getJSONP(callback), HttpStatus.OK);
        }
        List<Menu> menuBOList = new ArrayList<>();

        return new ResponseEntity(new SsoResponse<>(menuBOList).getJSONP(callback), HttpStatus.OK);
    }
}
