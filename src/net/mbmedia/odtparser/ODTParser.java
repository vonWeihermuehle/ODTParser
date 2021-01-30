package net.mbmedia.odtparser;

import net.mbmedia.odtparser.helper.InputStreamHelper;
import net.mbmedia.odtparser.helper.XMLHelper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ODTParser {
    private String path_to_odt;
    private InputStreamHelper inputStreamHelper;

    public ODTParser() {
    }

    public ODTParser(String path_to_odt) {
        this.path_to_odt = path_to_odt;
    }

    public void setODT(String path_to_odt) {
        this.path_to_odt = path_to_odt;
    }

    public String transform() {
        this.inputStreamHelper = new InputStreamHelper(this.path_to_odt);
        this.inputStreamHelper.readODT();
        XMLHelper xmlHelper = new XMLHelper();
        String text_mit_platzhalter = xmlHelper.getTextsWithImagePlaceholder(this.inputStreamHelper.getInputStreamByName("content.xml"));
        String gefuellt = "";

        try {
            gefuellt = this.fuellePlatzhalterMitBilder(text_mit_platzhalter);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        this.inputStreamHelper.schlieseStreams();
        return gefuellt;
    }

    private String fuellePlatzhalterMitBilder(String text_mit_platzhalter) throws IOException {
        String[] zeilen = text_mit_platzhalter.split("\n");

        for(int i = 0; i < zeilen.length; ++i) {
            if (zeilen[i].toLowerCase().contains("pictures")) {
                InputStream stream = this.inputStreamHelper.getInputStreamByName(zeilen[i].replace("Pictures/", ""));
                byte[] bytes = IOUtils.toByteArray(stream);
                String encodedBase64 = new String(Base64.getEncoder().encode(bytes));
                zeilen[i] = "<img src=\"data:image;base64," + encodedBase64 + "\" />";
            }
        }

        return String.join("\n", zeilen);
    }
}

