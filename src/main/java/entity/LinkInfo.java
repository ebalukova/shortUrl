/**
 *
 */
package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

/**
 * LinkInfo class
 * 
 * Объект хранящий информацию о ссылках
 * 
 * 
 * @author Balukova Elena
 * @created 26 окт. 2014 г.
 * 
 */
@Entity
@Table(name = "link_info")
@Index(columnNames = { "url_hash" })
public class LinkInfo implements Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 177204291008026845L;

    /**
     * Database-provided идентификатор.
     */
    private Long id;

    /**
     * Исходное значение ссылки
     */
    private String url;

    /**
     * Хэш от url
     */
    private String urlHash;

    // ////////////////////////////////////////////////////////////////////////
    // //// getters/setters
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @return Идентификатор - первичный ключ в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {

        return id;
    }

    /**
     * @return the url
     */
    @Column(name = "url")
    public String getUrl() {

        return url;
    }

    /**
     * @return the urlHash
     */
    @Column(name = "url_hash")
    public String getUrlHash() {

        return urlHash;
    }

    /**
     * @param id
     *            Идентификатор - первичный ключ в БД
     */
    protected void setId(Long id) {

        this.id = id;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {

        this.url = url;
    }

    /**
     * @param urlHash
     *            the urlHash to set
     */
    public void setUrlHash(String urlHash) {

        this.urlHash = urlHash;
    }

}
