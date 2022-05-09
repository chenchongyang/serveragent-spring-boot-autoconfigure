
package cn.chenchongyang.serveragent.spring.boot.autoconfigure;

import cn.chenchongyang.serveragent.spring.ServerAgentFactorBean;
import cn.chenchongyang.serveragent.spring.ServerAgentScannerConfigurer;
import cn.chenchongyang.serveragent.spring.annotations.ServerAgent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 类简要描述
 *
 * @author chenchongyang
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
 * @since 2022-03-29
 */
@Configuration
@EnableConfigurationProperties(ServerAgentProperties.class)
public class ServerAgentAutoConfiguration implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ServerAgentAutoConfiguration.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public static class AutoConfiguredServerAgentScannerRegistrar
        implements BeanFactoryAware, ImportBeanDefinitionRegistrar {

        private BeanFactory beanFactory;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
            BeanDefinitionRegistry registry) {

            if (!AutoConfigurationPackages.has(beanFactory)) {
                logger.debug("Could not determine auto-configuration package, automatic mapper scanning disabled.");
                return;
            }

            logger.debug("Searching for serverAgent annotated with @ServerAgent");

            List<String> packages = AutoConfigurationPackages.get(beanFactory);

            if (logger.isDebugEnabled()) {
                packages.forEach(pkg -> logger.debug("Using auto-configuration base package '{}'", pkg));
            }

            BeanDefinitionBuilder builder =
                BeanDefinitionBuilder.genericBeanDefinition(ServerAgentScannerConfigurer.class);
            builder.addPropertyValue("annotationClass", ServerAgent.class);
            builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(packages));

            // BeanWrapper beanWrapper = new BeanWrapperImpl(ServerAgentScannerConfigurer.class);
            // Set<String> propertyNames = Stream.of(beanWrapper.getPropertyDescriptors())
            // .map(PropertyDescriptor::getName)
            // .collect(Collectors.toSet());

            registry.registerBeanDefinition(ServerAgentScannerConfigurer.class.getName(), builder.getBeanDefinition());

        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }
    }

    @Configuration
    @Import(AutoConfiguredServerAgentScannerRegistrar.class)
    @ConditionalOnMissingBean({ServerAgentFactorBean.class, ServerAgentScannerConfigurer.class})
    public static class ServerAgentScannerRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            logger.debug(
                "Not found configuration for registering serverAgent bean using @ServerAgentScan, ServerAgentFactorBean and ServerAgentScannerConfigurer.");
        }
    }

}
