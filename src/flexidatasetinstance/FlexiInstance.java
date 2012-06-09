/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flexidatasetinstance;

import java.io.BufferedReader;
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
    private String bitSetFilename; //corresponding .bitset file containing which columns to process

    private List<Column> columns ;
    private List<Row> rows;

    public FlexiInstance(String datasetFilename,String permissionFile) {
        this.filename = datasetFilename; this.bitSetFilename = permissionFile;
        columns = new ArrayList<Column>();
        rows = new ArrayList<Row>();

    }

    public FlexiInstance(String filename) {
        this.filename = filename;
        columns = new ArrayList<Column>();
        rows = new ArrayList<Row>();
//        defaultPermission();
    }

    public int getNumberOfRows()
    {
        return rows.size();
    }
    
    public int getNumberofColumns()
    {
        return columns.size();
    }
//    private void defaultPermission() //for setting def permission of dataset
//    {
//        try{
//            BufferedReader reader = new BufferedReader(new FileReader(this.filename));
//            String datarow = reader.readLine();
//            String dataArray[] = datarow.split(",");
//            for(int i=0;i<dataArray.length-1;i++)
//            {
//                toProcess.add(1);
//            }
//            toProcess.add(dataArray.length-1,0);
//            reader.close();
//        }catch (FileNotFoundException fnfe) {
//            System.err.println("DataSet FileNotFound! Inside defaultPermission()" + fnfe.getLocalizedMessage());
//
//        } catch (IOException ioe) {
//            System.err.println("Inside defaultPermission() " + ioe.getLocalizedMessage());
//        }
//    }
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
            Column temp;
            for(int i=0;i<dataArray.length;i++){ //initialize columns with index and process permission values
                columns.add(new Column(i));
            }
            //now determine type using the one row read in dataArray
            for (int i=0;i<dataArray.length;i++) {
                temp=columns.get(i);
                columns.remove(i);
                try {
                    //try Integer.parseInt()
                    Float.parseFloat(dataArray[i].trim());
                    temp.setType(DataType.NUMERIC);

                } catch (NumberFormatException nfe) {
                    //set as catagorical
                    temp.setType(DataType.CATEGORICAL);
                }
                columns.add(i, temp);
            }
            //now populate all rows using the above obtained information
            Row tupple;
            while(datarow!=null){
                tupple = new Row();
                tupple.parse(datarow, columns);
                rows.add(tupple);
                datarow=reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("DataSet FileNotFound! " + fnfe.getLocalizedMessage());

        } catch (IOException ioe) {
            System.err.println("Inside parseFile() " + ioe.getLocalizedMessage());
        }
    }
    
//    public boolean[] getPermissions()
//    {
//        boolean result[]= new boolean[toProcess.size()];
//        for(int i=0;i<toProcess.size();i++){
//            if(toProcess.get(i)==1) result[i]=true;
//            else result[i]=false;
//        }
//        return result;
//    }
    public String[] getClassColumn()
    {
        String result[]=new String[rows.size()];
        for(int i=0;i<rows.size();i++)
        {
            result[i]=rows.get(i).getClassValue();
        }
        return result;
    }
    public Double[] getByIndex(int i)
    {
        Double[] result = new Double[rows.size()];
        //String temp;
        for(int j=0;j<rows.size();j++)
        {
//            System.err.println("In getByIndex(), trying "+rows.get(j).getAtIndex(i));
//            temp =(String)rows.get(j).getAtIndex(i);
//            result[j]=Double.parseDouble(temp);
            result[j]=(Double)rows.get(j).getAtIndex(i);
//            Object otemp = (String) rows.get(j).getAtIndex(i);
//            if (otemp instanceof Double) {
//                result[j] = (Double) rows.get(j).getAtIndex(i);
//            } else {
//                try {
//                    otemp = String.valueOf(rows.get(j).getAtIndex(i));
//                    result[j] = Double.parseDouble((String) otemp);
//                } catch (NumberFormatException e) {
//                    // logging and recovery code goes here, or rethrow as an exception you can handle.
//                    e.printStackTrace();
//                }
//            }

        }
        return result;
    }
    public String[] getByIndexString(int i)
    {
        String[] result = new String[rows.size()];
        for (int j = 0; j < rows.size(); j++) {
            //System.out.println("Trying to cast - "+rows.get(j).getAtIndex(i));
            if(rows.get(j).getAtIndex(i) instanceof String) result[j]=(String)rows.get(j).getAtIndex(i);
            else
            result[j]=Double.toString((Double)rows.get(j).getAtIndex(i));

        }
        return result;
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
//            readColumnPermission();
            parseFile();
            
        }catch(Exception e)
        {
            System.err.println("Inside Run() : "+e.getLocalizedMessage());
        }

    }
}
