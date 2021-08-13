# Building

- This file is for reference purpose only that gives you basic idea on how CI system is going to build this or how to setup dev environment locally
- Dummy Credintials replace it with actual one

> **DO NOT COMMIT CREDINTIALS**

```sh
# Will be set globally on CD
export ARTIFACTORY_USERNAME=admin
export ARTIFACTORY_PASSWORD=password
export ARTIFACTORY_URL=http://venus.strandls.com/artifactory
export DB_USERNAME=postgres
export DB_PASSWORD=postgres123
export DB_URL=jdbc:postgresql://localhost:5432/database
export MTPROP_SCHEMES=http
export MTPROP_HOST=localhost:8080
```

### Build commands

```sh
sh ./@ci/build-and-deploy.sh
```

### Maven Toolbox Documentation

- [maven-toolbox](https://github.com/harshzalavadiya/maven-toolbox/blob/master/README.md)
