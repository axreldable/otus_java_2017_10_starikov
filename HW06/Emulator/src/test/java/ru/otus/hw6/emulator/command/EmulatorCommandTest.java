package ru.otus.hw6.emulator.command;

import org.junit.Test;
import ru.otus.hw6.emulator.exception.UnknownCommandTypeException;

import static org.junit.Assert.*;
import static ru.otus.hw6.emulator.command.EmulatorCommand.*;

public class EmulatorCommandTest {

    @Test
    public void normalParse() throws Exception {
        EmulatorCommand command = parseCommand("put");
        assertEquals(PUT, command);
        command = parseCommand("get");
        assertEquals(GET, command);
        command = parseCommand("balance");
        assertEquals(BALANCE, command);
        command = parseCommand("exit");
        assertEquals(EXIT, command);
    }

    @Test(expected = UnknownCommandTypeException.class)
    public void unknownCommandTest() throws Exception {
        parseCommand("blablabla");
    }
}