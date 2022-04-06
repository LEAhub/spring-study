import org.junit.jupiter.api.Test;
import springstudy.spring.Main;
import springstudy.spring.exception.CustomException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ThorwsTest {

    @Test
    public void test() throws CustomException {
        CustomException e = assertThrows(CustomException.class,
                () -> Main.test());//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("나이는 음수일 수 없습니다.");




    }
}
