/**
 * 
 */
package algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import dao.LinkInfoDao;
import entity.LinkInfo;

/**
 * BasedOnIdAlgorithm class
 * 
 * Алгоритм кодирования коротких ссылок на основе идентификатора в базе данных
 * 
 * 
 * @author Balukova Elena
 * @created 26 окт. 2014 г.
 * 
 */

public final class BasedOnIdAlgorithm {

    /**
     * Соотвествие каждого символа алфавита числу
     */
    private static final Map<Character, Integer> ALPHABET_MAP;

    /**
     * Алфавит
     */
    private static final String ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static {

        ALPHABET_MAP = new HashMap<Character, Integer>();
        int len = ALPHABET.length();
        for (int i = 0; i < len; i++) {
            ALPHABET_MAP.put(ALPHABET.charAt(i), i);
        }
    }

    /**
     * Преобразование длинной ссылки в короткую с сохранением полученного
     * результата в бд
     * 
     * @param url
     *            длинная ссылка
     * @return короткая ссылка
     */
    public static String encode(String url) {

        LinkInfoDao dao = new LinkInfoDao();

        String hash = DigestUtils.md2Hex(url);
        List<LinkInfo> links = dao.findByHash(hash);

        LinkInfo link = null;
        for (LinkInfo item : links) {
            if (url.equals(item.getUrl())) {
                link = item;
                break;
            }
        }

        if (null == link) {
            link = new LinkInfo();
            link.setUrl(url);
            link.setUrlHash(hash);
            link = dao.save(link);
        }

        return idToLink(link.getId());
    }

    /**
     * Получение длинной ссылки из короткой
     * 
     * @param shortLink
     *            короткая ссылка
     * @return длинная ссылка
     */
    public static String decode(String shortLink) {

        LinkInfo link = null;
        if (StringUtils.isAsciiPrintable(shortLink)) {
            LinkInfoDao dao = new LinkInfoDao();
            link = dao.get(linkToId(shortLink));
        }

        String result = null;
        if (null != link) {
            result = link.getUrl();
        }

        return result;
    }

    /**
     * Преобразование идентификатора в строку на основе алфавита
     * 
     * @param id
     *            идентификатор
     * @return строка
     */
    private static String idToLink(Long id) {

        StringBuilder builder = new StringBuilder();
        int len = ALPHABET.length();

        do {

            Long rem = id % len;
            builder.append(ALPHABET.charAt(rem.intValue()));
            id = id / len;

        } while (id != 0);

        return builder.toString();
    }

    /**
     * Преобразование строки в идентификатор на основе алфавита
     * 
     * @param link
     *            строка
     * @return идентификатор
     */
    private static Long linkToId(String link) {

        Long id = 0L;
        int len = link.length();
        int alphLen = ALPHABET.length();
        for (int i = len - 1; i >= 0; i--) {

            id = ALPHABET_MAP.get(link.charAt(i)) + id * alphLen;
        }

        return id;
    }
}
