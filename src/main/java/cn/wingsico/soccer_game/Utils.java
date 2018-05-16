package cn.wingsico.soccer_game;

import cn.wingsico.soccer_game.dao.ResMessage;

import java.util.Arrays;
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

  public static Character getRandomCharacter() {
    return (char)(97 + random.nextInt(26));
  }

  public static String getRandomTeamName() {
    String teamName = "";
    teamName += getRandomCharacter();
    teamName += getRandomCharacter();
    teamName += getRandomCharacter();
    return teamName.toUpperCase();
  }

  public static String getRandomSex() {
    Integer sexNumber = random.nextInt(2);
    return sexNumber == 1 ? "male" : "female";
  }

  public static ResMessage potResMessage(Integer status, String message) {
    ResMessage res = new ResMessage();
    res.setStatus(status);
    res.setMessage(message);
    return res;
  }

  public static Integer getRandomShotCount () {
    Random random = new Random();
    Integer points = random.nextInt(100);
    Integer count = 0;

    if (points < 15) {
      count++;
      if (points > 11) {
        count++;
      }
    }

    return count;
  }

  public static Integer getRandomDuration () {
    Random random = new Random();
    return 93 + random.nextInt(6);
  }
}
