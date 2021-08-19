package mapper;

import domain.MemberVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={config.SecurityConfig.class,config.RootConfig.class,config.ServletConfig.class})
@Log4j
public class MemberMapperTests {
    @Setter(onMethod_= @Autowired)
    private MemberMapper mapper;

    @Test
    public void testRead() {
        MemberVO vo = mapper.read("admin90");
        log.info(vo);
        vo.getAuthList().forEach(authVO -> log.info(authVO));

    }
}
