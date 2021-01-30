# ODTParser
Parse a ODT File to a HTML Page;

This tool parses a odt file to a html page.
But just text and images.

Output could be:
```
<p>Hello World</p>
<img src="data:image;base64,/9j/4AAQSk .... />
```


Usage:
```
public static final String path_to_odt = "C:\\Users\\Max\\repo\\Bootsfreund\\src\\main\\webapp\\freizeit\\von_Saal_nach_Tulln.odt";

public static void main(String[] args) {
    ODTParser parser = new ODTParser(path_to_odt);
    System.out.println(parser.transform());
}
