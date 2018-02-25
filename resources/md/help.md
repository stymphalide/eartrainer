# The Idea
A game based on the game of set. TODO

## The Game
*Set is a real-time card game [...]. The deck consists of 81 cards varying in four features: number (one, two, or three); symbol (diamond, squiggle, oval); shading (solid, striped, or open); and colo[u]r (red, green, or purple). Each possible combination of features (e.g., a card with three striped green diamonds) appears precisely once in the deck. [...] Several Games can be played with these cards, all involving the concept of a set. A set consists of three cards satisfying all of these conditions:*
- *They all have the same number or have three different numbers.*
- *They all have the same symbol or have three different symbols.*
- *They all have the same shading or have three different shadings.*
- *They all have the same colo[u]r or have three different colo[u]rs.*

*[...] Given any two cards from the deck there is one and only one other card that forms a set with them.*

*In the standard Set game, the dealer lays out cards on the table until either twelve are laid down or someone sees a set and calls "Set!". The player who called "Set" takes the cards in the set, and the dealer continues to deal out cards until twelve are on the table. A player who sees a set among the twelve cards calls "Set" and takes the three cards, and the dealer lays three more cards on the table.*

Source: https://en.wikipedia.org/wiki/Set_(game)

## An Example of a Set

![An example of a set](https://upload.wikimedia.org/wikipedia/commons/8/8f/Set-game-cards.png)

## Our Tweaks
However, we do not want to create a clone of this game but rather convert it into something new.
Our idea is to use this notion of a set and apply it to music. 
Instead of having a 4x3 grid where one has to find a set, we will use a simpler version of the game.
In this version two 'cards' already lie on the table. By the rules of set, the third card is clearly determined.

In our version of the game the visual features of colour, number, symbol and shading are replaced by auditive features.
These auditive features are:
 - Which interval is it (out of a predetermined list of three)?
 - Is the lower or the higher note played first or are they played simultaneously?
 - In which range is the interval (high, middle, low)?
 - By which instrument is the interval played (out of a predetermined list of three)?
 
## Level 1 Specifications
In the first level of the game the list of intervals, range and instruments are quite simple. In further levels these features will be adapted in order to achieve a more difficult version.

### Intervals

The list of intervals to choose from is:
- Perfect fourth ![Perfect fourth](resources/img/perfect_fourth.png)
- Perfect fifth ![Perfect fifth](resources/img/perfect_fifth.png)
- Perfect octave ![Perfect octave](resources/img/perfect_octave.png)

(Image source: https://en.wikipedia.org/)

### Ranges

For the ranges we have taken the range of a piano and cut it roughly in three parts.

![The Range of a Piano](resources/img/range_piano_coloured.png)

(Image source: https://i.stack.imgur.com/MpiRg.png)

The blue keys indicate the start of a range whereas the red keys indicate the end of a range.

The pitches are listed in [Scientific Pitch Notation](https://en.wikipedia.org/wiki/Scientific_pitch_notation).

- The low range spans from the zeroth octave to the second.
(C2-B3)
- The middle range spans from the third octave to the fifth octave.
(C4-B5)
- The high range spans from the sixth to the eighth octave.
(C6-B7)

### Instruments

The instruments we chose are:
 - Piano
 - Strings
 - Brass

Note that strings and brass are not specific instruments but rather a group of instruments. This is done to achieve the wide range of tones to be played.

### Order

This is a knob that we can not turn, the default orders are:
- Upwards - meaning the lower note is played before the higher.
- Downwards - meaning the higher note is played before the lower.
- Chordal - both notes are played simultaneously.

### Overview

Interval | Range | Order | Instrument
---------|-------|-------|-----------
perfect fifth | low (C2 - B3) | upwards | piano
perfect fourth | middle (C4 - B5) | downwards | strings
perfect octave | high (C6 - B7) | at the same time | brass
