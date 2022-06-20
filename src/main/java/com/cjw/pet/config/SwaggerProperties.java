package com.cjw.pet.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cjw
 * @date 2022/3/7
 */
@ConfigurationProperties("pet-swagger")
@Data
public class SwaggerProperties {

    private Boolean enable = true;

    private ApiInfoProperties apiInfo = new ApiInfoProperties();

    @Data
    public static class ApiInfoProperties {
        /**
         * 标题
         */
        private String title;
        /**
         * 描述
         */
        private String description;
        /**
         * 版本
         */
        private String version;
    }
}
