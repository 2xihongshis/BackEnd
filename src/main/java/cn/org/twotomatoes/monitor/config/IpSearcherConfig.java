package cn.org.twotomatoes.monitor.config;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author HeYunjia
 */

@Configuration
public class IpSearcherConfig {

    @Bean
    public Searcher searcher() throws IOException {
        InputStream dbStream = new ClassPathResource("xdb/ip2region.xdb").getInputStream();
        byte[] data = new byte[dbStream.available()];

        int readCount = 0;
        while (readCount < data.length)
            readCount += dbStream.read(data, readCount, data.length - readCount);

        return Searcher.newWithBuffer(data);
    }

}
