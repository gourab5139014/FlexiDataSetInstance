/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flexidatasetinstance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 *
 * @author Gourab
 */
public class FlexiInstance {
    private String filename;
    private BitSet toProcess; //decide whether to process a column or not
    private String bitSetFilename; //corresponding .bitset file containing which columns to process

    private List<Column> columns ;
    private List<Row> rows;

    public FlexiInstance(String filename,String extension) {
        this.filename = "."+File.separator+"datasets"+File.separator+filename+extension;
        this.bitSetFilename = "."+File.separator+"datasets"+File.separator+filename+".bitSet";
        columns = new ArrayList<Column>();
        rows = new ArrayList<Row>();

    }
    public void parseFile() throws Exception
    {
        //parse File to assign Column data as CATEGORICAL, NUMERIC
        BufferedReader Reader = new BufferedReader(new FileReader(this.filename));

    }
    public void run()
    {
        try{
            //all calls from here
        }catch(Exception e)
        {
            System.err.println("Inside Run() : "+e.getLocalizedMessage());
        }

    }
}
