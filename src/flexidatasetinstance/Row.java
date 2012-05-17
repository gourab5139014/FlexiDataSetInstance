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

     public void parse( String row, List<Column> columns ) {
         String[] cols = row.split(",");
         data = new Object[cols.length];

         int i = 0;
         for( Column col : columns ) {
             data[i] = col.convert(cols[i]);
             i++;
         }
     }
}
