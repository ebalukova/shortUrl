/**
 * 
 */
package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * BasedOnIdAlgorithmTest class
 * 
 * Класс для тестирования преобразования идентификатора в короткую ссылку и
 * наоборот
 * 
 * 
 * @author Balukova Elena
 * @created 26 окт. 2014 г.
 * 
 */

public class BasedOnIdAlgorithmTest {

    /**
     * Тест преобразования идентификатора в короткую ссылку
     */
    @Test
    public void idToLinkTest() {

        Long id = 2906248L;

        try {
            Method method =
                    BasedOnIdAlgorithm.class.getDeclaredMethod("idToLink",
                            Long.class);
            method.setAccessible(true);
            String result =
                    (String) method.invoke(new BasedOnIdAlgorithm(), id);

            assertEquals("Y2cc", result);
        } catch (Exception e) {

            fail("Wrong method name!");
        }
    }

    /**
     * Тест преобразования короткой ссылки в идентификатор
     */
    @Test
    public void linkToIdTest() {

        String link = "Y2cc";

        try {
            Method method =
                    BasedOnIdAlgorithm.class.getDeclaredMethod("linkToId",
                            String.class);
            method.setAccessible(true);
            Long result = (Long) method.invoke(new BasedOnIdAlgorithm(), link);

            assertEquals(new Long(2906248), result);
        } catch (Exception e) {

            fail("Wrong method name!");
        }
    }

}
