/**
 * 
 */
package java6601.lesson11;

/**
 * @author CAI
 *
 */
class UseTimeTool {
	private static UseTimeTool utt = new UseTimeTool();

	private UseTimeTool() {
	}

	public static UseTimeTool getInstance() {
		return utt;
	}

	private long start;

	public void start() {
		start = System.currentTimeMillis();
	}

	public void stop() {
		long end = System.currentTimeMillis();
		System.out.println("UserTime: " + (end - start)/60 + "s");
	}
}
