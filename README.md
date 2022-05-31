# Tapir Swagger UI with Finatra

## Start the local server
You have to use OpenJDK 8, because of Finatra. For Nix users, the `shell.nix` might be helpful.
To start a local server you can run the following command:

```bash
sbt "run -http.port=:8888 -admin.port=:9990"
```

## Issue
Swagger redirect is broken. `http://localhost:8888/docs` redirects to `http://localhost:8888/docs/docs/` instead of `http://localhost:8888/docs/index.html`.
