
package cn.chenchongyang.serveragent.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 类简要描述
 *
 * @author chenchongyang
 * @see org.mybatis.spring.boot.autoconfigure.MybatisProperties
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
 * @since 2022-03-28
 */
@ConfigurationProperties(prefix = ServerAgentProperties.SERVER_AGENT_PREFIX)
public class ServerAgentProperties {

    public static final String SERVER_AGENT_PREFIX = "server.agent";

    private String timeout;

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
