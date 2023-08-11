# AND1558-CLIENT
Welcome to the AND1558 PVP Client Public Repository!
- You may use this client as a base but YOU MUST credit me for it
- If you use this client or any of my code YOU MUST make your SOURCE CODE PUBLIC, no exceptions
- For any issues you may have found, you are allowed to push/send fixes or create an issue for it
## Building/Compiling
Tutorial on how to compile/build this Gradle Project<br>
Before you start, please make sure that you have your minecraft assets stored in the default .minecraft folder<br>
or else the script would fail to download as the minecraft assets website no longer allow downloading using http<br>
#### Setting up
1. Open using your IDE (I used IntelliJ IDE for this)
2. Wait until everything finish loading
3. Click on gradle -> project name (should be "and1558") -> tasks -> forgegradle
4. Click on setupDecompWorkspace<br>
Note: This might take a while.
5. Click on genIntellijRuns
6. Next edit your run configurations
7. Expand on the Application menu
8. Click "Minecraft Client"
9. on the line "-cp" change it to "and1558.main"
10. You should be done now
#### Compiling
1. Goto Gradle Task
2. Click on Tasks -> Build -> build
3. Wait for it to finish and open the Compiled jar in (build/libs)
4. Get a jfxrt.jar from your jre (Make sure its Java 8) located in <jrefolder>/jre/lib/ext and open it with an archive opener (7-Zip, Winrar, etc)
5. Open the built client jar file
6. Copy everything inside jfxrt.jar to the client jar<br>
This is to prevent crashing on some launcher as it doesn't know where the jfxrt.jar is located or allow minecraft to use it or if the end-user JRE Installation doesn't include jfxrt.jar<br>
INFO: jfxrt.jar is used for the Microsoft Login Window, which used JavaFX and is included in the jfxrt.jar
7. and you should be done
#### Launching from a launcher
1. Install the normal client using the installer provided in the client website
2. Make sure you are installing version dev-1.81 or above
3. Goto your minecraft folder -> libraries -> uk/and1558/AND1558/LOCAL/
4. Rename your built client jar to "AND1558-LOCAL.jar"
5. and launch the client inside your preferred _supported_ launcher
## Common Issues when Developing
### "Caused by: java.lang.ClassNotFoundException: com.mojang.authlib.exceptions.AuthenticationException"
Re-import the gradle project
### No sound on minecraft
Copy the assets folder from your minecraft folder over to the run folder on intellij
# Thank you for your cooperations
