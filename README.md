# akka-bot-application

- Demo application: CLI bot, based on Akka Actors with API-service, based on Spring-boot. Stack: Java, Maven,
  Spring-boot, Akka-actor, Akka-stream, Akka-http.

1. Local API start:

- local-start.sh in /bash-scripts or manually type:
- java -jar bash-scripts/user-cli-local.jar

2. Start with deployed API (API-service deployed on heroku.com)*:

- start-with-deployed-api.sh in /bash-scripts or manually type:
- java -jar bash-scripts/user-cli-with-deploy.jar

3. Type 'help' to browse all known questions
4. Type your question
5. Type 'n' or 'N' for exit
6. First response from deployed API comes within 20-30 seconds
   (the time the server is restarted).