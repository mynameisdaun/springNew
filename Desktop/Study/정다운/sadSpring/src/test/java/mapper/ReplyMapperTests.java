package mapper;


import domain.Criteria;
import domain.ReplyVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={config.RootConfig.class})
@Log4j
public class ReplyMapperTests {
    private Long[] bnoArr = {62L,61L,55L,54L,53L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Test
    public void testList() {
        Criteria cri = new Criteria();
        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
        replies.forEach(reply -> log.info(reply));
    }

    @Test
    public void testUpdate() {
        Long targetRno = 7L;
        ReplyVO vo = mapper.read(targetRno);

        vo.setReply("update Reply1/!");

        int count = mapper.update(vo);
        log.info(count);
    }

    @Test
    public void testDelete() {
        Long targetRno = 5L;
        int vo = mapper.delete(targetRno);
        log.info(vo);
    }

    @Test
    public void testRead() {
        Long targetRno = 5L;
        ReplyVO vo = mapper.read(targetRno);
        log.info(vo);
    }

    @Test
    public void testcreate() {
        IntStream.rangeClosed(1,10).forEach(i -> {
            ReplyVO vo = new ReplyVO();
            vo.setBno(bnoArr[i%5]);
            vo.setReply("댓글테스트"+i);
            vo.setReplyer("replyer"+i);
            mapper.insert(vo);
        });
    }

    @Test
    public void testList2() {
        Criteria cri = new Criteria(1,10);
        List<ReplyVO> replies = mapper.getListWithPaging(cri,52L);
        replies.forEach(reply -> log.info(reply));
    }

    @Test
    public void testMapper() {
        log.info(mapper);
    }
    }


