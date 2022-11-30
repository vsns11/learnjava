package ca.siva.datetime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DateTimeConversions {
    public static void main(String[] args) throws JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        OffsetDateTime odt = OffsetDateTime.parse("2022-10-03T18:38:05.507", formatter);
//        OffsetDateTime now = OffsetDateTime.now(ZoneId.of("UTC"));
//        long val = ChronoUnit.DAYS.between(odt, now);
        ObjectMapper mapper = new ObjectMapper();
        List<String> x = new ArrayList<>();
        x.add("1");
        x.add("2");
        mapper.writeValueAsString(x);


        OffsetDateTime now2 = OffsetDateTime.now(ZoneId.of("UTC"));
        String odtAfter15Min =  now2.plusMinutes(14).plusSeconds(55).format(formatter);
        System.out.println(now2.format(formatter));
        System.out.println(odtAfter15Min);
    }
}