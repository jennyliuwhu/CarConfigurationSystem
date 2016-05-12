/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package exception;

import adapter.FixAuto;

public class AutoException extends Exception implements FixAuto{

	private static final long serialVersionUID = 1L;
	private Object result;
	public AutoException(int errno) {
		fix(errno);
	}

	public void fix(int errno) {
		FixHelper fh = new FixHelper();
		switch (errno) {

		// fix missing price exception, errno = 1
		case 1: result = fh.fix1(errno);
		break;

		// fix missing OptionSet name exception, errno = 2
		case 2: result = fh.fix2(errno);
		break;

		// fix missing Option name exception, errno = 3
		case 3: result = fh.fix3(errno);
		break;

		// fix missing filename or wrong filename exception, errno = 4
		case 4: result = fh.fix4(errno);
		break;

		// fix wrong type for option price exception, errno = 5
		case 5: result = fh.fix5(errno);
		}
	}

	public Object getFixedResult() {
		return result;
	}
}
