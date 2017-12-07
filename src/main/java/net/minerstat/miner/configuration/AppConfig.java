package net.minerstat.miner.configuration;

import net.minerstat.miner.aspect.StatisticAspect;
import org.aspectj.lang.Aspects;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@ImportResource("spring.xml")
public class AppConfig {

    @Bean
    public StatisticAspect statisticAspect() {
        StatisticAspect aspect = Aspects.aspectOf(StatisticAspect.class);
        return aspect;
    }
}
