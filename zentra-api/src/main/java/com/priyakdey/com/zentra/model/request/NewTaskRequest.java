package com.priyakdey.com.zentra.model.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Priyak Dey
 */
public class NewTaskRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -2647937431401085576L;

    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime tentativeCompletionDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getTentativeCompletionDate() {
        return tentativeCompletionDate;
    }

    public void setTentativeCompletionDate(ZonedDateTime tentativeCompletionDate) {
        this.tentativeCompletionDate = tentativeCompletionDate;
    }
}
