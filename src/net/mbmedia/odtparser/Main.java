package net.mbmedia.odtparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public Main() {
    }

    public static void main(String[] args) throws IOException {
        if(!checkInput(args)){
            return;
        }

        ODTParser parser = new ODTParser(args[0]);
        Files.write(Paths.get(args[1]), parser.transform().getBytes());
    }

    private static boolean checkInput(String[] args){
        if(args.length != 2){
            System.out.println("Usage: java -jar ODTParser.jar <path_to_input_file.odt> <output_file.html>");
            return false;
        }

        return true;
    }


}
