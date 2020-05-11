package com.architecture.android.takenotes.data;

import com.architecture.android.takenotes.data.entity.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataGenerator {

    public static List<Note> getNotes(){
        List<Note> notes = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat ("dd/MM/yyy");
        String strDate = ft.format(date);
        Date dt = null;
        try {
            dt = new SimpleDateFormat ("dd/MM/yyy").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        notes.add(new Note("Android programming", "This is android tutorial", dt));
        notes.add(new Note("Go programming", "This is golang tutorial", dt));
        notes.add(new Note("Kotlin programming", "This is kotlin tutorial", dt));
        notes.add(new Note("Java programming", "This is java tutorial", dt));
        notes.add(new Note("Python programming", "This is python tutorial", dt));
        return notes;
    }

}
