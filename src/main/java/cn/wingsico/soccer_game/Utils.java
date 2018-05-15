package cn.wingsico.soccer_game;

import java.util.Random;

public class Utils {
  private static Random random = new Random();

  public static String getRandomName(String sex) {
    return getRandomLastName() + " " +getRandomFirstName(sex);
  }

  public static String getRandomLastName() {
    return StaticData.LAST_NAME[random.nextInt(StaticData.LAST_NAME_LENGTH)];
  }

  public static String getRandomFirstName(String sex) {
    String firstName;
    if (sex.equals("f")) {
      firstName = StaticData.FEMALE_FIRST_NAME[random.nextInt(StaticData.FEMALE_FIRST_NAME_LENGTH)];
    } else {
      firstName = StaticData.MALE_FIRST_NAME[random.nextInt(StaticData.MALE_FIRST_NAME_LENGTH)];
    }

    return firstName;
  }
}
