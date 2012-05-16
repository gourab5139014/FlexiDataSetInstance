/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flexidatasetinstance;

/**
 *
 * @author Gourab
 */
public class Column {
private String name;
     private int index;
     private DataType type;

     public Object convert( String data ) {
         if( type == DataType.NUMERIC ) {
            return Double.parseDouble( data );
         } else {
            return data;
         }
     }
}
