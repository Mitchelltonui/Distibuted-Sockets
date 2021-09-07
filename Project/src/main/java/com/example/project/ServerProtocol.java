package com.example.project;

import java.util.Objects;
import java.util.Random;

public class ServerProtocol {

    public static String Reply;
    public static boolean correctOrWrong;

    private static String[] riddle = {"First Hint: The man who invented it, doesn't want it. ",
            "Second Hint: The man who bought it, doesn't need it.",
            "Third Hint:The man who needs it, doesn't know it."};
    private static String answer = "Coffin";

    public static String processInput() {

        if (Objects.equals(SocketServer.Input, answer)) {
            Reply = "Congratulations!,)";
            correctOrWrong = true;
        } else {
            Random r = new Random();
            int randomStringPosition = r.nextInt(riddle.length);
            Reply = riddle[randomStringPosition];
            correctOrWrong = false;
        }
        return Reply;

    }
}
