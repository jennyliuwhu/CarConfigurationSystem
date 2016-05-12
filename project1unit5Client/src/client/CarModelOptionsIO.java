/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package client;
/**
 * This CarModelOptionIO class does the following functions:
 * a. Read data from the Properties file; create properties object, using the load method,
 *    which transfers the object from the client to server, using ObjectStream
 * b. Receive a response from the Server, verifying that the Car Model object 
 *    is created successfully
 * c. Use CreateAuto interface to build Automobile and handle different type of files, 
 *    passed in filetype
 * @author jialingliu
 *
 */
public class CarModelOptionsIO {
	public void uploadProp() {
		DefaultSocketClient defaultSocketClient = new DefaultSocketClient(1);
		Thread t = new Thread(defaultSocketClient);
		t.start();
	}
}
