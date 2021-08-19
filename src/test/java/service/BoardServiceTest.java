package service;

import config.RootConfig;
import domain.BoardVO;
import domain.Criteria;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardServiceTest {

    @Setter(onMethod_ = {@Autowired})
    private BoardService service;

    @Test
    public void testExist() {
        log.info(service);
        assertNotNull(service);
    }

    @Test
    public void testRegister() {
        BoardVO board = new BoardVO();
        board.setTitle("new Title!");
        board.setContent("new Content");
        board.setWriter("newbie");
        service.register(board);
        log.info("생성된 게시물 번호: "+board.getBno());
    }


    @Test
    public void testGet() {
        log.info(service.get(13L));
    }

    @Test
    public void testDelete() {
        log.info(service.remove(2L));
    }

    @Test
    public void testUpdate() {
        BoardVO board = service.get(1L);
        if(board==null){
            return ;
        }
        board.setTitle("제목 수정합니다.");
        log.info("MODIFY RESULT: "+service.modify(board));
    }

    @Test
    public void testGetList() {
        service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
    }

}