package org.data.traffic_01.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.data.traffic_01.entity.TestReqVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisTestController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping(value = "/setRedisKeyValue", method = RequestMethod.POST)
    public ResponseEntity<?> setRedisKeyValue(HttpServletRequest request) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("name","강만두");
        ops.set("age","10");
        ops.set("country","korea");
        ops.set("gender","공주님");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getRedisKeyValue", method = RequestMethod.GET)
    public ResponseEntity<?> getRediskeyValue(TestReqVO req, HttpServletRequest request) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(req.getKey());
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

}
