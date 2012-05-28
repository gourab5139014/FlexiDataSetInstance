/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flexidatasetinstance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gourab
 */
public class FlexiInstance {
    private String filename;
    private List<Integer> toProcess; //decide whether to process a column or not
    private String bitSetFilename; //corresponding .bitset file containing which columns to process

    private List<Column> columns ;
    private List<Row> rows;

    public FlexiInstance(String filename,String extension) {
        this.filename = "."+File.separator+"datasets"+File.separator+filename+extension;
        this.bitSetFilename = "."+File.separator+"datasets"+File.separator+filename+".bitSet";
        columns = new ArrayList<Column>();
        rows = new ArrayList<Row>();
        toProcess = new ArrayList<Integer>();

    }
    private void parseFile()
    {
        try {
            //parse File to assign Column data as CATEGORICAL, NUMERIC
            BufferedReader reader = new BufferedReader(new FileReader(this.filename));
            String datarow = reader.readLine();
            if (datarow == null) {
                reader.close();
                System.err.println("DataSet File Empty! ");
                return;
            }
            String dataArray[] = datarow.split(",");
            for(int i=0;i<dataArray.length;i++){ //initialize columns with index and process permission values
                columns.add(new Column(i,(toProcess.get(i)==1?true:false)));
            }
            //now determine type using the one row read in dataArray
            Column temp;
            for (int i=0;i<dataArray.length;i++) {
                temp=columns.get(i);
                try {
                    //try Integer.parseInt()
                    Integer.parseInt(dataArray[i]);
                    temp.setType(DataType.NUMERIC);

                } catch (NumberFormatException nfe) {
                    //set as catagorical
                    temp.setType(DataType.CATEGORICAL);
                }
                
            }
            //now populate all rows using the above obtained information
            reader.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("DataSet FileNotFound! " + fnfe.getLocalizedMessage());

        } catch (IOException ioe) {
            System.err.println("Inside parseFile() " + ioe.getLocalizedMessage());
        }
    }
    private void readColumnPermission()
    {
        try{
        BufferedReader reader = new BufferedReader(new FileReader(this.bitSetFilename));
        String bitrow = reader.readLine();
        System.err.println("Read permission : "+bitrow+" from "+bitSetFilename);
        for(int i=0;i<bitrow.length();i++)
        {
            toProcess.add(i, Integer.parseInt(bitrow.substring(i, i+1)));
        }
        reader.close();
        }catch(FileNotFoundException fnfe) {
            System.err.println("Permissions preferences file <dataSetName>.bitSet not Found! Reverting to default permission preferences.");
            /*
             * Code to write all 1s in toProcess permitting processing of all attributes except the last one (class column)
             */

        }
        catch(IOException ioe) { System.err.println("Inside readColumnPermission() " + ioe.getLocalizedMessage());}
    }
    public void run()
    {
        try{
            //all calls from here
            /*
             * Order of calls
             * 1. parse file to read and configure columns
             * 2. readColumnPermission on created columns
             */
            readColumnPermission();
        }catch(Exception e)
        {
            System.err.println("Inside Run() : "+e.getLocalizedMessage());
        }

    }
}
