package java6601.lesson13;

/**
 * 运行时间工具类
 * 
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
		System.out.println("UseTime: " + (end - start)/1000 + "s");
	}
}