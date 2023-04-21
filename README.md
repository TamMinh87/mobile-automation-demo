##  Required tools:
- Java jdk 11.
- Appium (1.20.2) Desktop (GUI).
- IntelliJ - Communication Edition.
- Lombok library: https://www.journaldev.com/18124/java-project-lombok
- Set up/Config: Maven home: https://mkyong.com/maven/how-to-install-maven-in-windows/
- Android Studio.
- Git.

----
## Setup browserstack account
- Register account on https://browserstack.com
- Navigate to `app-automate` domain https://app-automate.browserstack.com/dashboard/v2/quick-start/get-started#introduction
- Find your `browserstack.user` and `browserstack.key`
- Get your `app_url` by uploading your app on Browserstack; For Android, only file `*.apk` and `*.aab` allowed; For iOS, only file `*.ipa` allowed
- Go back to project, find and open file in folder `/src/test/resources`:
  - For Android `common_android_cap.properties`
  - For iOS `common_ios_cap.properties`
- Update Those keys:
  - `browserstack_app_url`=<app_url you uploaded on browserstack>
  - `browserstack.user`=<browserstack.user you found on browserstack>
  - `browserstack.key`=<browserstack.key you found on browserstack>

----
##  How to run on different platform: 
- Run on Android:
    ```text
    mvn clean verify -Denv=SIT -Dproperties=default.properties -Dcucumber.filter.tags="@test-poc"
    ```
- Run on iOS:
    ```text
    mvn clean verify -Denv=SIT -Dproperties=ios.properties -Dcucumber.filter.tags="@regression and not @ignore"
    ```
- Run on BrowserStack:
    ```text
    mvn clean verify -Denv=SIT -Dproperties=browserstack.properties -Dcucumber.filter.tags="@regression and not @ignore"
    ```
- Run API test:
    ```text
    mvn clean verify -Denv=SIT -Dproperties=default.properties -Dcucumber.filter.tags="@test-poc-api"
    ```
----
## How to view test report
- After running tests, the test report will be automatically generated as `spark.html` file in `/test-output`.
- Open the file on your browser to see the detailed test report.
----
## How to change/update test data
- Currently, automation team stores all test data in `/src/test/resources/test-data` including `api_data.json` and `user_data.json`.
- Just go there to change/update your data when needed(Require understanding `feature files`; updating test data may cause failure tests).  
----
## How to run on specific device on Browserstack
- Run on Specific device on BrowserStack. Please check the existed properties files in `/src/test/resources`. Example:
    ```text
    mvn clean verify -Dproperties=bs_ios_iPhone13ProMax.properties -Dcucumber.filter.tags="@regression and not @ignore"
    ```

