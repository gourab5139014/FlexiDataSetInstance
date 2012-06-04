/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flexidatasetinstance;

import java.util.List;

/**
 *
 * @author Gourab
 */
public class Row {
private Object[] data;

    public String getClassValue()
    {
        return (String)data[data.length-1];
    }
    public Object getAtIndex(int i)
    {
        return data[i];
    }
    public void parse( String row, List<Column> columns ) {
//         System.err.println("Row parsing> "+row);
         String[] cols = row.split(",");
         data = new Object[cols.length];

         int i = 0;
         for( Column col : columns ) {
             data[i] = col.convert(cols[i]);
             i++;
         }
     }
}
