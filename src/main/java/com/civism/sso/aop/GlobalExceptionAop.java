package com.civism.sso.aop;

import com.civism.sso.entity.Response;
import com.civism.sso.enums.CustomExceptionEnum;
import com.civism.sso.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Component
@Aspect
@Slf4j
public class GlobalExceptionAop {

    private final String POINT_CUT = "execution(public * com.civism.sso.controller.*.*(..))";

    @Around(value = POINT_CUT)
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature = proceedingJoinPoint.getSignature();
        long t = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();
            printErrorLog(proceedingJoinPoint, signature, t);
            return result;
        } catch (CustomException ex) {
            printErrorLog(proceedingJoinPoint, signature, t);
            return new Response<>(ex);
        } catch (Exception e) {
            printErrorLog(proceedingJoinPoint, signature, t);
            return new Response<>(CustomExceptionEnum.SYSTEM_ERROR);
        } catch (Throwable throwable) {
            printErrorLog(proceedingJoinPoint, signature, t);
            return new Response<>(CustomExceptionEnum.SYSTEM_ERROR);
        }
    }

    private void printErrorLog(ProceedingJoinPoint proceedingJoinPoint, Signature signature, long t) {
        log.info("类名:{}-方法名:{}-传入参数:{}###总共耗时:{}毫秒", signature.getDeclaringTypeName(),
                signature.getName(), proceedingJoinPoint.getArgs(),
                System.currentTimeMillis() - t);
    }

}
