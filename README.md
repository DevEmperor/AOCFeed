![image-20221210095413323](https://raw.githubusercontent.com/DevEmperor/AOCFeed/master/image.png)


# AOCFeed

AOCFeed is a simple WearOS app for devices with API level >= 26. When opened, it shows you your position and a percentage of how high up you are in the leaderboard to a previously set leaderboard in [Advent of Code](https://adventofcode.com).

## Setting up

1. Pull the repository into Android Studio
2. edit "AOCFeed/app/src/main/java/net/devemperor/aocfeed/MainActivity.java":
   1. fill in the URL of your leaderbords json file (line 21)
   2. fill in a fresh session cookie from you logged in account which has access to this leaderboard (line 22)
   3. fill in the user-id of you user, which you can find in the json file of the leaderboard (line 23)
3. connect your watch to Android Studio and run the app
