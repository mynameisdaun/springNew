package mapper;

import domain.MemberVO;
import org.apache.ibatis.annotations.Insert;

public interface MemberMapper {
    public MemberVO read(String userid);
}
