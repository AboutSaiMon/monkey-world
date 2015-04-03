# Monkey World

Monkey World è un framework a singolo agente, per la simulazione di sistemi intelligenti. L'agente in questione è una scimmietta che può muoversi lungo un ambiente monodimensionale. Il suo obiettivo è quello di rubare un casco di banane appeso per aria e ritornare a cuccia. Essa ha a disposizione una cassa che può spostare liberamente, sulla quale dovrà salire per poter raggiungere il suo scopo. In Monkey World è stato implementato un comportamento che permettesse alla scimmietta di portare a termine la sua missione in maniera razionale.

Questo progetto è stato sviluppato da Simone Spaccarotella {spa.simone@gmail.com} e Carmine Dodaro {carminedodaro@gmail.com}, come valutazione intermedia per l'esame di Sistemi Intelligenti https://www.mat.unical.it/ComputerScience/SistemiIntelligenti del corso di Informatica dell'Università della Calabria http://www.unical.it

Monkey and Bananas is a famous toy problem in Artificial Intelligence, particularly in logic programming and planning.
For further information about the problem see http://en.wikipedia.org/wiki/Monkey_and_banana_problem

This implementation uses DLV, a deductive database system based on disjunctive logic programming.

## Configuration
1. Download DLV from http://www.dlvsystem.com/dlv/
2. Copy the executable in a directory
3. Rename the file in *dlv* and set the PATH env variable
4. On *nix-like os run this command:
```
chmod u+x dlv
```
