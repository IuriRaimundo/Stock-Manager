package com.stockmanager.model.storage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author rsmal
 */
public class Date {

    Calendar calendar;

    public Date(int day, int month, int year) {
        calendar = new GregorianCalendar(year, month-1, day);
    }

    public static Date parse(String data) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        simpleDateFormat.parse(data);

        String[] dataComponentes = data.trim().split("/");
        int day = Integer.valueOf(dataComponentes[0]);
        int month = Integer.valueOf(dataComponentes[1]);
        int year = Integer.valueOf(dataComponentes[2]);

        return new Date(day, month, year);
    }

    @Override
    public String toString() {
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                (calendar.get(Calendar.MONTH)+1) + "/" +
                calendar.get(Calendar.YEAR);
    }

}