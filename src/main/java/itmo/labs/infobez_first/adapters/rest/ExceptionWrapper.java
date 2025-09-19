package itmo.labs.infobez_first.adapters.rest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Slf4j
public class ExceptionWrapper {

    private String message;
    private String time;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ExceptionWrapper(Exception e) {
        log.debug("Exception wrapper got error: ", e);
        message = e.getMessage();
        this.time = LocalDateTime.now().format(FORMATTER);
    }

    @Override
    public String toString() {
        return "{\"message\": \"" + message + "\", \"time\": \"" + time + "\"}";
    }

}
