package ru.otus.hw4.statistic.mapper;

import ru.otus.hw4.logger.info.Generation;
import ru.otus.hw4.statistic.result.model.ResultObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogMapper {
    private String logFileName;
    private boolean isOldNamed;
    private boolean isYoungNamed;

    public LogMapper(String logFileName) {
        this.logFileName = logFileName;
    }

    public ResultObject map() {
        ResultObject resultObject = new ResultObject(logFileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(logFileName));
            while (br.ready()) {
                String nextString = br.readLine();
                processLine(nextString, resultObject);
            }
        } catch (FileNotFoundException e) {
            System.out.println(logFileName + "doesn't exist, run all scripts!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    private void processLine(String nextString, ResultObject resultObject) {
        if (!nextString.startsWith("INFO")) {
            return;
        }
        int duration = getDuration(nextString);
        if (nextString.contains(Generation.YOUNG.toString())) {
            resultObject.incrementYoungGcCounter();
            resultObject.increaseYoungGcDuration(duration);
            if (!isYoungNamed) {
                String youngName = getGcName(nextString);
                resultObject.setYoungGcName(youngName);
                isYoungNamed = true;
            }
        } else if (nextString.contains(Generation.OLD.toString())) {
            resultObject.incrementOldGcCounter();
            resultObject.increaseOldGcDuration(duration);
            if (!isOldNamed) {
                String oldName = getGcName(nextString);
                resultObject.setOldGcName(oldName);
                isOldNamed = true;
            }
        }
    }

//    INFO: Generation ='OLD', GC name ='MarkSweepCompact', GC duration='15'
    private String getGcName(String nextString) {
        nextString = nextString.substring(0, nextString.lastIndexOf(','));
        return nextString.substring(nextString.lastIndexOf('=')+2, nextString.length()-1);
    }

    private int getDuration(String nextString) {
        nextString = nextString.substring(0, nextString.length()-1);
        nextString = nextString.substring(nextString.lastIndexOf('\'') + 1);
        return Integer.valueOf(nextString);
    }
}
