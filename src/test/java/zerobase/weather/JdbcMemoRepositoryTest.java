package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JdbcMemoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest(){

        // given
        Memo newMemo = new Memo(2, "this is memo~");

        // when
        jdbcMemoRepository.save(newMemo);

        //then
        Optional<Memo> result =  jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "this is memo~");
    }

    @Test
    void findAllMemoTest() {
        //given
        List<Memo> memoList =  jdbcMemoRepository.findAll();

        //when
        //then
        assertNotNull(memoList);
    }
}
