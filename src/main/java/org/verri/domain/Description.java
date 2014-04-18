package org.verri.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.function.Function;

@Entity
public class Description implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public Description() {
    }

    public Description(String title) {
        this.title = title;
    }

    public String transformTitle(Function<String, String> transformation) {
        return transformation.apply(title);
    }

    public String getTitleUppercase() {
        //====================================================
        // PS: Eclipselink will not recognize this entity due to the Lambda expression.
        //====================================================
        return transformTitle(s -> s.toUpperCase());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
