# AND1558-CLIENT
> This client has been discontinued.<br>
Don't worry! The development for this client will continue on another name and with a different loader.<br>
As you may expect from the package rename, it will probably be named DevyClient, although this decision might change later on in the future.<br><br>
You might ask why are we moving?<br>
Well it's because this project uses deprecated tools that are no longer supported, and I also want to bring forge mods support. For example: Replay Mod.<br>
Thank you for understanding!<br>
(Licenses/rules on this repo WILL NOT AND WOULD NEVER CHANGE).
#### Welcome to the AND1558 Client Public Repository!
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
9. on the line "-cp" change it to "aydendevy.main"
10. You should be done now
#### Compiling
1. Goto Gradle Task
2. Click on Tasks -> Build -> build
3. Wait for it to finish and open the Compiled jar in (build/libs)
4. and you should be done
- Note: You no longer need to extract jfxrt.jar from a Java Installation!<br>
The Client no longer needs them for Microsoft Login
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
