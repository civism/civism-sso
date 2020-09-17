package com.civism.sso;

import com.civism.sso.entity.SsoProductDO;
import com.civism.sso.service.SsoProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SsoApplicationTests {

    @Resource
    private SsoProductService ssoProductService;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void 测试redis() {
        redisTemplate.opsForValue().set("star", "123");

        Object star = redisTemplate.opsForValue().get("star");
        System.out.println(star);
    }

    @Test
    void contextLoads() {
        PageHelper.startPage(1, 10);
        List<SsoProductDO> list = ssoProductService.list();
        PageInfo<SsoProductDO> pageInfo = new PageInfo<>(list);

        System.out.println(pageInfo);
    }

}
