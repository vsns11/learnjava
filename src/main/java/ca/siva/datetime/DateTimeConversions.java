package ca.siva.datetime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateTimeConversions {
    public static void main(String[] args) throws JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        OffsetDateTime odt = OffsetDateTime.parse("2022-10-03T18:38:05.507+0000", formatter);
        OffsetDateTime now = OffsetDateTime.now(ZoneId.of("UTC"));
        long val = ChronoUnit.DAYS.between(odt, now);
        System.out.println(val);
        //========================
        ObjectMapper mapper = new ObjectMapper();
        List<String> x = new ArrayList<>();
        x.add("1");
        x.add("2");
        mapper.writeValueAsString(x);
        System.out.println(x);
    }
}