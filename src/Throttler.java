public class Throttler {
  private long lastExecutionTime = 0;

  public void throttle(Runnable task, int interval) {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastExecutionTime >= interval) {
      lastExecutionTime = currentTime;
      task.run();
    }
  }
}
