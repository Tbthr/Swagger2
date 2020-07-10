package com.lyq.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启Swagger2的自动配置
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group1");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group2");
    }

    //配置docket以配置Swagger具体参数
    @Bean
    public Docket docket(Environment environment) {

        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("group3")
                .apiInfo(apiInfo())
                .enable(b) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select() //通过.select()方法,去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.lyq.swagger.controller"))
                /**
                 * RequestHandlerSelectors下的所有方式：
                 *
                 * //扫描所有，项目中的所有接口都会被扫描到
                 * any()
                 *
                 * //不扫描接口
                 * none()
                 *
                 * // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
                 * withMethodAnnotation(final Class<? extends Annotation> annotation)
                 *
                 * // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
                 * withClassAnnotation(final Class<? extends Annotation> annotation)
                 *
                 * // 根据包路径扫描接口
                 * basePackage(final String basePackage)
                 */
                .paths(PathSelectors.ant("/**")) //配置通过path过滤,即这里只扫描请求以/开头的接口
                /**
                 * PathSelectors下的所有方式：
                 *
                 * //任何请求都扫描
                 * any()
                 *
                 * //任何请求都不扫描
                 * none()
                 *
                 * //通过正则表达式控制
                 * regex(final String pathRegex)
                 *
                 * //通过ant()控制
                 * ant(final String antPattern)
                 */
                .build();
    }

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("刘以强", "http://www.thtmf.xyz/", "1598622578@qq.com");
        return new ApiInfo(
                "Swagger学习", //标题
                "The header, the more fortunate", //描述
                "v1.0", //版本
                "http://www.thtmf.xyz/", //组织链接
                contact, //联系人信息
                "Apache 2.0 许可", //许可
                "http://www.apache.org/licenses/LICENSE-2.0", //许可连接
                new ArrayList<>() //扩展
        );
    }
}
