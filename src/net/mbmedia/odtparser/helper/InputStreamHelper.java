package net.mbmedia.odtparser.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class InputStreamHelper {
    private String Filepath;
    private ArrayList<InputStream> streams = new ArrayList();
    private HashMap<String, Integer> positionen = new HashMap();

    public InputStreamHelper(String odt) {
        this.Filepath = odt;
    }

    public void schlieseStreams() {
        Iterator var1 = this.streams.iterator();

        while(var1.hasNext()) {
            InputStream s = (InputStream)var1.next();

            try {
                s.close();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

    }

    public InputStream getInputStreamByName(String name) {
        int i = (Integer)this.positionen.get(name);
        return (InputStream)this.streams.get(i);
    }

    public ArrayList<InputStream> readODT() {
        try {
            return this.read();
        } catch (IOException var2) {
            var2.printStackTrace();
            System.out.println("Datei nicht gefunden oder fehlerhaft");
            return null;
        }
    }

    private ArrayList<InputStream> read() throws IOException {
        ZipFile zipFile = new ZipFile(this.Filepath);
        Enumeration entries = zipFile.entries();

        while(entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            InputStream stream;
            if (entry.getName().equals("content.xml")) {
                stream = zipFile.getInputStream(entry);
                this.positionen.put("content.xml", this.streams.size());
                this.streams.add(stream);
            }

            if (entry.getName().contains("Pictures")) {
                stream = zipFile.getInputStream(entry);
                this.positionen.put(entry.getName().replace("Pictures/", ""), this.streams.size());
                this.streams.add(stream);
            }
        }

        return this.streams;
    }
}
