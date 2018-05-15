package cn.wingsico.soccer_game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  @Autowired
  private static FakeMockGenerator fakeMockGenerator;

  public static void main(String[] args) {
    init();
    SpringApplication.run(App.class, args);
  }

  public static class MockThread extends Thread {
    @Override
    public void run() {
      fakeMockGenerator.run();
    }
  }

  public static void init() {
    MockThread mockThread = new MockThread();
    mockThread.run();
  }
}
