# Jotto

## Summary

Jotto is a logic-oriented word game played with two players. Each player picks a secret word of five letters (that is in the dictionary), and the object of the game is to correctly guess the other player's word first. Players take turns guessing and giving the number of Jots, or the number of letters that are in both the guessed word and the secret word.

The Jotto application is built with a single player, playing against a computer.  The objective of the game is to correctly guess the secret word before the maximum number of guesses.  The user interface provides feedback about the success of each guess, and the progress being made by the player.  Each guess must be validated that it is present in the dictionary, of the proper length and contains repeated characters.  After each guess the player will be provided feedback about the guess, such as the number of exact character matches and the number of partial character matches.

## Development

If you are developing on Windows, it is recommended to install the IDE [IntelliJ IDEA](https://www.jetbrains.com/idea/).  This will work with the existing `jotto.iml` present in the `jotto/` directory. The output of the build process is available in the CI artifacts browser.  The artifacts have an expiration period to ensure that old build artifacts are properly cleaned up.

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

## Acknowledgements

The project icon is retrieved from [the Noun Project](docs/icon/icon.json). The original source material has been altered for the purposes of the project. The icon is used under the terms of the [Public Domain](https://creativecommons.org/publicdomain/zero/1.0/).

The project icon is by [Five by Five from the Noun Project](https://thenounproject.com/term/startup/38246/).