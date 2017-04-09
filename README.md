# Jotto
[![License][license-badge]][license-link]
[![Download][download-badge]][download-link]

# Abstract

Jotto is a logic-oriented word game played with two players. Each player picks a secret word of five letters (that is in the dictionary), and the object of the game is to correctly guess the other player's word first. Players take turns guessing and giving the number of Jots, or the number of letters that are in both the guessed word and the secret word.

# Summary

The Jotto application is built with a single player, playing against a computer.  The objective of the game is to correctly guess the secret word before the maximum number of guesses.  The user interface provides feedback about the success of each guess, and the progress being made by the player.  Each guess must be validated that it is present in the dictionary, of the proper length and contains repeated characters.  After each guess the player will be provided feedback about the guess, such as the number of exact character matches and the number of partial character matches

## Development

If you are developing on Windows, it is recommended to install the IDE [IntelliJ IDEA](https://www.jetbrains.com/idea/), a java editor.  This will work with the existing `jotto.iml` present in the source directory.

[license-badge]: https://img.shields.io/badge/license-MIT-blue.svg?maxAge=2592000
[license-link]: LICENSE

[download-badge]: https://img.shields.io/badge/artifacts-jotto-red.svg?maxAge=2592000
[download-link]: /../builds/artifacts/master/download?job=deploy