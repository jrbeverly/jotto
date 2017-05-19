# Jotto
[![Build][build-badge]][build-link]
[![License][license-badge]][license-link]
[![Download][download-badge]][download-link]

---

## Summary

Jotto is a logic-oriented word game played with two players. Each player picks a secret word of five letters (that is in the dictionary), and the object of the game is to correctly guess the other player's word first. Players take turns guessing and giving the number of Jots, or the number of letters that are in both the guessed word and the secret word.

The Jotto application is built with a single player, playing against a computer.  The objective of the game is to correctly guess the secret word before the maximum number of guesses.  The user interface provides feedback about the success of each guess, and the progress being made by the player.  Each guess must be validated that it is present in the dictionary, of the proper length and contains repeated characters.  After each guess the player will be provided feedback about the guess, such as the number of exact character matches and the number of partial character matches.

## Development

If you are developing on Windows, it is recommended to install the IDE [IntelliJ IDEA](https://www.jetbrains.com/idea/).  This will work with the existing `jotto.iml` present in the `source/` directory. The output of the build process is available [here](/../builds/artifacts/master/download?job=deploy).  The artifacts have an expiration period to ensure that old build artifacts are properly cleaned up.

### Building

You can build the image using `mvn` or scripts in the `build/` directory.  To build with maven, you can either install maven in your environment, or make use of the docker image.  To start the docker image, run the following:

```console
sh build/start.sh
```

To build with `mvn`, you can do the following: 

```console
mvn compile
```

It is recommend to use the build scripts available in `build/`.   These scripts are used in the build pipeline, ensuring that all arguments and attributes are set for compilation of the project.  The output application `jotto.jar` is available at the project root, while other build files are available in the `target/` directory.

```console
sh build/compile.sh
```

### GitLab CI

This project's application is built by [GitLab CI](https://about.gitlab.com/gitlab-ci/), following the steps defined in [`.gitlab-ci.yml`](.gitlab-ci.yml).  The build scripts are available in `build/`, which are used to compile the java project.

[license-badge]: https://img.shields.io/badge/license-MIT-blue.svg?maxAge=2592000
[license-link]: LICENSE

[build-badge]: /../badges/master/build.svg
[build-link]: /../commits/master

[download-badge]: https://img.shields.io/badge/artifacts-jotto-red.svg?maxAge=2592000
[download-link]: /../builds/artifacts/master/download?job=deploy