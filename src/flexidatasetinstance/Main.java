/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flexidatasetinstance;

import java.io.File;

/**
 *
 * @author Gourab
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FlexiInstance f = new FlexiInstance("."+File.separator+"datasets"+File.separator+"Iris.data","."+File.separator+"datasets"+File.separator+"Iris.bitSet");
        f.run();
    }

}
