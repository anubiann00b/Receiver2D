<<<<<<< HEAD
Remote2D
========

Hi there! This is a fork of Adrian Biagioli's Remote2D, hopefully designed to address some of the inefficiencies of the original version and thus to serve as an improvement. It is currently a proof-of-concept, and is not intended for commercial use. The goal of this project is to create a cross-platform, efficient, java-based, usable and developer-friendly game engine.

Remote2D is the basic class that initializes all of the other components of the editor and the engine. In Remote2D, the editor runs separately and pulls in functionality from the engine classes, like Physics, Audio, Renderer, et cetera. Both groups, the engine and the editor, exist in parallel to each other and are not the same. The editor is intended to be a tool for the developer to create maps and levels for games built on top of Remote2D. In addition, the engine uses an entity/component system where entities hold shared data and components (chosen by the developer) provide functionality. Another goal of this project is to allow the engine to run in multiple threads, and take advantage of today's multicore CPUs.

The original engine was created by Adrian Biagioli and can be found at his GitHub repository:
- https://github.com/Flafla2/Remote2D-Engine

The source code for this engine can be found at the GitHub repository:
- https://github.com/Prince781/Remote2D

With that, we hope that you may find our version to be useful.
- Ben and Princeton
=======
Receiver2D
========
Hi there! This is a _fork_ of Remote2D, hopefully designed
to address some of the inefficiencies of the original version
and thus to serve as an improvement. It is currently a 
proof-of-concept, and is not intended for commercial use.


Receiver2D is the basic class that initializes all of the other
components of the editor and the engine.
In Receiver2D, the editor runs separately and pulls in 
functionality from the engine, like Physics, Audio, Renderer, et cetera, which
runs as a separate program with the Editor being the frontend. Both programs, 
the engine and the editor, exist in parallel to each other and are not the same.
In addition, the entity system is designed with respect to the engine being 
responsible for functionality, so that entities
are _not responsible for rendering themselves_, and that everything can be
neatly parallelized.


The original engine was created by Adrian Biagioli and can
be [found at his GitHub repository](https://github.com/Flafla2/Remote2D-Engine).

With that, we hope that you may find our version to be 
useful.
* Ben and Princeton
>>>>>>> d21b640813105b2b845b5968f7b10e7fd6af4b11
