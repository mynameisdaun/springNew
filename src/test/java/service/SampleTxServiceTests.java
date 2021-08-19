package service;


import com.sun.source.tree.AssertTree;
import config.RootConfig;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration(classes = {RootConfig.class})
public class SampleTxServiceTests {
    @Setter(onMethod_ = {@Autowired})
    private SampleTxService service;

    @Test
    public void testLong() {
        String str = "Starry\r\n" +
                     "Paint your palette blue and grey \r\n" +
                     "Look out on a summer`s daㅅㄷㅅㄷㄱㅅㄷㄱㅅㄷㅅㄷㄱㅅㄷㅅㄷㄱㅅㄷㄱㅅㄷㄱㅅy";

        log.info(str.getBytes().length);
        service.addData(str);
    }

}
