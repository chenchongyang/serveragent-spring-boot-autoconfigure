# serveragent-spring-boot-autoconfigure

serveragent自动装配spring-boot

- spring.factories

  自动扫描配置类入口

- additional-spring-configuration-metadata.json

  IDE在`application.properties`或`application.yml`文件下对配置项的详细描述

- spring-configuration-metadata.json

  IDE读取该文件，进行已有配置项提示

- spring-autoconfigure-metadata.properties

  待自动装配候选类的过滤条件