package zerobase.weather.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyResource implements AutoCloseable{

    @Override
    public void close() throws Exception {
        log.info("ClosedMyResource");
    }
}
