package ru.otus.hw4;

import ru.otus.hw4.leak.MemoryLeaker;
import ru.otus.hw4.logger.GcLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * First start Main 4 times.
 * Then run Statist.
 * See start.sh
 */
public class Main {
    public static void main(String[] args) throws IOException{
        new GcLogger().start();
        try {
            new MemoryLeaker().leak();
        } catch (OutOfMemoryError e) {
            // don't write in file gc_0*.log without it
            BufferedReader fileReader = new BufferedReader(new FileReader("gc.log"));
            while (fileReader.ready()) {
                System.out.println(fileReader.readLine());
            }
        }
    }
}
