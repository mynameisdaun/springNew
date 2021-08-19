package security;

import domain.MemberVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.warn("Load By UserName: "+userName);
        MemberVO vo = memberMapper.read(userName);
        log.warn("quried by member mapper: "+vo);
        return vo == null? null : new CustomUser(vo);
    }
}
