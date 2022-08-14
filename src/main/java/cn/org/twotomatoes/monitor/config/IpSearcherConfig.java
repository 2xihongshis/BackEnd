package cn.org.twotomatoes.monitor.config;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Objects;

/**
 * @author HeYunjia
 */

@Configuration
public class IpSearcherConfig {

    @Bean
    public Searcher searcher() throws IOException {
        String dbPath = Objects.requireNonNull(
                getClass().getClassLoader().getResource("xdb/ip2region.xdb"))
                .getPath();
        byte[] cBuff = Searcher.loadContentFromFile(dbPath);
        return Searcher.newWithBuffer(cBuff);
    }

}
