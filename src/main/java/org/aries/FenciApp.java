package org.aries;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.*;

/**
 *
 */
public class FenciApp {
    public static void main( String[] args ) {

        Analyzer analyzer = new PaodingAnalyzer();
        String docText = null;
        File file = new File("test-file/test.txt");
        docText = readText(file);

        TokenStream tokenStream = analyzer.tokenStream(docText, new StringReader(docText));
        TermAttribute termAtt = (TermAttribute)tokenStream.getAttribute(TermAttribute.class);
        TypeAttribute typeAtt = (TypeAttribute)tokenStream.getAttribute(TypeAttribute.class);

        try {
            System.out.println(docText);
            while (tokenStream.incrementToken()){
                System.out.print(termAtt.term());
                System.out.print(' ');
                System.out.println(typeAtt.type());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readText(File file) {
        String text = null;
        try
        {
            InputStreamReader read1 = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br1 = new BufferedReader(read1);
            StringBuffer buff1 = new StringBuffer();
            while((text = br1.readLine()) != null)
            {
                buff1.append(text + "/r/n");
            }
            br1.close();
            text = buff1.toString();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return text;
    }
}
