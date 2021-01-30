package net.mbmedia.odtparser;

public class Main {

    public static final String path_to_odt = "C:\\Users\\Max\\repo\\Bootsfreund\\src\\main\\webapp\\freizeit\\von_Saal_nach_Tulln.odt";

    public Main() {
    }

    public static void main(String[] args) {
        ODTParser parser = new ODTParser(path_to_odt);
        System.out.println(parser.transform());
    }


}
