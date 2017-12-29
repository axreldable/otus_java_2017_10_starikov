package ru.otus.hw6.emulator.parser;

import ru.otus.hw6.atm.cash.CashType;
import ru.otus.hw6.atm.command.CommandType;
import ru.otus.hw6.atm.exception.UnknownCashValueException;
import ru.otus.hw6.atm.request.Request;
import ru.otus.hw6.emulator.command.EmulatorCommand;
import ru.otus.hw6.emulator.exception.ExitException;
import ru.otus.hw6.emulator.exception.UnknownCommandTypeException;
import ru.otus.hw6.emulator.exception.WrongArgumentsException;

import java.util.Arrays;
import java.util.HashMap;

import static ru.otus.hw6.atm.cash.CashType.getCashValues;
import static ru.otus.hw6.emulator.constants.Constants.GOOD_BYE;
import static ru.otus.hw6.emulator.constants.Constants.SPACE;

public class CommandParser {
    public static Request parse(String command) throws Exception {
        String[]  split = command.split(SPACE);
        if (split.length == 0) {
            throw new UnknownCommandTypeException("Need not empty command!");
        }
        String[] splitWithoutEmptyString = removeEmptyString(split);
        String userCommand = splitWithoutEmptyString[0];
        EmulatorCommand emulatorCommand = EmulatorCommand.parseCommand(userCommand);
        switch (emulatorCommand) {
            case PUT:
                if (splitWithoutEmptyString.length != 3) {
                    int parametersNumber = splitWithoutEmptyString.length - 1;
                    throw new WrongArgumentsException("Wrong number of parameters in command PUT: " + parametersNumber + ", need 2!");
                }
                CashType type;
                try {
                    // ATM really can get different cash types at once, but this emulator can put only one type of cash at once
                    type = CashType.parseCash(Integer.valueOf(splitWithoutEmptyString[1]));
                } catch (NumberFormatException | UnknownCashValueException e) {
                    throw new WrongArgumentsException(
                            "Wrong cash type parameter in command PUT! " + e.getMessage() + ". Use: " + getCashValues());
                }
                int cashAmount;
                try {
                    cashAmount = Integer.valueOf(splitWithoutEmptyString[2]);
                } catch (NumberFormatException e) {
                    throw new WrongArgumentsException(
                            "Wrong cash amount parameter in command PUT: " + splitWithoutEmptyString[2] + "!" + " Need int value!");
                }
                return Request.builder()
                        .command(CommandType.PUT)
                        .cash(new HashMap<CashType, Integer>() {
                            {
                                put(type, cashAmount);
                            }
                        })
                        .build();
            case GET:
                if (splitWithoutEmptyString.length != 2) {
                    int parametersNumber = splitWithoutEmptyString.length - 1;
                    throw new WrongArgumentsException("Wrong number of parameters in command GET: " + parametersNumber + ", need 1!");
                }
                try {
                    cashAmount = Integer.valueOf(splitWithoutEmptyString[1]);
                } catch (NumberFormatException e) {
                    throw new WrongArgumentsException("Wrong cash amount parameter in command GET: " + splitWithoutEmptyString[1] + " !");
                }
                return Request.builder()
                        .command(CommandType.GET)
                        .cashAmount(cashAmount)
                        .build();
            case BALANCE:
                if (splitWithoutEmptyString.length != 1) {
                    int parametersNumber = splitWithoutEmptyString.length - 1;
                    throw new WrongArgumentsException("Wrong number of parameters in command BALANCE: " + parametersNumber + ", need 0!");
                }
                return Request.builder()
                        .command(CommandType.BALANCE)
                        .build();
            case EXIT:
                throw new ExitException(GOOD_BYE);
        }
        throw new UnknownCommandTypeException("unknown command: " + command);
    }

    private static String[] removeEmptyString(String[] stringArray) {
        return Arrays.stream(stringArray)
                .filter(value -> !"".equals(value))
                .toArray(String[]::new);
    }
}
