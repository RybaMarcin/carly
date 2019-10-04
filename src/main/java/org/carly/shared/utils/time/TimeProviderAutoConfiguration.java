package org.carly.shared.utils.time;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.ds.config.time.service", "com.ds.config.time.api"})
public class TimeProviderAutoConfiguration {

}
