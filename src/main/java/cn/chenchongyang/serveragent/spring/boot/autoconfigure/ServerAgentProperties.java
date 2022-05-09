
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

    public static final String SERVER_AGENT_PREFIX = "server-agent";

    /**
     * 超时设置
     */
    private Integer timeout;

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
