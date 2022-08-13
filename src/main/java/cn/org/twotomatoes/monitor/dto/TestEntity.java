package cn.org.twotomatoes.monitor.dto;

import lombok.Data;

/**
 * @author HeYunjia
 */
@Data
public class TestEntity {
    int a;
    int b;
    public TestEntity(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
