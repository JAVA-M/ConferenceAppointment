package com.mxf;



/**
 * @author manxingfu
 * @date 2023/1/13
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class MyTest {
    public static void main(String[] args) {
        System.out.println("'spring:\n  redis:\n    host: localhost\n    port: 6379\n    password:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: ruoyi-auth\n          uri: lb:/ruoyi-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - ValidateCodeFilter\n            - StripPrefix=1\n        # 代码生成\n        - id: ruoyi-gen\n          uri: lb:/ruoyi-gen\n          predicates:\n            - Path=/code/**\n          filters:\n            - StripPrefix=1\n        # 定时任务\n        - id: ruoyi-job\n          uri: lb:/ruoyi-job\n          predicates:\n            - Path=/schedule/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: ruoyi-system\n          uri: lb:/ruoyi-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 文件服务\n        - id: ruoyi-file\n          uri: lb:/ruoyi-file\n          predicates:\n            - Path=/file/**\n          filters:\n            - StripPrefix=1\n\n# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    enabled: true\n    type: math\n  # 防止XSS攻击\n  xss:\n    enabled: true\n    excludeUrls:\n      - /system/notice\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/logout\n      - /auth/login\n      - /auth/register\n      - /*/v2/api-docs\n      - /csrf\n','57cec5abd0e0a6b77d853750344a9dc0','2020-05-14 14:17:55','2022-09-29 02:48:32','nacos','0:0:0:0:0:0:0:1','','','网关模块','null','null','yaml','',''");
    }

    /*@Value("${map}")
    private Map<String, String> map;*/

    /*@Value("${spring.application.name}")
    private String name;

    @Autowired
    private  RedisService redisService;

    @Autowired
    private MailService mailService;*/

    /*@Test*/
    /*public void mailTest() {
        Mail mail = new Mail();
        mail.setSubject("测试");
        mail.setContent("预约系统集成邮件测试");
        ArrayList<String> address = new ArrayList<>();
        address.add("2519307389@qq.com");
        address.add("manxingfu2022@163.com");
        mail.setAddresses(address);
        System.out.println(mailService.send(mail) ? "发送成功" : "发送失败");
    }*/
}
