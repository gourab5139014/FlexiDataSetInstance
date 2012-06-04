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
    //private String name;
    private int index;
    private DataType type;
    private boolean process;

    public Column(int index) {
        this.index = index;
        //this.process = (process==1?true:false);
        //System.out.println("Set in Column constructor #"+this.index+" process? "+this.process);
    }

    public void setType(DataType type) {
        this.type = type;
        System.err.println("Created Column for #"+this.index+" "+this.type);
    }

    public DataType getType() {
        return type;
    }

    
    public Object convert( String data ) {
        data = data.trim();
        if( type == DataType.NUMERIC ) {
           return Double.parseDouble( data );
        } else {
           return data;
        }
    }
}
