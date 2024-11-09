package Activity14Interface_Esquivel;

public interface Teachable {
    public void listen();
    public void teach();
    public void learn();

    public static void read() {
        System.out.println("I am reading!");
    }
}




/*	 	_Main_
 *		|	 |
 *   Teachable
 *       |
 *   Readable
 *       |
 *   Teacher - teach
 *       |
 *   Student - listen - learn - know - look
 *   */
 