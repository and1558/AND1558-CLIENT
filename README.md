# AND1558-CLIENT
Welcome to the AND1558 PVP Client Public Repository!<br>
- You may use this client as a base but YOU MUST credit me for it<br>
- If you use this client or any of my code YOU MUST make your SOURCE CODE PUBLIC, no exceptions<br>
- For any issues you may have found, you are allowed to push/send fixes or create an issue for it<br>
## Building/Compiling
Tutorial on how to compile/build this Gradle Project<br>
Before you start, please make sure that you have your minecraft assets stored in the default .minecraft folder<br>
else the script would fail to download as the minecraft assets website no longer allow using http<br>
1. Open using your IDE (I used IntelliJ IDE for this)
2. Wait until everything finish loading
3. Click on gradle -> project name (should be "and1558") -> tasks -> forgegradle
4. Click on setupDecompWorkspace<br>
Note: This might take a while.<br>
5. Click on genIntellijRuns
6. Next edit your run configurations
7. Expand on the Application menu
8. Click "Minecraft Client"
9. on the line "-cp" change it to "and1558.main"
10. You should be done now
## Common Issues when Developing
### "Caused by: java.lang.ClassNotFoundException: com.mojang.authlib.exceptions.AuthenticationException"
Re-import the gradle project<br>
### No sound on minecraft
Copy the assets folder from your minecraft folder over to the run folder on intellij
<h1>Thank you for your cooperations</h1>
